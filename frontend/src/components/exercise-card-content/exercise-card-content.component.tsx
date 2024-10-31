import { HTMLAttributes } from 'react';
import { CardContent } from './exercise-card-content.styles';

export type ExerciseCardContentProps = {
  content: string;
} & HTMLAttributes<HTMLSpanElement>;

const ExerciseCardContent = ({ content, ...otherProps}: ExerciseCardContentProps) => {
  return (
    <CardContent {...otherProps}>
      {content 
        ? content?.at(0)?.toUpperCase() + content?.substring(1).toLowerCase() 
        : ''
      }
    </CardContent>
  );
};

export default ExerciseCardContent;
