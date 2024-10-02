import { useCallback } from "react";
import { Link, Outlet } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import './navigation.styles.css'

const Navigation = () => {
  const navigate = useNavigate();

  const navigateToPage = useCallback((path: string) => {
    navigate(path);
  }, [navigate]);

  return (
    <>
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
      <Outlet />
    </>
  );
};

export default Navigation;
