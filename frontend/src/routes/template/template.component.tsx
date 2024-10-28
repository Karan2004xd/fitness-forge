import { ChangeEvent, FormEvent, useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import './template.styles.css';

import {
  createWorkoutStart,
  defaultFormFields,
  setFormFields,
} from '../../store/workout/workout.reducer';

import FormInput from '../../components/form-input/form-input.component';
import { EXERCISE_FILTER_TYPES } from '../../components/filter-options/filter-options.component';
import Button, { BUTTON_TYPE_CLASSES } from '../../components/button/button.component';
import WorkoutOptions from '../../components/workout-options/workout-options.component';
import { selectFormFields } from '../../store/workout/workout.selector';

export const WORKOUT_CONSTANTS = {
  days: [
    'Monday', 'Tuesday',
    'Wednesday', 'Thursday',
    'Friday', 'Saturday',
    'Sunday'
  ]
};

const Template = () => {
  const [formFieldsValues, setFormFieldsValues] = useState(defaultFormFields);

  const dispatch = useDispatch();
  const fields = useSelector(selectFormFields);

  const { days } = WORKOUT_CONSTANTS;
  const { category, equipment } = EXERCISE_FILTER_TYPES;

  useEffect(() => {
    if (fields) {
      setFormFieldsValues(fields);
    } else {
      setFormFieldsValues(defaultFormFields);
    }
  }, [fields]);

  const onSubmitHandler = (event: FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    dispatch(setFormFields({ formFields: defaultFormFields }));
    dispatch(createWorkoutStart({workout: formFieldsValues}));
  };

  const onChangeHandler = (event: ChangeEvent<HTMLInputElement & HTMLSelectElement>) => {
    const { name, value } = event.target;
    const newFormFieldsValue = { ...formFieldsValues, [name]: value };

    setFormFieldsValues(newFormFieldsValue);
    dispatch(setFormFields({ formFields: newFormFieldsValue }));
  }

  return (
    <div className="main-container">
      <form onSubmit={onSubmitHandler} className="main-container__form">

        <span>Workout Level</span>
        <select onChange={onChangeHandler} name='level'>
          <option value={''}>Choose a Level</option>
          <option value={'Beginner'}>Beginner</option>
          <option value={'Intermediate'}>Intermediate</option>
          <option value={'Expert'}>Expert</option>
        </select>

        <span>Workout Name</span>
        <FormInput 
          type='text'
          placeholder={"Name"}
          onChange={onChangeHandler}
          name='name'
          id='text-input'
        />

        <span>Workout Categories</span>
        <WorkoutOptions type='workoutCategories' data={category} />

        <span>Workout Days</span>
        <WorkoutOptions type='workoutDays' data={days} />

        <span>Enter Workout Duration (minutes)</span>
        <FormInput 
          type='number'
          placeholder='Duration'
          name='duration'
          onChange={onChangeHandler}
          id='text-input'
        />

        <span>Equipments</span>
        <WorkoutOptions type='equipments' data={equipment} />

        <span>Enter Rest Duration (seconds)</span>
        <FormInput 
          type='number'
          placeholder='Rest Duration'
          name='restDuration'
          onChange={onChangeHandler}
          id='text-input'
        />

        <span>Cardio Days</span>
        <WorkoutOptions type='cardioDays' data={days} />

        <span>Enter Cardio Duration (minutes)</span>
        <FormInput 
          type='number'
          placeholder='Cardio Duration'
          name='cardioDuration'
          onChange={onChangeHandler}
          id='text-input'
        />

        <Button type='submit' buttonType={BUTTON_TYPE_CLASSES.base}>
          Create Workout Template
        </Button>
      </form>
    </div>
  );
}

export default Template;
