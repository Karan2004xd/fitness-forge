import { Link } from "react-router-dom";
import { styled } from "styled-components";

export const BaseLinkTag = styled(Link)`
  text-decoration: none;
  color: #FCEAEC;
  position: relative;

  &:hover {
    cursor: pointer;
  }
`;

export const AnimatedLinkTag = styled(BaseLinkTag)`
  &:after {
    content: '';
    position: absolute;
    width: 100%;
    height: 2px;
    bottom: -2px;
    left: 0;
    background-color: #E42E41;
    transform-origin: left;
    transform: scaleX(0);
    transition: transform 0.3s ease-out;
  }

  &:hover {
    &::after {
      transform: scaleX(1);
    }
  }
`;
