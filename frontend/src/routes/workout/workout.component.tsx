import { useNavigate } from 'react-router';
import Button, { BUTTON_TYPE_CLASSES } from '../../components/button/button.component';
import WorkoutDetails from '../../components/workout-details/workout-details.component';
import './workout.styles.css';

const Workout = () => {
  const navigate = useNavigate();

  const goToMyTemplate = () => {
    navigate('/workout/my_templates');
  };

  return (
    <div className='workout-container'>
      <h1>My Workout</h1>

      <Button 
        buttonType={BUTTON_TYPE_CLASSES.base}
        onClick={goToMyTemplate}
      >
        Apply Template
      </Button>

      <div className='workout-container__title'>
        <span>Number</span>
        <span>Name</span>
        <span>Number of exercises</span>
      </div>

      <WorkoutDetails />
    </div>
  );
}

export default Workout;
