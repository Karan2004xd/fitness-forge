import { styled } from "styled-components";

export const BaseButton = styled.button`
  display: flex;
  justify-content: flex-end;
  margin-right: 5rem;
  padding: 1rem 3.2rem;
  font-size: 1.125rem;
  font-weight: bold;
  background-color: #E42E41;
  border: none;
  color: #FCEAEC;
  border-radius: 16px;

  &:hover,
  &:active {
    background-color: #FCEAEC;
    color: #E42E41;
    transition: 0.2s ease-out;
    cursor: pointer;
  }
`;

export const GoogleButton = styled(BaseButton)`
  border: 2px solid #FCEAEC;
  background: transparent;
`;
