import { useDispatch, useSelector } from 'react-redux';
import ExerciseCard from '../../components/exercise-card/exercise-card.component';

import {
  fetchExerciseByPageStart,
  fetchExerciseByPageWithFilterStart,
  fetchTotalExercisesStart, 
  setCurrentExercise, 
  setCurrentSize, 
  setFilters,
  setToggleFilterBox
} from '../../store/exercise/exercise.reducer';

import { 
  selectCurrentExercises, 
  selectCurrentPage, 
  selectCurrentSize, 
  selectFilters, 
  selectToggleFilterBox, 
  selectTotalExercises 
} from '../../store/exercise/exersice.selector';

import { ChangeEvent, useCallback, useEffect, useState } from 'react';
import useDebounce from '../../utils/debounce/use-debounce.utils';
import { DEFAULT_SIZE, Exercise } from '../../store/exercise/exercise.types';
import { BUTTON_TYPE_CLASSES } from '../../components/button/button.component';
import FiltersBox from '../../components/filters-box/filters-box.component';
import Backdrop, { BACKDROP_TYPES } from '../../components/backdrop/backdrop.component';

import {
  ApplyFilterButton,
  ExerciseNotFound,
  LoadMoreExerciseButton,
  MainContainer,
  MainContainerExercises,
  MainContainerTitle,
  MainContainerUtils,
  SearchExercise,
  TitleCount 
} from './exercises.styles';
import { useNavigate } from 'react-router';
import { EXERCISE_API_ROUTES } from '../../utils/api/api-routes.util';

const Exercises = () => {
  const dispatch = useDispatch();
  const currentCount = useSelector(selectTotalExercises);
  const currentExercises = useSelector(selectCurrentExercises);
  const page = useSelector(selectCurrentPage);
  const currentSize = useSelector(selectCurrentSize);
  const filters = useSelector(selectFilters);
  const navigate = useNavigate();

  const [searchFieldValue, setSearchFieldValue] = useState('');
  const toggleFilterBox = useSelector(selectToggleFilterBox);

  const handleExerciseCardClick = (exercise: Exercise | undefined) => {
    if (exercise) {
      dispatch(setCurrentExercise({currentExercise: exercise}))
      navigate(`${EXERCISE_API_ROUTES.getExerciseInfo}/${exercise.id}`);
    }
  }
  
  const getTotalExercises = () => {
    if (currentCount === 0) {
      dispatch(fetchTotalExercisesStart());
    }  
  };

  const debouncedSearch = useDebounce(() => {
    const newFilter = {
      ...filters,
      name: searchFieldValue
    };

    dispatch(setFilters({filters: newFilter}));
  }, 500);

  const loadExercises = useCallback(() => {
    if (filters) {
      dispatch(fetchExerciseByPageWithFilterStart({
        pageNumber: page,
        size: currentSize,
        filters: filters
      }));
    } else {
      dispatch(fetchExerciseByPageStart({pageNumber: page, size: currentSize}));
    }
  }, [filters, page, currentSize, dispatch]);

  const handleSearchInput = (event: ChangeEvent<HTMLInputElement>) => {
    const { value } = event.target;
    if (value && value !== '') {
      setSearchFieldValue(value);
      debouncedSearch();
    } else {
      dispatch(setFilters({filters: {...filters, name: undefined}}))
    }
  };

  const handleFiltersClick = () => {
    dispatch(setToggleFilterBox({toggleFilterBox: !toggleFilterBox}));
  };

  const loadMoreExercisesClick = () => {
    dispatch(setCurrentSize({currentSize: currentSize + DEFAULT_SIZE}));
  };

  useEffect(() => {
    loadExercises();
  }, [loadExercises]);

  useEffect(() => {
    getTotalExercises();
    if (!currentExercises || currentExercises.length === 0) {
      dispatch(fetchExerciseByPageStart({pageNumber: 0, size: DEFAULT_SIZE}));
      dispatch(setCurrentSize({currentSize: DEFAULT_SIZE}));
    }
  });

  useEffect(() => {
    if (filters) {
      dispatch(fetchExerciseByPageWithFilterStart({
        pageNumber: page,
        size: currentSize,
        filters: filters
      }));
    }
  }, [filters, dispatch, page, currentSize]);

  return (
    <MainContainer>
      {
        toggleFilterBox ? (
          <>
            <Backdrop backdropType={BACKDROP_TYPES.dark} onClick={handleFiltersClick} />
            <FiltersBox />
          </>
        ) : (
            <></>
          )
      }
      <MainContainerTitle>
        <TitleCount>
          <span>{currentCount}</span>
          <span> </span>
          Total Exercises For Every Level
        </TitleCount>
        <p>
          Beginner To Advanced, We Have Got You Covered!
        </p>
      </MainContainerTitle>

      <MainContainerUtils>
        <SearchExercise 
          type='search' 
          placeholder='Search Exercise' 
          name='searchBox' 
          onChange={handleSearchInput}
        />
        <ApplyFilterButton 
          buttonType={BUTTON_TYPE_CLASSES.base}
          onClick={() => handleFiltersClick()}
        >
          Apply Filters
        </ApplyFilterButton>
      </MainContainerUtils>

      <MainContainerExercises>
        {
          currentExercises?.length ? (
            currentExercises.map(
              (item) => (
                <ExerciseCard 
                  exercise={item}
                  key={item.exerciseId}
                  onClick={() => handleExerciseCardClick(item ? item : undefined)} />
              )
            )
          ) : (
              <ExerciseNotFound>No exercises where found for the applied filter!</ExerciseNotFound>
            )
        }
      </MainContainerExercises>

      <LoadMoreExerciseButton buttonType={BUTTON_TYPE_CLASSES.base} onClick={loadMoreExercisesClick}>
        Load More
      </LoadMoreExerciseButton>

    </MainContainer>
  );
}

export default Exercises;
