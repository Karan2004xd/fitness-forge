import { Exercise } from '../../store/exercise/exercise.types';
import './exercise-about-content.styles.css';

export type ExerciseAboutContentProps = {
  contentType: keyof Exercise;
  content: string | string[] | undefined | null;
};

const ExerciseAboutContent = ({ content, contentType }: ExerciseAboutContentProps) => {
  if (!content || content === null) {
    return null;
  }

  const formattedContentType = contentType.at(0)?.toUpperCase() + contentType.substring(1);

  let contentToDisplay = content;

  if (Array.isArray(content)) {
    if (contentType === 'instructions') {
      contentToDisplay = content.join(' ');
    } else {
      contentToDisplay = content.join(', ');
    }
  }

  return (
    <li className='content-item'>
      <span>{formattedContentType}: </span>
      {contentToDisplay}
    </li>
  );
}

export default ExerciseAboutContent;
