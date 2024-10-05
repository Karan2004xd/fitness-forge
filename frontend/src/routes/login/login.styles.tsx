import { styled } from "styled-components";
import backgroundImage from '../../assets/images/authentication-page.jpg';
import Button from "../../components/button/button.component";

export const MainAuthContainer = styled.div`
  background-image: url(${backgroundImage});
  position: absolute;
  width: 100vw;
  height: 100vh;
  background-size: cover;
  background-position: top;
  z-index: 0;
`;

export const MainInputBox = styled.form`
  position: absolute;
  display: flex;
  right: 15%;
  top: 25%;
  border-left: 5px solid #E42E41;
  background-color: rgba(0, 0, 0, 0.60);
  z-index: 2;
  color: #FCEAEC;
  align-items: center;
  flex-direction: column;
  line-height: 1.5;
  margin-top: 3rem;

  a {
    margin-bottom: 2rem;
  }
`;

export const MainInputBoxTitle = styled.p`
  position: relative;
  font-size: 30px;
  margin-left: -1rem;
  margin-right: 3rem;

  span {
    &:first-child {
      display: block;
      margin-right: 15px;
      font-weight: bold;
      color: #E42E41;
    }
  }
`;

export const MainInputBoxBtnContainer = styled.div`
  display: flex;
  flex-direction: row;
  position: relative;
  margin-top: 1rem;
  margin-bottom: 2rem;
`;

export const MainInputSignInBtn = styled(Button)`
  position: relative;
  left: 12%;
`;

export const MainInputGoogleBtn = styled(Button)`
  position: relative;
  left: 4%;
`;

// .auth-main-container {
// }

// .main-input-box {
// }

// .main-input-box__title {
// }

// .main-input-box__title span:first-child {
// }

// .main-input-box-btn-container {
// }

// #main-input-box__sign-in-btn {
// }

// #main-input-box__google-sign-in-btn {
// }

// .main-input-box a {
// }
