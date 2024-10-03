import { FC, InputHTMLAttributes } from 'react';
import './form-input.styles.css';

export type FormInputProps = {
  Icon?: React.FunctionComponent;
} & InputHTMLAttributes<HTMLInputElement>;

const FormInput: FC<FormInputProps> = ({ Icon, ...otherProps }) => {
  return (
    <div className='input-field-container'>
      <input {...otherProps} />
      {
        Icon && (<Icon />)
      }
    </div>
  );
};

export default FormInput;
