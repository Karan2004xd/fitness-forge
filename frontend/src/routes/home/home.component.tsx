import { useCallback } from 'react';
import { useNavigate } from 'react-router-dom';
import Backdrop, { BACKDROP_TYPES } from '../../components/backdrop/backdrop.component';
import Button, { BUTTON_TYPE_CLASSES } from '../../components/button/button.component';
import './home.styles.css'

const Home = () => {
  const navigate = useNavigate();

  const navigateToPage = useCallback((path: string) => {
    navigate(path);
  }, [navigate]);

  return (
    <div className='main-container'>
      <Backdrop backdropType={BACKDROP_TYPES.normal} />
      <div className='main-description-container'>
        <div className='main-description-container__title'>
          <p>Elevate Your
            <span> </span>
            <span>
              Workout
            </span>
          </p>
        </div>

        <div className='main-description-container__description'>
          <p>
            Forge your Fitness with Customized Workouts and Smart Calorie Tracking,
          </p>
          <p>
            if you are ready to see the change get started now!
          </p>
        </div>

        <Button 
          onClick={() => navigateToPage('/register')} buttonType={BUTTON_TYPE_CLASSES.base}
        >
          Get Started
        </Button>

      </div>
    </div>
  );
};

export default Home;
