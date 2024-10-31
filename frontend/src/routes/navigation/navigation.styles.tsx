import { styled } from "styled-components";
import Button from "../../components/button/button.component";
import LinkTag from "../../components/link-tag/link-tag.component";

export const MainContainerNavbar = styled.nav`
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  background: black;
  width: 100vw;
  position: fixed;
  z-index: 100;

  a {
    text-decoration: none;
  }

  @media (max-width: 75rem) {
    flex-direction: column;
  }
`;

export const NavbarBodyLeft = styled(LinkTag)`
  flex: 1;
  font-size: 30px;
  font-weight: bold;
  padding-left: 5rem;

  @media (max-width: 75rem) {
    margin-right: 7rem;
  }
`;

export const MainLogo = styled.p`
  color: #FCEAEC;
  
  span {
    color: #E42E41;
  }
`;

export const NavbarBodyMiddle = styled.ul`
  flex: 1;
  display: flex;
  justify-content: flex-start;
  list-style: none;
  margin-right: 15rem;

  li {
    margin-left: 1rem;
    margin-right: 1rem;
    font-size: 20px;
    font-weight: 500;
  }

  @media (max-width: 75rem) {
    margin-right: 5rem;
    margin-top: -0.80rem;
  }
`;

export const NavbarBodyRight = styled(Button)`
  @media (max-width: 75rem) {
    margin-left: 2rem;
    margin-top: 10px;
    margin-bottom: 1rem;
  }
`;
