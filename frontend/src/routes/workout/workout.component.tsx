import { useNavigate } from 'react-router';
import Button, { BUTTON_TYPE_CLASSES } from '../../components/button/button.component';
import WorkoutDetails from '../../components/workout-details/workout-details.component';
import { WorkoutContainer, WorkoutContainerTitle } from './workout.styles';

const Workout = () => {
  const navigate = useNavigate();

  const goToMyTemplate = () => {
    navigate('/workout/my_templates');
  };

  return (
    <WorkoutContainer>
      <h1>My Workout</h1>

      <Button 
        buttonType={BUTTON_TYPE_CLASSES.base}
        onClick={goToMyTemplate}
      >
        Apply Template
      </Button>

      <WorkoutContainerTitle>
        <span>Number</span>
        <span>Name</span>
        <span>Number of exercises</span>
      </WorkoutContainerTitle>

      <WorkoutDetails />
    </WorkoutContainer>
  );
}

export default Workout;
