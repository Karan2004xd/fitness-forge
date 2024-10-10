import { useDispatch, useSelector } from 'react-redux';
import {
  fetchExerciseByExerciseIdStart,
  fetchExerciseByPageStart,
  fetchTotalExercisesStart 
} from '../../store/exercise/exercise.reducer';

import { selectTotalExercises } from '../../store/exercise/exersice.selector';
import './exercises.styles.css';

const Exercises = () => {
  const dispatch = useDispatch();
  const currentCount = useSelector(selectTotalExercises);
  
  const getTotalExercises = () => {
    if (currentCount === 0) {
      dispatch(fetchTotalExercisesStart());
    }  
  };

  const getExerciseByPage = (page: number = 0, size: number = 10) => {
    dispatch(fetchExerciseByPageStart({pageNumber: page, size: size}));
  };

  const getExerciseById = (exerciseId: number) => {
    dispatch(fetchExerciseByExerciseIdStart({exerciseId: exerciseId}));
  }

  return (
    <div className='main-container'>
      <h1>Total exercises: {currentCount}</h1>
      <button type='button' onClick={getTotalExercises}>Get Total Exercises</button>
      <button type='button' onClick={() => getExerciseByPage()}>Get Exercise by page</button>
      <button type='button' onClick={() => getExerciseById(873)}>Get Exercise by id</button>
    </div>
  );
}

export default Exercises;
