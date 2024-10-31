import { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { fetchWorkoutExercisesStart } from '../../store/workout/workout.reducer';
import { selectWorkoutExercises } from '../../store/workout/workout.selector';
import './workout-details.styles.css';

const WorkoutDetails = () => {
  const workoutExercises = useSelector(selectWorkoutExercises);
  const dispatch = useDispatch();

  useEffect(() => {
    if (!workoutExercises || workoutExercises.length === 0) {
      dispatch(fetchWorkoutExercisesStart());
    }
  }, []);

  return (
    <div className='workout-details__container'>
    </div>
  );
};

export default WorkoutDetails;
