import { styled } from "styled-components";

export const InputContainer = styled.input`
  width: 90%;
  padding: 1rem;
  padding-left: 40px;
  border-radius: 8px;
  font-size: 18px;
  background-color: #FCEAEC;
  color: black;
  transition: 0.2s ease;
  
  &:focus {
    transition: 0.5s ease;
    
    &::placeholder {
      opacity: 0;
    }
  }

  &::placeholder {
    color: black;
    font-size: 15px;
    font-weight: 600;
    padding-left: 5px;
  }
`;

export const InputFieldContainer = styled.div`
  width: 75%;
  position: relative;
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 10px;
  margin-bottom: 10px;

  svg {
    position: absolute;
    left: 4%;
    top: 30%;
    transform: translateX(-50%);
    fill: black;
  }
`;
