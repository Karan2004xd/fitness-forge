import { ButtonHTMLAttributes } from 'react';
import './button.styles.css'

export enum BUTTON_TYPE_CLASSES {
  base = 'base-btn',
  google = 'google-btn'
};

export type ButtonProps = {
  buttonType: BUTTON_TYPE_CLASSES;
} & ButtonHTMLAttributes<HTMLButtonElement>;

const getButtonClass = (buttonType: BUTTON_TYPE_CLASSES): string => {
  let buttonClass;

  switch (buttonType) {
    case BUTTON_TYPE_CLASSES.base:
      buttonClass = BUTTON_TYPE_CLASSES.base;
      break;
    case BUTTON_TYPE_CLASSES.google:
      buttonClass = BUTTON_TYPE_CLASSES.google;
      break;
    default:
      buttonClass = BUTTON_TYPE_CLASSES.base;
      break;
  };
  return buttonClass;
};

const Button = ({ buttonType, children, ...otherProps}: ButtonProps) => {
  const buttonClass = getButtonClass(buttonType);

  return (
    <button {...otherProps} className={buttonClass}>
      {children}
    </button>
  );
}

export default Button;
