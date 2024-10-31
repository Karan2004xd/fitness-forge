import { styled } from "styled-components";
import ExerciseCard from "../../components/exercise-card/exercise-card.component";
import { CardContainerContent, ContentCategory, ContentForce } from "../../components/exercise-card/exercise-card.styles";
import { MainContainerExercises } from "../exercises/exercises.styles";

export const WorkoutExercisesContainer = styled.div`
  position: absolute;
  top: 10%;
  width: 100%;
  min-height: 100vh;
  background: -webkit-linear-gradient(to right, #A43931, #1D4350);
  background: linear-gradient(to right, #A43931, #1D4350);
  background-size: cover;

  h1 {
    margin-left: 5rem;
    margin-top: 8rem;
  }
`;

export const WorkoutExercisesContent = styled(MainContainerExercises)`
  margin-top: 5rem;
`;

export const WorkoutExerciseCard = styled(ExerciseCard)`
  ${CardContainerContent} {
    height: 30%;

    h1 {
      margin-top: 20px;
      margin-left: 10px;
    }
  }

  ${ContentForce} {
    bottom: 20%;
  }

  ${ContentCategory} {
    bottom: -2%;
  }
`;
