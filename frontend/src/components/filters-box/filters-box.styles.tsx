import { styled } from "styled-components";
import { ReactComponent as CloseIcon } from '../../assets/icons/close-icon.svg';

export const FilterBoxContentLabel = styled.summary`
  margin: 1rem;
  font-size: 20px;

  &:hover,
  &:active {
    cursor: pointer;
  }
`;

export const BoxCloseIcon = styled(CloseIcon)`
  fill: black;
  position: absolute;
  right: 0;
  margin-right: 15px;

  &:hover,
  &:active {
    cursor: pointer;
  }
`;

export const FilterBoxContainer = styled.div`
  display: flex;
  position: fixed;
  left: 40%;
  right: 50%;
  top: 15%;
  background: #FCEAEC;
  z-index: 5;
  width: 20%;
  flex-direction: column;
  max-height: 80vh;
  overflow-y: auto;
  padding: 1rem;
  border-radius: 5px;
  border: 2px solid #E42E41;
  margin-bottom: 3rem;

  h1 {
    color: #E42E41;
  }
`;
