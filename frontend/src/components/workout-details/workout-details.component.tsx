import { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate } from 'react-router';
import { fetchWorkoutExercisesStart, setWorkoutExercises } from '../../store/workout/workout.reducer';
import { selectCurrentWorkout, selectWorkoutExercises } from '../../store/workout/workout.selector';
import { WorkoutDetailsContent } from './workout-details.styles';

const WorkoutDetails = () => {
  const currentWorkoutTemplate = useSelector(selectCurrentWorkout);
  const workoutExercises = useSelector(selectWorkoutExercises);

  const dispatch = useDispatch();
  const navigate = useNavigate();

  useEffect(() => {
    if (currentWorkoutTemplate) {
      dispatch(fetchWorkoutExercisesStart());
    } else {
      dispatch(setWorkoutExercises({ WorkoutExercises: [] }));
    }
  }, [currentWorkoutTemplate, dispatch]);

  const goToWorkoutExercisesPage = (workoutName: string) => {
    navigate(`/workout/${workoutName}`);
  };

  return (
    <>
      {
        (workoutExercises.length > 0) ? (

        workoutExercises.map((item, index) => (
          <WorkoutDetailsContent 
            key={index} 
            onDoubleClick={() => goToWorkoutExercisesPage(item.name)}
          >
            <span>{index + 1}</span>
            <span>{item.name.replace('_', ' set ')}</span>
            <span>{item.exercises.length}</span>
          </WorkoutDetailsContent>
        ))
        ) : (
          <h1>No Exercises found for the applied template</h1>
        )
      }
    </>
  );
};

export default WorkoutDetails;
