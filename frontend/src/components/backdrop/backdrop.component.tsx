import { FC } from 'react';
import './backdrop.styles.css';

export enum BACKDROP_TYPES {
  normal = 'main-container-backdrop',
  dark = 'main-container-backdrop__dark',
  darker = 'main-container-backdrop__darker',
};

export type BackdropProps = {
  backdropType: BACKDROP_TYPES;
};

const getBackdropType = (backdropType: BACKDROP_TYPES): string => {
  let backdrop;
  switch (backdropType) {
    case BACKDROP_TYPES.normal:
      backdrop = BACKDROP_TYPES.normal;
      break;
    case BACKDROP_TYPES.dark:
      backdrop = BACKDROP_TYPES.dark;
      break;
    case BACKDROP_TYPES.darker:
      backdrop = BACKDROP_TYPES.darker;
      break;
    default:
      backdrop = BACKDROP_TYPES.normal;
      break;
  };

  return backdrop;
};

const Backdrop: FC<BackdropProps> = ({ backdropType }) => {
  const backdrop = getBackdropType(backdropType);

  return (
    <div className={backdrop}>
    </div>
  );
};

export default Backdrop;
