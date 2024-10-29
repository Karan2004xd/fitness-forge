import { HTMLAttributes, useEffect, useState } from "react";
import { Workout } from "../../store/workout/workout.types";
import { ReactComponent as DeleteForever } from '../../assets/icons/delete-forever.svg';
import { ReactComponent as ApplyOn } from '../../assets/icons/apply-on.svg';
import { ReactComponent as ApplyOff } from '../../assets/icons/apply-off.svg';
import './template-details.styles.css';
import { useDispatch, useSelector } from "react-redux";
import { deleteWorkoutStart, setCurrentWorkout } from "../../store/workout/workout.reducer";
import { selectCurrentWorkout } from "../../store/workout/workout.selector";

export type TemplateDetailsProps = {
  template: Workout;
} & HTMLAttributes<HTMLDivElement>;

const TemplateDetails = ({ template, ...otherProps }: TemplateDetailsProps) => {
  const { id, name } = template;
  const [apply, setApply] = useState(false);

  const dispatch = useDispatch();
  const currentWorkout = useSelector(selectCurrentWorkout);

  const deleteWorkoutTemplate = () => {
    if (id) {
      dispatch(deleteWorkoutStart({workoutId: id}));
    }
  };

  const iconClickHandler = () => {
    if (apply) {
      dispatch(setCurrentWorkout({ workout: null }));
    } else {
      dispatch(setCurrentWorkout({ workout: template }));
    }
  };

  useEffect(() => {
    if (currentWorkout && currentWorkout.id == id) {
      setApply(true);
    } else {
      setApply(false);
    }
  }, [currentWorkout]);

  return (
    <div className="details-container" {...otherProps}>
      <span>{id}</span>
      <span>{name}</span>
      {
        apply ? 
          <ApplyOn onClick={iconClickHandler} />
          : <ApplyOff onClick={iconClickHandler} />
      }
      <DeleteForever onClick={deleteWorkoutTemplate} />
    </div>
  );
};

export default TemplateDetails;
