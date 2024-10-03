import { useCallback } from "react";
import { Outlet, useNavigate } from "react-router-dom";
import Button, { BUTTON_TYPE_CLASSES } from "../../components/button/button.component";
import LinkTag from "../../components/link-tag/link-tag.component";
import './navigation.styles.css'

const Navigation = () => {
  const navigate = useNavigate();

  const navigateToPage = useCallback((path: string) => {
    navigate(path);
  }, [navigate]);

  return (
    <>
      <nav className='main-container__navbar'>
        <LinkTag to='/' id='navbar-link__left' animate={false}>
          <p id='title'>
            Fitness<span id='inner-title'>Forge</span>
          </p>
        </LinkTag>
        <ul className='navbar-link__middle'>
          <li className='middle-body'>
            <LinkTag to='/'>
              Home
            </LinkTag>
          </li>

          <li className='middle-body'>
            <LinkTag to='/exercises'>
              Exercises
            </LinkTag>
          </li>

          <li className='middle-body'>
            <LinkTag to='/about'>
              About
            </LinkTag>
          </li>
        </ul>

        <Button
          onClick={() => navigateToPage('/login')}
          buttonType={BUTTON_TYPE_CLASSES.base}
          className='navbar-link__right'
        >
          Login
        </Button>
      </nav>
      <Outlet />
    </>
  );
};

export default Navigation;
