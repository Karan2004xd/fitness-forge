import styled from "styled-components";

export const BackdropMain = styled.div`
  display: block;
  height: 100vh;
  width: 100vw;
  background-color: rgba(0, 0, 0, 0.7);
  z-index: 1;
  position: absolute;
`;

export const BackdropDark = styled(BackdropMain)`
  background-color: rgba(0, 0, 0, 0.85);
`;

export const BackdropDarker = styled(BackdropMain)`
  background-color: rgba(0, 0, 0, 0.90);
`;
