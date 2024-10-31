import { styled } from "styled-components";

export const WorkoutDetailsContent = styled.div`
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  grid-template-columns: 20% 70% 10%;
  width: 60%;
  margin-left: 10rem;
  font-size: 20px;
  font-weight: 550;
  margin-top: 2rem;
  border: 2px solid #E42E41;
  padding: 1rem;
  border-radius: 8px;
  margin-bottom: 5px;

  &:hover,
  &:active {
    background: #FCEAEC;
    cursor: pointer;
    transition: 0.2s ease-out;
  }
`;
