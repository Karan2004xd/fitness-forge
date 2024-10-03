import { ChangeEvent, useState } from 'react';
import { ReactComponent as LockIcon } from '../../assets/icons/lock-icon.svg';
import { ReactComponent as PersonIcon } from '../../assets/icons/person-icon.svg';
import Backdrop, { BACKDROP_TYPES } from '../../components/backdrop/backdrop.component';
import FormInput from '../../components/form-input/form-input.component';
import './login.styles.css';

const defaultFormFields = {
  email: '',
  password: ''
};

const Login = () => {
  const [formFields, setFormFields] = useState(defaultFormFields);
  const { email, password } = formFields;

  const onChangeHandler = (event: ChangeEvent<HTMLInputElement>) => {
    const { name, value } = event.target;
    setFormFields({...formFields, [name]: value});
  };

  const resetFormFields = () => {
    setFormFields(defaultFormFields);
  }

  return (
    <div className='auth-main-container'>
      <Backdrop backdropType={BACKDROP_TYPES.darker} />
      <form className='main-input-box'>
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
          Icon={LockIcon}
        />
      </form>
    </div>
  );
}

export default Login;
