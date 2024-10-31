import { styled } from "styled-components";
import ExerciseCardContent from "../exercise-card-content/exercise-card-content.component";

export const CardContainer = styled.div`
  display: flex;
  flex-direction: column;
  margin: 5px 5px;
  position: relative;

  &:hover,
  &:active {
    transform: translateY(-5px);
    box-shadow: 0 8px 16px rgba(0, 0, 0, 0.8);
    border: 2px solid #E42E41;
    cursor: pointer;
    transition: transform 0.2s ease-in;
  }

  img {
    width: 100%;
    height: 300px;
    object-fit: cover;
  }
`;

export const ContentForce = styled(ExerciseCardContent)`
  position: absolute;
  bottom: 24%;
  right: 0;
  margin-bottom: 10px;
  margin-right: 10px;
`;

export const ContentCategory = styled(ExerciseCardContent)`
  position: absolute;
  bottom: 0;
  right: 0;
  margin-bottom: 10px;
  margin-right: 10px;
`;

export const CardContainerContent = styled.div`
  background: black;
  display: flex;
  flex-direction: column;
  color: #FCEAEC;
  font-size: 12px;
  padding-bottom: 10px;
  position: relative;
  width: 100%;

  span,
  h1 {
    margin-left: 10px;
  }

  span {
    font-size: 14px;
    font-weight: 500;
  }
`;
