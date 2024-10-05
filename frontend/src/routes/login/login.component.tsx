import { ChangeEvent, FormEvent, useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate } from 'react-router';
import { ReactComponent as LockIcon } from '../../assets/icons/lock-icon.svg';
import { ReactComponent as PersonIcon } from '../../assets/icons/person-icon.svg';
import Backdrop, { BACKDROP_TYPES } from '../../components/backdrop/backdrop.component';
import { BUTTON_TYPE_CLASSES } from '../../components/button/button.component';
import FormInput from '../../components/form-input/form-input.component';
import LinkTag from '../../components/link-tag/link-tag.component';
import { googleSignInStart, signInStart } from '../../store/member/member.reducer';
import { selectIsMemberAuthenticated } from '../../store/member/member.selector';
import { 
  MainAuthContainer, 
  MainInputBox, 
  MainInputBoxBtnContainer, 
  MainInputBoxTitle, 
  MainInputGoogleBtn, 
  MainInputSignInBtn 
} from './login.styles';

const defaultFormFields = {
  email: '',
  password: ''
};

const Login = () => {
  const [formFields, setFormFields] = useState(defaultFormFields);
  const { email, password } = formFields;
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

  const signInWithGoogleHandler = async () => {
    dispatch(googleSignInStart());
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
    <MainAuthContainer>
      <Backdrop backdropType={BACKDROP_TYPES.dark} />
      <MainInputBox onSubmit={handleSubmit}>
        <MainInputBoxTitle>
          <span>Login</span>
          <span>
            Back To Your Account
          </span>
        </MainInputBoxTitle>

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

        <MainInputBoxBtnContainer>
          <MainInputSignInBtn 
            buttonType={BUTTON_TYPE_CLASSES.google}
            type='submit'
          >
            Sign In
          </MainInputSignInBtn>

          <MainInputGoogleBtn
            buttonType={BUTTON_TYPE_CLASSES.base}
            onClick={signInWithGoogleHandler}
          >
            Google Sign In
          </MainInputGoogleBtn>
        </MainInputBoxBtnContainer>

        <LinkTag to={'/register'} animate={true}>
          No account? Register now.
        </LinkTag>
      </MainInputBox>
    </MainAuthContainer>
  );
}

export default Login;
