import { useDispatch, useSelector } from 'react-redux';
import { fetchTotalExercisesStart } from '../../store/exercise/exercise.reducer';
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

  return (
    <div className='main-container'>
      <h1>Total exercises: {currentCount}</h1>
      <input type='search' name='searchField' value='Search exercises' />
      <button type='button' onClick={getTotalExercises}>Get Total Exercises</button>
    </div>
  );
}

export default Exercises;
