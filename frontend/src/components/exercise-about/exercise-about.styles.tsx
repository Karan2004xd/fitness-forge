import { styled } from "styled-components";

export const ExerciseAboutContainer = styled.div`
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  grid-template-columns: 30% 70%;
  background: -webkit-linear-gradient(to right, #A43931, #1D4350);
  background: linear-gradient(to right, #A43931, #1D4350);
  width: 100%;
  min-height: 100vh;
  position: relative;
  color: #FCEAEC;

  @media (max-width: 85rem) {
    grid-template-columns: repeat(1, 1fr);
  }
`;

export const AboutContainerImages = styled.div`
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  width: 85%;
  margin-top: 10rem;
  margin-left: 1.5rem;

  img {
    width: 100%;
    margin: 1rem;
  }

  @media (max-width: 85rem) {
    margin-top: 15rem;
  }
`;

export const AboutContainerContent = styled.div`
  display: flex;
  flex-direction: column;
  margin-top: 10rem;
  line-height: 2.7rem;
  width: 90%;

  h1 {
    color: #E42E41;
  }

  @media (max-width: 85rem) {
    margin-left: 2rem;
    margin-right: 2rem;
  }
`;
