import { LinkProps } from 'react-router-dom';
import { AnimatedLinkTag, BaseLinkTag } from './link-tag.styles';

export type LinkTagProps = {
  children: React.ReactNode;
  animate: boolean;
} & LinkProps;

const LinkTag = ({ children, animate, ...otherProps }: LinkTagProps) => {
  return (
    !animate ? ( 
      <BaseLinkTag {...otherProps}>{children}</BaseLinkTag>
      ) :
      (
      <AnimatedLinkTag {...otherProps}>{children}</AnimatedLinkTag>
      )
  );
};

export default LinkTag;
