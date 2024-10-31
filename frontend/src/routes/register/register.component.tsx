import { ChangeEvent, FormEvent, useEffect, useState } from "react";
import { ReactComponent as LockIcon } from '../../assets/icons/lock-icon.svg';
import { ReactComponent as PersonIcon } from '../../assets/icons/person-icon.svg';
import { ReactComponent as MailIcon } from '../../assets/icons/mail-icon.svg';
import Backdrop, { BACKDROP_TYPES } from "../../components/backdrop/backdrop.component";
import { BUTTON_TYPE_CLASSES } from "../../components/button/button.component";
import FormInput from "../../components/form-input/form-input.component";

import { Member } from "../../store/member/member.types";
import { useDispatch, useSelector } from "react-redux";
import { googleSignUpStart, signUpStart } from "../../store/member/member.reducer";
import { useNavigate } from "react-router";
import { selectIsMemberAuthenticated } from "../../store/member/member.selector";
import {
  MainAuthContainer,
  MainInputBoxBtnContainer,
  MainInputBoxTitle,
  MainInputGoogleBtn,
  MainInputSignInBtn 
} from "../login/login.styles";
import { MainRegisterInputBox } from "./register.styles";

const defaultFormFields = {
  email: '',
  name: '',
  password: '',
  confirmPassword: ''
};

const Register = () => {
  const [formFields, setFormFields] = useState(defaultFormFields);
  const { email, password, name, confirmPassword } = formFields;
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const isAuthenticated = useSelector(selectIsMemberAuthenticated);

  useEffect(() => {
    if (isAuthenticated) {
      navigate('/');
    }
  }, [navigate, isAuthenticated]);

  const onChangeHandler = (event: ChangeEvent<HTMLInputElement>) => {
    const { name, value } = event.target;
    setFormFields({...formFields, [name]: value});
  };

  const resetFormFields = () => {
    setFormFields(defaultFormFields);
  }

  const handleSubmit = (event: FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    if (password !== confirmPassword) {
      alert("The password and confirm password don't match");
      return;
    }

    const member: Member = {
      name: name,
      email: email,
      password: password,
    };

    dispatch(signUpStart({member: member}));
    resetFormFields();
  }

  const handleGoogleSignUp = () => {
    dispatch(googleSignUpStart());
  }

  return (
    <MainAuthContainer>
      <Backdrop backdropType={BACKDROP_TYPES.dark} />
      <MainRegisterInputBox onSubmit={handleSubmit}>
        <MainInputBoxTitle>
          <span>Register</span>
          <span>
            Your New Account
          </span>
        </MainInputBoxTitle>

        <FormInput
          type='text'
          required
          name='name'
          value={name}
          onChange={onChangeHandler}
          placeholder='Username'
          Icon={PersonIcon}
        />

        <FormInput
          type='email'
          required
          name='email'
          value={email}
          onChange={onChangeHandler}
          placeholder='E-mail'
          Icon={MailIcon}
        />

        <FormInput
          type='password'
          required
          name='password'
          value={password}
          onChange={onChangeHandler}
          placeholder='Password'
          Icon={LockIcon}
        />

        <FormInput
          type='password'
          required
          name='confirmPassword'
          value={confirmPassword}
          onChange={onChangeHandler}
          placeholder='Confirm Password'
          Icon={LockIcon}
        />

        <MainInputBoxBtnContainer>
          <MainInputSignInBtn 
            buttonType={BUTTON_TYPE_CLASSES.google}
            type='submit'
          >
            Sign Up 
          </MainInputSignInBtn>

          <MainInputGoogleBtn 
            buttonType={BUTTON_TYPE_CLASSES.base}
            onClick={handleGoogleSignUp}
          >
            Google Sign Up 
          </MainInputGoogleBtn>
        </MainInputBoxBtnContainer>
      </MainRegisterInputBox>
    </MainAuthContainer>
  );
};

export default Register;
