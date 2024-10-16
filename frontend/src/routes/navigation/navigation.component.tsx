import { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Outlet, useNavigate } from "react-router-dom";
import { BUTTON_TYPE_CLASSES } from "../../components/button/button.component";
import LinkTag from "../../components/link-tag/link-tag.component";
import { signOut } from "../../store/member/member.reducer";
import { selectIsMemberAuthenticated } from "../../store/member/member.selector";
import { MainContainerNavbar, MainLogo, NavbarBodyLeft, NavbarBodyMiddle, NavbarBodyRight } from "./navigation.styles";

const defaultBtnValues = {
  signIn: {
    name: 'Sign In',
    path: '/login'
  },
  signOut: {
    name: 'Sign Out',
    path: '/'
  },
};

const Navigation = () => {
  const dispatch = useDispatch();
  const isAuthenticated = useSelector(selectIsMemberAuthenticated);
  const navigate = useNavigate();

  const [buttonChildren, setButtonChildren] = useState(defaultBtnValues.signIn);

  const handleBtnClick = () => {
    if (buttonChildren === defaultBtnValues.signOut) {
      dispatch(signOut());
    }
    navigate(buttonChildren.path);
  }

  useEffect(() => {
    if (isAuthenticated) {
      setButtonChildren(defaultBtnValues.signOut);
    } else {
      setButtonChildren(defaultBtnValues.signIn);
    }
  }, [isAuthenticated])

  return (
    <>
      <MainContainerNavbar>
        <NavbarBodyLeft to='/' id='navbar-link__left' animate={false}>
          <MainLogo>
            Fitness<span>Forge</span>
          </MainLogo>
        </NavbarBodyLeft>
        <NavbarBodyMiddle>
          <li>
            <LinkTag to='/' animate={true}>
              Home
            </LinkTag>
          </li>

          <li>
            <LinkTag to='/exercises' animate={true}>
              Exercises
            </LinkTag>
          </li>

          <li>
            <LinkTag to='/about' animate={true}>
              About
            </LinkTag>
          </li>
        </NavbarBodyMiddle>

        <NavbarBodyRight
          buttonType={BUTTON_TYPE_CLASSES.base}
          onClick={handleBtnClick}
        >
          {buttonChildren.name}
        </NavbarBodyRight>
      </MainContainerNavbar>
      <Outlet />
    </>
  );
};

export default Navigation;
