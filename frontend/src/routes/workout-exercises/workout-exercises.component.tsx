import { useEffect, useState } from 'react';
import { useSelector } from 'react-redux';
import { useParams } from 'react-router';
import { Exercise } from '../../store/exercise/exercise.types';
import { selectWorkoutExercises } from '../../store/workout/workout.selector';
import { WorkoutExerciseCard, WorkoutExercisesContainer, WorkoutExercisesContent } from './workout-exercises.styles';

const WorkoutExercises = () => {
  const { workoutName } = useParams<{ workoutName: string }>();
  const workoutExercises = useSelector(selectWorkoutExercises);

  const [exercises, setExercises] = useState<Exercise[]>([]);

  useEffect(() => {
    if (workoutExercises && workoutName) {
      const workout = Object.entries(workoutExercises).find(
        ([, value]) => value.name === workoutName
      );

      if (workout) {
        setExercises(workout[1].exercises);
      }
    }
  }, [workoutExercises, workoutName]);

  return (
    <WorkoutExercisesContainer>
      <h1>{workoutName?.replace('_', ' SET:- ').toUpperCase()}</h1>
      <WorkoutExercisesContent>
        {
          exercises.map((exercise, index) => (
            <WorkoutExerciseCard exercise={exercise} key={index} />
          ))
        }
      </WorkoutExercisesContent>
    </WorkoutExercisesContainer>
  );
};

export default WorkoutExercises;
