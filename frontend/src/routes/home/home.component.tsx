import { useCallback } from 'react';
import { useNavigate } from 'react-router-dom';
import Backdrop, { BACKDROP_TYPES } from '../../components/backdrop/backdrop.component';
import { BUTTON_TYPE_CLASSES } from '../../components/button/button.component';

import { 
    GetStartedButton,
  MainContainer, 
  MainDescription, 
  MainDescriptionContainer, 
  MainTitle, 
  MainTitleSpan 
} from './home.styles';

const Home = () => {
  const navigate = useNavigate();

  const navigateToPage = useCallback((path: string) => {
    navigate(path);
  }, [navigate]);

  return (
    <MainContainer>
      <Backdrop backdropType={BACKDROP_TYPES.normal} />
      <MainDescriptionContainer>
        <MainTitle>
          <p>Elevate Your
            <span> </span>
            <MainTitleSpan>
              Workout
            </MainTitleSpan>
          </p>
        </MainTitle>

        <MainDescription>
          <p>
            Forge Your Perfect Workout: Personalized, Data-Driven Fitness Plans Tailored to Your Goals and Schedule.
          </p>
          <p>
            If you are ready to see the change forge your workout now!
          </p>
        </MainDescription>

        <GetStartedButton 
          onClick={() => navigateToPage('/register')} buttonType={BUTTON_TYPE_CLASSES.base}
        >
          Get Started
        </GetStartedButton>

      </MainDescriptionContainer>
    </MainContainer>
  );
};

export default Home;
