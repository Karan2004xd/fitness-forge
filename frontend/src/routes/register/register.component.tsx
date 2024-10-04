import { ChangeEvent, FormEvent, useState } from "react";
import { ReactComponent as LockIcon } from '../../assets/icons/lock-icon.svg';
import { ReactComponent as PersonIcon } from '../../assets/icons/person-icon.svg';
import { ReactComponent as MailIcon } from '../../assets/icons/mail-icon.svg';
import Backdrop, { BACKDROP_TYPES } from "../../components/backdrop/backdrop.component";
import Button, { BUTTON_TYPE_CLASSES } from "../../components/button/button.component";
import FormInput from "../../components/form-input/form-input.component";

import './register.styles.css'
import { Member } from "../../store/member/member.types";
import { useDispatch } from "react-redux";
import { signUpStart } from "../../store/member/member.reducer";

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

  const onChangeHandler = (event: ChangeEvent<HTMLInputElement>) => {
    const { name, value } = event.target;
    setFormFields({...formFields, [name]: value});
  };

  const resetFormFields = () => {
    setFormFields(defaultFormFields);
  }

  const handleSubmit = (event: FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    const member: Member = {
      name: name,
      email: email,
      password: password,
      gender: 'male',
      height: 5.2,
      weight: 80,
      fitnessLevel: 'beginner',
    };

    dispatch(signUpStart({member: member}));
    resetFormFields();
  }

  return (
    <div className='auth-main-container'>
      <Backdrop backdropType={BACKDROP_TYPES.dark} />
      <form className='main-input-box' id="main-input-box__register" onSubmit={handleSubmit}>
        <p className='main-input-box__title main-input-box__title-register'>
          <span>Register</span>
          <span>
            Your New Account
          </span>
        </p>

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

        <div className='main-input-box-btn-container'>
          <Button 
            buttonType={BUTTON_TYPE_CLASSES.google}
            id='main-input-box__sign-in-btn'
            type='submit'
          >
            Sign Up 
          </Button>

          <Button 
            buttonType={BUTTON_TYPE_CLASSES.base}
            id='main-input-box__google-sign-in-btn'
          >
            Google Sign Up 
          </Button>
        </div>
      </form>
    </div>
  );
};

export default Register;
