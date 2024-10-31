import { styled } from "styled-components";

export const WorkoutContainer = styled.div`
  position: absolute;
  top: 10%;
  display: flex;
  flex-direction: column;
  width: 100%;
  min-height: 100vh;
  background: -webkit-linear-gradient(to right, #A43931, #1D4350);
  background: linear-gradient(to right, #A43931, #1D4350);
  background-size: cover;
  
  button {
    width: 15%;
    margin-top: 1rem;
    justify-content: center;
    border-radius: 12px;
    margin-left: 3rem;
  }

  h1 {
    margin-top: 5rem;
    margin-left: 3rem;
  }
`;

export const WorkoutContainerTitle = styled.div`
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  grid-template-columns: 20% 50% 25%;
  width: 65%;
  margin-left: 10rem;
  font-size: 20px;
  font-weight: bold;
  margin-top: 5rem;
`;
