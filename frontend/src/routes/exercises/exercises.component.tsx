import './exercises.styles.css';
import { useDispatch, useSelector } from 'react-redux';
import ExerciseCard from '../../components/exercise-card/exercise-card.component';

import {
  fetchExerciseByPageStart,
  fetchExerciseByPageWithFilterStart,
  fetchTotalExercisesStart, 
  setFilters
} from '../../store/exercise/exercise.reducer';

import { 
  selectCurrentExercises, 
  selectCurrentPage, 
  selectFilters, 
  selectTotalExercises 
} from '../../store/exercise/exersice.selector';

import { ChangeEvent, useEffect, useState } from 'react';
import useDebounce from '../../utils/debounce/use-debounce.utils';
import { DEFAULT_SIZE } from '../../store/exercise/exercise.types';
import Button, { BUTTON_TYPE_CLASSES } from '../../components/button/button.component';
import FiltersBox from '../../components/filters-box/filters-box.component';
import Backdrop, { BACKDROP_TYPES } from '../../components/backdrop/backdrop.component';

const Exercises = () => {
  const dispatch = useDispatch();
  const currentCount = useSelector(selectTotalExercises);
  const currentExercises = useSelector(selectCurrentExercises);
  const page = useSelector(selectCurrentPage);
  const filters = useSelector(selectFilters);

  const [searchFieldValue, setSearchFieldValue] = useState('');
  const [toggleFilters, setToggleFilters] = useState(false);
  
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

  const handleSearchInput = (event: ChangeEvent<HTMLInputElement>) => {
    const { value } = event.target;
    if (value && value !== '') {
      setSearchFieldValue(value);
      debouncedSearch();
    } else {
      dispatch(setFilters({filters: {...filters, name: undefined}}))
    }
  };

  const handleFiltersClick = (isButtonClick: boolean = true) => {
    if (isButtonClick) {
      setToggleFilters(true);
    } else {
      setToggleFilters(false);
    }
  };

  useEffect(() => {
    getTotalExercises();
    if (!currentExercises) {
      dispatch(fetchExerciseByPageStart({pageNumber: page, size: DEFAULT_SIZE}));
    }
  });

  useEffect(() => {
    if (filters) {
      dispatch(fetchExerciseByPageWithFilterStart({
        pageNumber: page,
        size: DEFAULT_SIZE,
        filters: filters
      }));
    }
  }, [filters, dispatch, page]);

  return (
    <div className='main-container' >
      {
        toggleFilters ? (
          <>
            <Backdrop backdropType={BACKDROP_TYPES.dark} onClick={() => handleFiltersClick(false)} />
            <FiltersBox />
          </>
        ) : (
            <></>
          )
      }
      <div className='main-container__title'>
        <p id='title-count'>
          <span>{currentCount}</span>
          <span> </span>
          Total Exercises For Every Level
        </p>
        <p>
          Beginner To Advanced, We Have Got You Covered!
        </p>
      </div>

      <div className='main-container__utils'>
        <input 
          type='search' 
          placeholder='Search Exercise' 
          name='searchBox' 
          onChange={handleSearchInput}
        />
        <Button buttonType={BUTTON_TYPE_CLASSES.base} onClick={() => handleFiltersClick()}>Apply Filters</Button>
      </div>

      <div className='main-container__exercises'>
        {
          currentExercises?.length ? (
            currentExercises.map(
              (item) => (
                <ExerciseCard exercise={item} key={item.exerciseId} />
              )
            )
          ) : (
              <></>
            )
        }
      </div>
    </div>
  );
}

export default Exercises;
