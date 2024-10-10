import './exercises.styles.css';
import { useDispatch, useSelector } from 'react-redux';
import ExerciseCard from '../../components/exercise-card/exercise-card.component';
import {
  fetchExerciseByExerciseIdStart,
  fetchExerciseByPageStart,
  fetchTotalExercisesStart 
} from '../../store/exercise/exercise.reducer';

import { selectCurrentExercises, selectTotalExercises } from '../../store/exercise/exersice.selector';

const Exercises = () => {
  const dispatch = useDispatch();
  const currentCount = useSelector(selectTotalExercises);
  const currentExercises = useSelector(selectCurrentExercises);
  
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
