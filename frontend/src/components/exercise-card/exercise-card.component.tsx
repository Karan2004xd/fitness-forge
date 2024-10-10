import './exercise-card.styles.css'
import { Exercise } from '../../store/exercise/exercise.types';
import { EXERCISE_API_ROUTES } from '../../utils/api/api-routes.util';

export type ExerciseCardProps = {
  exercise: Exercise;
};

const ExerciseCard = ({ exercise }: ExerciseCardProps) => {
  const { name, images, level } = exercise;
  return (
    <div className='card-container'>
      <img 
        src={`${EXERCISE_API_ROUTES.getImage}/${images ? images[0] : ''}`} 
        alt={name}
        className='card-image'
      /> 
      <h1>{name}</h1>
      <span>{level}</span>
    </div>
  );
};

export default ExerciseCard;
