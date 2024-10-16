import { useSelector } from 'react-redux';
import ExerciseAbout from '../../components/exercise-about/exercise-about.component';
import { selectCurrentExercise } from '../../store/exercise/exersice.selector';

const ExerciseInfo = () => {
  const currentExercise = useSelector(selectCurrentExercise);

  return (
    <>
      {
        currentExercise ? (
          <ExerciseAbout exercise={currentExercise} />
        ) : (
          <></>
        )
      }
    </>
  );
};

export default ExerciseInfo;
