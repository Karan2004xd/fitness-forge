import { Exercise } from '../../store/exercise/exercise.types';
import { ContentItem } from './exercise-about-content.styles';

export type ExerciseAboutContentProps = {
  contentType: keyof Exercise;
  content: string | string[] | undefined | null;
};

const ExerciseAboutContent = ({ content, contentType }: ExerciseAboutContentProps) => {
  if (!content || content === null) {
    return null;
  } else if (Array.isArray(content) && content.length === 0) {
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
    <ContentItem>
      <span>{formattedContentType}: </span>
      {contentToDisplay}
    </ContentItem>
  );
}

export default ExerciseAboutContent;
