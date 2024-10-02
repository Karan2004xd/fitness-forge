import { useCallback } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import './home.styles.css'

const Home = () => {
  const navigate = useNavigate();

  const navigateToPage = useCallback((path: string) => {
    navigate(path);
  }, [navigate]);

  return (
    <div className='main-container'>
      <div className='main-container__backdrop'>
        <nav className='main-container__navbar'>
          <Link to='/' className='navbar-link__left'>
            <p id='title'>
              Fitness<span id='inner-title'>Forge</span>
            </p>
          </Link>
          <ul className='navbar-link__middle'>
            <li className='middle-body'>
              <Link to='/'>
                Home
              </Link>
            </li>

            <li className='middle-body'>
              <Link to='/exercises'>
                Exercises
              </Link>
            </li>

            <li className='middle-body'>
              <Link to='/about'>
                About
              </Link>
            </li>
          </ul>

          <button onClick={() => navigateToPage('/login')} className='navbar-link__right'>Login</button>
        </nav>

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
