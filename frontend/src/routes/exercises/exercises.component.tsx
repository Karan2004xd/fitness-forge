import './exercises.styles.css';
import { useDispatch, useSelector } from 'react-redux';
import ExerciseCard from '../../components/exercise-card/exercise-card.component';

import {
  fetchExerciseByPageStart,
  fetchExerciseByPageWithFilterStart,
  fetchTotalExercisesStart 
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

const Exercises = () => {
  const dispatch = useDispatch();
  const currentCount = useSelector(selectTotalExercises);
  const currentExercises = useSelector(selectCurrentExercises);
  const page = useSelector(selectCurrentPage);
  const filters = useSelector(selectFilters);

  const [searchFieldValue, setSearchFieldValue] = useState('');
  
  const getTotalExercises = () => {
    if (currentCount === 0) {
      dispatch(fetchTotalExercisesStart());
    }  
  };

  const debouncedSearch = useDebounce(() => {
    dispatch(fetchExerciseByPageWithFilterStart({
      pageNumber: page,
      size: DEFAULT_SIZE,
      filters: {
        name: searchFieldValue 
      }
    }))
  }, 500);

  const handleSearchInput = (event: ChangeEvent<HTMLInputElement>) => {
    const { value } = event.target;
    setSearchFieldValue(value);
    debouncedSearch();
  };

  useEffect(() => {
    getTotalExercises();
  });

  useEffect(() => {
    dispatch(fetchExerciseByPageStart({pageNumber: page, size: DEFAULT_SIZE}));
  }, [page, dispatch]);

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
    <div className='main-container'>
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

      <input 
        type='search' 
        placeholder='Search Exercise' 
        name='searchBox' 
        onChange={handleSearchInput}
        id='search-box'
      />

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
