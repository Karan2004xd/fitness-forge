import { styled } from "styled-components";
import Button from "../../components/button/button.component";

export const ExerciseNotFound = styled.h1`
  margin-left: 4rem;
  margin-top: 3rem;
  margin-bottom: 7rem;
`;

export const TitleCount = styled.p`
  span {
    color: #E42E41;
  }

  @media (max-width: 75rem) {
    margin-top: 7rem;
  }

  @media (max-width: 40rem) {
    margin-top: 7rem;
  }
`;

export const MainContainerTitle = styled.div`
  font-size: 30px;
  font-weight: 600;
  color: #FCEAEC;
  display: flex;
  flex-direction: column;
  line-height: 1.5px;
  margin: 4rem 0 15rem 2rem;

  @media (max-width: 75rem) {
    line-height: 2rem;
    margin-bottom: 4rem;
  }

  @media (max-width: 40rem) {
    line-height: 4rem;
  }
`;

export const ApplyFilterButton = styled(Button)`
  height: 50%;
  justify-content: center;
  margin-top: 1.9rem;

  @media (max-width: 60rem) {
    margin: 0;
    margin-left: 1.9rem;
    margin-bottom: 10px;
    width: 40%;
  }
`;

export const SearchExercise = styled.input`
  width: 30%;
  padding: 1rem;
  margin: 2rem;
  color: black;
  font-size: 15px;
  border-radius: 8px;
  background: #FCEAEC;

  @media (max-width: 60rem) {
    width: 60%;
  }
`;

export const MainContainerUtils = styled.div`
  display: flex;
  flex-direction: row;

  @media (max-width: 60rem) {
    flex-direction: column;
  }
`;

export const MainContainerExercises = styled.div`
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  column-gap: 10px;
  row-gap: 10px;

  @media (max-width: 75rem) {
    display: grid;
    grid-template-columns: repeat(3, 1fr);
    column-gap: 10px;
  }

  @media (max-width: 60rem) {
    display: grid;
    grid-template-columns: repeat(2, 1fr);
    column-gap: 10px;
  }

  @media (max-width: 40rem) {
    display: grid;
    grid-template-columns: repeat(1, 1fr);
    column-gap: 10px;
  }
`;

export const MainContainer = styled.div`
  position: absolute;
  top: 10%;
  display: flex;
  width: 100%;
  flex-direction: column;
  background-color: -webkit-linear-gradient(to right, #414345, #232526);
  background: linear-gradient(to right, #414345, #232526);
  background-size: cover;
`;
