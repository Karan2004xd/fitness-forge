import { FC, InputHTMLAttributes } from 'react';
import { InputContainer, InputFieldContainer } from './form-input.styles';

export type FormInputProps = {
  Icon?: React.FunctionComponent;
} & InputHTMLAttributes<HTMLInputElement>;

const FormInput: FC<FormInputProps> = ({ Icon, ...otherProps }) => {
  return (
    <InputFieldContainer>
      <InputContainer {...otherProps}/>
      {
        Icon && (<Icon />)
      }
    </InputFieldContainer>
  );
};

export default FormInput;
