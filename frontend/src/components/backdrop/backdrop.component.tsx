import { FC } from 'react';
import { BackdropDark, BackdropDarker, BackdropMain } from './backdrop.styles';

export enum BACKDROP_TYPES {
  normal = 'normal',
  dark = 'dark',
  darker = 'darker'
};

export type BackdropProps = {
  backdropType: BACKDROP_TYPES;
};

const getBackdrop = (backdropType = BACKDROP_TYPES.normal) => (
  {
    [BACKDROP_TYPES.normal]: BackdropMain,
    [BACKDROP_TYPES.dark]: BackdropDark,
    [BACKDROP_TYPES.darker]: BackdropDarker
  }[backdropType]
);

const Backdrop: FC<BackdropProps> = ({ backdropType }) => {
  const CustomBackdrop = getBackdrop(backdropType);

  return (
    <CustomBackdrop/>
  );
};

export default Backdrop;
