import { ChangeEvent, FormEvent, useState } from 'react';
import { useDispatch } from 'react-redux';
import { Workout } from '../../store/workout/workout.types';
import './template.styles.css';

import {
  createWorkoutStart, deleteWorkoutStart, updateWorkoutStart
} from '../../store/workout/workout.reducer';

const defaultFormFields: Workout = {
  name: '',
  level: '',
  duration: 0,
  workoutCategories: [],
  workoutDays: [],
  restDuration: 0,
  cardioDays: [],
  cardioDuration: 0,
  equipments: [],
  exerciseToExclude: []
};

const Template = () => {
  const [formFields, setFormFields] = useState(defaultFormFields);
  const dispatch = useDispatch();

  const onSubmitHandler = (event: FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    dispatch(createWorkoutStart({workout: formFields}));
  };

  const deleteWorkout = (id: number) => {
    dispatch(deleteWorkoutStart({workoutId: id}));
  };

  const updateWorkout = () => {
    dispatch(updateWorkoutStart({workout: formFields}));
  }

  const onChangeHandler = (event: ChangeEvent<HTMLInputElement & HTMLSelectElement>) => {
    const { name, value, type, checked } = event.target;
    setFormFields((prevFields) => {
      const fieldValue = prevFields[name as keyof Workout];

      if (Array.isArray(fieldValue)) {
        let updatedArray;

        if (type === 'checkbox') {
          updatedArray = checked
            ? [...fieldValue, value]
            : fieldValue.filter((item) => item !== value);
        } else {
          updatedArray = [value];
        }
        return { ...prevFields, [name]: updatedArray};
      }

      return { ...prevFields, [name]: type === 'checkbox' ? checked : value };
    });
  }

  return (
    <div className="main-container">
      <form onSubmit={onSubmitHandler}>

        <select onChange={onChangeHandler} name='level'>
          <option value={undefined}/>
          <option value={'Beginner'}>Beginner</option>
          <option value={'Intermediate'}>Intermediate</option>
          <option value={'Expert'}>Expert</option>
        </select>

        <input 
          type='text'
          placeholder={"Name"}
          onChange={onChangeHandler}
          name='name'
        />

        <span>Workout Categories</span>
        <label>
          <input 
            type='checkbox' 
            onChange={onChangeHandler} 
            name='workoutCategories' 
            value={'Strength'}
          /> Strength
        </label>

        <span>Workout Days</span>
        <label>
          <input
            type='checkbox'
            onChange={onChangeHandler}
            name='workoutDays'
            value={'Monday'}
          /> Monday 
        </label>

        <input 
          type='number'
          placeholder='duration'
          name='duration'
          onChange={onChangeHandler}
        />

        <span>Equipments</span>
        <label>
          <input 
            type='checkbox'
            onChange={onChangeHandler}
            name='equipments'
            value={'None'}
          /> None 
        </label>

        <input 
          type='number'
          placeholder='rest duration'
          name='restDuration'
          onChange={onChangeHandler}
        />

        <span>Cardio Days</span>
        <label>
          <input
            type='checkbox'
            onChange={onChangeHandler}
            name='cardioDays'
            value={'Monday'}
          /> Monday 
        </label>

        <input 
          type='number'
          placeholder='cardio duration'
          name='cardioDuration'
          onChange={onChangeHandler}
        />

        <input type='submit' value={'Create Workout Template'} />
      </form>

      <button onClick={() => deleteWorkout(5)}>Delete Workout</button>
      <button onClick={updateWorkout}>Update Workout</button>
    </div>
  );
}

export default Template;
