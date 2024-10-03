import { Link, LinkProps } from 'react-router-dom';
import './link-tag.styles.css';

export type LinkTagProps = {
  children: React.ReactNode;
  animate?: boolean;
} & LinkProps;

const LinkTag = ({ children, animate, ...otherProps }: LinkTagProps) => {
  if (animate === undefined) {
    animate = true;
  }
  return (
    <Link 
      {...otherProps}
      className={
        `link-tag ${animate ? 'link-tag-animated' : ''}`
      }
    >
      {children}
    </Link>
  );
};

export default LinkTag;
