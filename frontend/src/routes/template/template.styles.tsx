import { styled } from "styled-components";
import FormInput from "../../components/form-input/form-input.component";

export const MainContainer = styled.div`
  position: absolute;
  top: 10%;
  display: flex;
  flex-direction: column;
  width: 100%;
  background: -webkit-linear-gradient(to right, #A43931, #1D4350);
  background: linear-gradient(to right, #A43931, #1D4350);
  background-size: cover;
`;

export const MainContainerForm = styled.form`
  display: flex;
  flex-direction: column;
  width: 70%;
  position: relative;
  margin-top: 10rem;
  left: 10%;

  button {
    width: 35%;
    margin-top: 1rem;
    justify-content: center;
    border-radius: 12px;
    margin-bottom: 3rem;
  }

  span {
    font-weight: bold;
    font-size: 25px;
    margin-top: 2rem;
    margin-bottom: 1rem;
  }
  
  select {
    width: 98%;
    margin-left: 10px;
    padding: 1rem;
    padding-left: 2rem;
    border-radius: 10px;
    background: #FCEAEC;
    border: 2px solid #E42E41;
    font-size: 20px;
    font-weight: bold;

    &:hover,
    &:active {
      border: 3px solid #E42E41;
    }
  }
`;

export const TextInput = styled(FormInput)`
  width: 94%;
  margin: 0;
  border: none;
  font-size: 20px;
  border: 2px solid #E42E41;
  
  &:hover {
    border: 3px solid #E42E41;
  }

  &::placeholder {
    font-size: 18px;
  }
`;
