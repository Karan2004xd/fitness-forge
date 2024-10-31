import { ChangeEvent, FormEvent, useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';

import {
  createWorkoutStart,
  defaultFormFields,
  setCompleted,
  setFormFields,
  updateWorkoutStart,
} from '../../store/workout/workout.reducer';

import { EXERCISE_FILTER_TYPES } from '../../components/filter-options/filter-options.component';
import Button, { BUTTON_TYPE_CLASSES } from '../../components/button/button.component';
import { selectCompleted, selectFormFields } from '../../store/workout/workout.selector';
import { useNavigate } from 'react-router';
import { selectWorkouts } from '../../store/member/member.selector';
import TemplateOptions from '../../components/template-options/template-options.component';

import { Workout } from '../../store/workout/workout.types';
import { MainContainer, MainContainerForm, TextInput } from './template.styles';

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
  const navigate = useNavigate();

  const dispatch = useDispatch();
  const fields = useSelector(selectFormFields);
  const currentWorkouts = useSelector(selectWorkouts);

  const [disable, setDisable] = useState(false);
  const [edit, setEdit] = useState(false);

  const completed = useSelector(selectCompleted);

  const { days } = WORKOUT_CONSTANTS;
  const { category, equipment } = EXERCISE_FILTER_TYPES;

  const editWorkoutTemplate = () => {
    setEdit(true);
    setDisable(false);
  }

  const updateWorkoutTemplate = () => {
    dispatch(updateWorkoutStart({workout: formFieldsValues}));
    setEdit(false);
    setDisable(true);
  }

  useEffect(() => {
    if (fields) {
      setFormFieldsValues(fields);
    } else {
      setFormFieldsValues(defaultFormFields);
    }
  }, [fields]);

  useEffect(() => {
    if (!edit) {
      if (fields && fields.id && currentWorkouts) {
        if (currentWorkouts.indexOf(fields?.id) !== -1) {
          setDisable(true);
        } else {
          setDisable(false);
        }
      }
    }
  }, [])

  useEffect(() => {
    if (completed) {
      navigate('/workout/my_templates');
      dispatch(setCompleted({completed: false}));
    }
  }, [completed]);

  const onSubmitHandler = (event: FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    let acc: Partial<Workout> = {};

    Object.entries(formFieldsValues).forEach(([key, value]) => {
      if (Array.isArray(value)) {
        acc[key as keyof Workout] = value.map(item => item.toLowerCase()) as any;
      } else if (typeof value === 'string') {
        acc[key as keyof Workout] = value.toLowerCase() as any;
      } else {
        acc[key as keyof Workout] = value;
      }
    });

    const updatedWorkout: Workout = acc as Workout;

    dispatch(createWorkoutStart({workout: updatedWorkout}));
    dispatch(setFormFields({ formFields: defaultFormFields }));
  };

  const onChangeHandler = (event: ChangeEvent<HTMLInputElement & HTMLSelectElement>) => {
    const { name, value } = event.target;
    let newFormFieldsValue  = { ...formFieldsValues, [name]: value };

    setFormFieldsValues(newFormFieldsValue);
    dispatch(setFormFields({ formFields: newFormFieldsValue }));
  }

  return (
    <MainContainer>
      <MainContainerForm onSubmit={onSubmitHandler}>

        <span>Workout Level</span>
        <select onChange={onChangeHandler} name='level' required disabled={disable}>
          <option value={formFieldsValues.level}>{formFieldsValues.level}</option>
          <option value={'Beginner'}>Beginner</option>
          <option value={'Intermediate'}>Intermediate</option>
          <option value={'Expert'}>Expert</option>
        </select>

        <span>Workout Name</span>
        <TextInput 
          type='text'
          placeholder={"Name"}
          onChange={onChangeHandler}
          name='name'
          id='text-input'
          value={formFieldsValues.name}
          required
          disabled={disable}
        />

        <span>Workout Categories</span>
        <TemplateOptions 
          type='workoutCategory' 
          data={category}
          disabled={disable}
        />

        <span>Workout Days</span>
        <TemplateOptions 
          type='workoutDays' 
          data={days}
          disabled={disable}
        />

        <span>Enter Workout Duration (minutes)</span>
        <TextInput 
          type='number'
          placeholder='Duration'
          name='duration'
          onChange={onChangeHandler}
          id='text-input'
          value={formFieldsValues.duration}
          required
          disabled={disable}
        />

        <span>Equipments</span>
        <TemplateOptions 
          type='equipments' 
          data={equipment}
          disabled={disable}
        />

        <span>Enter Rest Duration (seconds)</span>
        <TextInput 
          type='number'
          placeholder='Rest Duration'
          name='restDuration'
          onChange={onChangeHandler}
          id='text-input'
          value={formFieldsValues.restDuration}
          required
          disabled={disable}
        />

        <span>Cardio Days</span>
        <TemplateOptions 
          type='cardioDays' 
          data={days}
          disabled={disable}
        />

        <span>Enter Cardio Duration (minutes)</span>
        <TextInput 
          type='number'
          placeholder='Cardio Duration'
          name='cardioDuration'
          onChange={onChangeHandler}
          id='text-input'
          value={formFieldsValues.cardioDuration}
          required
          disabled={disable}
        />

        {
          edit && (
            <Button 
              type='submit' 
              buttonType={BUTTON_TYPE_CLASSES.base}
              onClick={updateWorkoutTemplate}
            >
              Save Changes
            </Button>
          )
        }

        {
          (disable && !edit) && (
            <>
              <Button 
                type='submit' 
                buttonType={BUTTON_TYPE_CLASSES.base}
                onClick={editWorkoutTemplate}
              >
                Edit Workout Template
              </Button>
            </>
          )  
        }

        {
          (!disable && !edit) && (
            <Button type='submit' buttonType={BUTTON_TYPE_CLASSES.base}>
              Create Workout Template
            </Button>
          )
        }
      </MainContainerForm>
    </MainContainer>
  );
}

export default Template;
