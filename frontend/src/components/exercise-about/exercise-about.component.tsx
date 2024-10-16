import { Exercise } from '../../store/exercise/exercise.types';
import { EXERCISE_API_ROUTES } from '../../utils/api/api-routes.util';
import ExerciseAboutContent from '../exercise-about-content/exercise-about-content.component';
import './exercise-about.styles.css'

export type ExerciseAboutProps = {
  exercise: Exercise;
};

const ExerciseAbout = ({ exercise }: ExerciseAboutProps) => {
  const { name,
    images,
    category,
    force,
    level,
    primaryMuscles,
    secondaryMuscles,
    mechanic,
    equipment,
    instructions 
  } = exercise;

  return (
    <div className='exercise-about-container'>
      <div className='about-container__images'>
        {
          images!.map((image) => (
            <img 
              src={`${EXERCISE_API_ROUTES.getImage}/${image}`} 
              alt={name}
              key={image}
            /> 
          ))
        }
      </div>

      <div className='about-container__content'>
        <h1>{name}</h1>
        <ul>
          <ExerciseAboutContent contentType='category' content={category} />
          <ExerciseAboutContent contentType='force' content={force} />
          <ExerciseAboutContent contentType='level' content={level} />
          <ExerciseAboutContent contentType='mechanic' content={mechanic} />
          <ExerciseAboutContent contentType='equipment' content={equipment} />
          <ExerciseAboutContent contentType='primaryMuscles' content={primaryMuscles} />
          <ExerciseAboutContent contentType='secondaryMuscles' content={secondaryMuscles} />
          <ExerciseAboutContent contentType='instructions' content={instructions} />
        </ul>
      </div>
    </div>
  );
};

export default ExerciseAbout;
