import { styled } from "styled-components";
import backgroundImage from '../../assets/images/home-page-background.jpg'
import Button from "../../components/button/button.component";

export const MainContainer = styled.div`
  display: block;
  position: absolute;
  background-image: url(${backgroundImage});
  background-size: cover;
  height: 100%;
  width: 100%;
  z-index: 0;
`;

export const GetStartedButton = styled(Button)`
  font-size: 20px;
  margin-top: 3rem;
  z-index: 1;
`;

export const MainDescriptionContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  color: #FCEAEC;
  margin-left: 5rem;
  margin-top: 5rem;
`;

export const MainTitleSpan = styled.span`
  color: #E42E41;
`;

export const MainTitle = styled.div`
  font-size: 60px;
  font-weight: bold;
  line-height: 1.5rem;
  margin-top: 10rem;
  z-index: 1;

  @media (max-width: 75rem) {
    line-height: 4rem;
  }
`;

export const MainDescription = styled.div`
  line-height: 5px;
  font-size: 20px;
  z-index: 1;

  @media (max-width: 75rem) {
    line-height: 1.3rem;
  }
`;
