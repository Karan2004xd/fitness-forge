import './exercises.styles.css';
import { useDispatch, useSelector } from 'react-redux';
import ExerciseCard from '../../components/exercise-card/exercise-card.component';
import {
  fetchExerciseByPageWithFilterStart,
  fetchTotalExercisesStart 
} from '../../store/exercise/exercise.reducer';

import { 
  selectCurrentExercises, 
  selectCurrentFilters, 
  selectCurrentPage, 
  selectTotalExercises 
} from '../../store/exercise/exersice.selector';

import { ChangeEvent, useEffect, useMemo, useState } from 'react';
import useDebounce from '../../utils/debounce/use-debounce.utils';
import Button, { BUTTON_TYPE_CLASSES } from '../../components/button/button.component';
import Filters from '../../components/filters/filters.component';

const Exercises = () => {
  const dispatch = useDispatch();
  const size = 20;

  const currentCount = useSelector(selectTotalExercises);
  const currentExercises = useSelector(selectCurrentExercises);
  const page = useSelector(selectCurrentPage);
  const filters = useSelector(selectCurrentFilters);

  const filtersMap = useMemo(() => {
    if (filters) {
      return new Map<string, string | undefined>(Object.entries(filters));
    } else {
      return new Map<string, string | undefined>();
    }
  }, [filters]);

  const [searchFieldValue, setSearchFieldValue] = useState('');
  
  const getTotalExercises = () => {
    if (currentCount === 0) {
      dispatch(fetchTotalExercisesStart());
    }  
  };

  const debouncedSearch = useDebounce(() => {
    dispatch(fetchExerciseByPageWithFilterStart({
      pageNumber: page,
      size: size,
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

      <div className='main-container__utils'>
        <input type='search' placeholder='search exercise' name='searchBox' onChange={handleSearchInput}/>
        <Button buttonType={BUTTON_TYPE_CLASSES.base} onClick={() => {}}>Apply Filter</Button>

        <div className='filter-box'>
          {
            Array.from(filtersMap.entries()).map(([key, value]) => (
              <Filters checked={!!value} content={key} key={key} />
            ))
          }
        </div>
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
