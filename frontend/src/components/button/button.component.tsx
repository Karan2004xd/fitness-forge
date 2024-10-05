import { ButtonHTMLAttributes } from 'react';
import { BaseButton, GoogleButton } from './button.styles';

export enum BUTTON_TYPE_CLASSES {
  base = 'base',
  google = 'google'
};

const getButton = (buttonType = BUTTON_TYPE_CLASSES.base) => (
  {
    [BUTTON_TYPE_CLASSES.base]: BaseButton,
    [BUTTON_TYPE_CLASSES.google]: GoogleButton 
  }[buttonType]
);

export type ButtonProps = {
  buttonType: BUTTON_TYPE_CLASSES;
} & ButtonHTMLAttributes<HTMLButtonElement>;

const Button = ({ buttonType, children, ...otherProps}: ButtonProps) => {
  const CustomButton = getButton(buttonType);

  return (
    <CustomButton {...otherProps}>
      {children}
    </CustomButton>
  );
}

export default Button;
