import './exercise-card.styles.css'
import { Exercise } from '../../store/exercise/exercise.types';
import { EXERCISE_API_ROUTES } from '../../utils/api/api-routes.util';

export type ExerciseCardProps = {
  exercise: Exercise;
};

const ExerciseCard = ({ exercise }: ExerciseCardProps) => {
  const { name, images, level, force } = exercise;
  return (
    <div className='card-container'>
      <img 
        src={`${EXERCISE_API_ROUTES.getImage}/${images ? images[0] : ''}`} 
        alt={name}
        className='card-image'
      /> 
      <div className='card-container__content'>
        <h1>{name}</h1>
        <span>
          {level 
            ? level?.at(0)?.toUpperCase() + level?.substring(1).toLowerCase() 
            : ''
          }
        </span>
        <span id='content-force'>Force: {
          force
            ? force.at(0)?.toUpperCase() + force.substring(1).toLowerCase()
            : ''
        }
        </span>
      </div>
    </div>
  );
};

export default ExerciseCard;
