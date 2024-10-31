import { styled } from "styled-components";

export const DetailsContainer = styled.div`
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  grid-template-columns: 10% 60% 20% 10%;
  font-size: 20px;
  padding: 1rem;
  border: 2px solid #E42E41;
  width: 60%;
  margin: 1rem;
  border-radius: 8px;
  font-weight: 550;
  margin-left: 9.8rem;

  &:active,
  &:hover {
    background: #FCEAEC;
    cursor: pointer;
    transition: 0.2s ease-out;
  }

  svg {
    fill: black;
    width: 40px;
    
    &:hover,
    &:active {
      fill: #E42E41;
      transition: 0.2s ease-out;
    }
  }
`;
