import { useCallback } from 'react';
import { useNavigate } from 'react-router-dom';
import './home.styles.css'

const Home = () => {
  const navigate = useNavigate();

  const navigateToPage = useCallback((path: string) => {
    navigate(path);
  }, [navigate]);

  return (
    <div className='main-container'>
      <div className='main-container__backdrop'>
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

          <button onClick={() => navigateToPage('/register')} className='navbar-link__right main-description-container__btn'>Get Started</button>
        </div>
      </div>
    </div>
  );
};

export default Home;
