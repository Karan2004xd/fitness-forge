import { HTMLAttributes } from 'react';
import { useDispatch } from 'react-redux';
import { useNavigate } from 'react-router';
import { setCurrentExercise } from '../../store/exercise/exercise.reducer';
import { Exercise } from '../../store/exercise/exercise.types';
import { EXERCISE_API_ROUTES } from '../../utils/api/api-routes.util';
import ExerciseCardContent from '../exercise-card-content/exercise-card-content.component';
import { 
  CardContainer,
  CardContainerContent,
  ContentCategory,
  ContentForce 
} from './exercise-card.styles';

export type ExerciseCardProps = {
  exercise: Exercise;
} & HTMLAttributes<HTMLDivElement>;

const ExerciseCard = ({ exercise, ...otherProps }: ExerciseCardProps) => {
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const handleExerciseCardClick = (exercise: Exercise | undefined) => {
    if (exercise) {
      dispatch(setCurrentExercise({currentExercise: exercise}))
      navigate(`${EXERCISE_API_ROUTES.getExerciseInfo}/${exercise.id}`);
    }
  }

  const { name, images, level, force, category } = exercise;
  return (
    <CardContainer 
      {...otherProps} 
      onClick={() => handleExerciseCardClick(exercise ? exercise : undefined)}
    >
      <img 
        src={`${EXERCISE_API_ROUTES.getImage}/${images ? images[0] : ''}`} 
        alt={name}
      /> 
      <CardContainerContent>
        <h1>{name}</h1>
        <ExerciseCardContent content={level ? level : ''} />
        <ContentForce content={`Force: ${force ? force : 'None'}`} />
        <ContentCategory content={`Category: ${category ? category : 'None'}`} />
      </CardContainerContent>
    </CardContainer>
  );
};

export default ExerciseCard;
