import { LabelHTMLAttributes } from 'react';
import './filters.styles.css';

export type FiltersProps = {
  content: string;
  checked: boolean;
} & LabelHTMLAttributes<HTMLLabelElement>; 

const Filters = ({ content, checked, ...otherProps }: FiltersProps) => {
  return (
    <label {...otherProps}>
      <input type='checkbox' defaultChecked={checked}/> {content}
    </label>
  );
};

export default Filters;
