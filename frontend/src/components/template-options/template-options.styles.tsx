import { styled } from "styled-components";

export const OptionLabel = styled.label`
  font-size: 18px;
  font-weight: 500;
  margin-top: 10px;

  input[type = "checkbox"] {
    transform: scale(1.5);
    margin-right: 8px;
  }
  
  &:hover,
  &:active {
    cursor: pointer;
    color: #E42E41;
    transition: 0.1s;
  }
`;

export const OptionContainer = styled.div`
  display: flex;
  flex-direction: column;
  width: 30%;
`;
