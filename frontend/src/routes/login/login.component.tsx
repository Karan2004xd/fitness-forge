import { ChangeEvent, FormEvent, useState } from 'react';
import { useDispatch } from 'react-redux';
import { ReactComponent as LockIcon } from '../../assets/icons/lock-icon.svg';
import { ReactComponent as PersonIcon } from '../../assets/icons/person-icon.svg';
import Backdrop, { BACKDROP_TYPES } from '../../components/backdrop/backdrop.component';
import Button, { BUTTON_TYPE_CLASSES } from '../../components/button/button.component';
import FormInput from '../../components/form-input/form-input.component';
import LinkTag from '../../components/link-tag/link-tag.component';
import { googleSignUpStart, signInStart } from '../../store/member/member.reducer';
import { Member } from '../../store/member/member.types';
import './login.styles.css';

const defaultFormFields = {
  email: '',
  password: ''
};

const Login = () => {
  const [formFields, setFormFields] = useState(defaultFormFields);
  const { email, password } = formFields;
  const dispatch = useDispatch();

  const onChangeHandler = (event: ChangeEvent<HTMLInputElement>) => {
    const { name, value } = event.target;
    setFormFields({...formFields, [name]: value});
  };

  const signInWithGoogleHandler = async () => {
    const member: Member = {
      name: '',
      email: '',
      password: '',
      gender: 'male',
      height: 5.2,
      weight: 80,
      fitnessLevel: 'beginner',
    };

    dispatch(googleSignUpStart({member: member}))
  }

  const resetFormFields = () => {
    setFormFields(defaultFormFields);
  }

  const handleSubmit = (event: FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    dispatch(signInStart({ member: { email: email, password: password }}));
    resetFormFields();
  }

  return (
    <div className='auth-main-container'>
      <Backdrop backdropType={BACKDROP_TYPES.dark} />
      <form className='main-input-box' onSubmit={handleSubmit}>
        <p className='main-input-box__title'>
          <span>Login</span>
          <span>
            Back To Your Account
          </span>
        </p>

        <FormInput
          type='email'
          required
          name='email'
          value={email}
          onChange={onChangeHandler}
          placeholder='E-mail'
          Icon={PersonIcon}
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

        <div className='main-input-box-btn-container'>
          <Button 
            buttonType={BUTTON_TYPE_CLASSES.google}
            id='main-input-box__sign-in-btn'
            type='submit'
          >
            Sign In
          </Button>

          <Button 
            buttonType={BUTTON_TYPE_CLASSES.base}
            id='main-input-box__google-sign-in-btn'
            onClick={signInWithGoogleHandler}
          >
            Google Sign In
          </Button>
        </div>

        <LinkTag to={'/register'}>
          No account? Register now.
        </LinkTag>
      </form>
    </div>
  );
}

export default Login;
