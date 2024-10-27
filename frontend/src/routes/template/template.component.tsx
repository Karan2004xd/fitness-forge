import { ChangeEvent, FormEvent, useState } from 'react';
import { Workout } from '../../store/workout/workout.types';
import './template.styles.css';

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

  const onSubmitHandler = (event: FormEvent<HTMLFormElement>) => {
    event.preventDefault();
  };

  const onChangeHandler = (event: ChangeEvent<HTMLInputElement>) => {
    const { name, value } = event.target;
    console.log(name, value);
    setFormFields({...formFields, [name]: value});
  }

  return (
    <div className="main-container">
      <form onSubmit={onSubmitHandler}>

        <select>
          <option>Choose a level</option>
          <option>Beginner</option>
          <option>Intermediate</option>
          <option>Expert</option>
        </select>

        <input type='text' placeholder={"Name"} onChange={onChangeHandler}/>

        <span>Workout Categories</span>
        <label>
          <input type='checkbox' onChange={onChangeHandler} name='strength' value={'Strength'}/> Strength
        </label>

        <label>
          <input type='checkbox' onChange={onChangeHandler}/> Powerlifting
        </label>

        <label>
          <input type='checkbox'/> Strongman 
        </label>

        <label>
          <input type='checkbox'/> Olympic WeightLifting 
        </label>

        <label>
          <input type='checkbox'/> Polymetrics
        </label>

        <label>
          <input type='checkbox'/> Stretching
        </label>

        <span>Workout Days</span>
        <label>
          <input type='checkbox'/> Sunday 
        </label>

        <label>
          <input type='checkbox'/> Monday 
        </label>

        <label>
          <input type='checkbox'/> Tuesday 
        </label>

        <label>
          <input type='checkbox'/> Wednesday
        </label>

        <label>
          <input type='checkbox'/> Thursday 
        </label>

        <label>
          <input type='checkbox'/> Friday 
        </label>

        <label>
          <input type='checkbox'/> Saturday 
        </label>

        <input type='number' placeholder='duration'/>

        <span>Equipments</span>
        <label>
          <input type='checkbox'/> Medicine ball
        </label>
        <label>
          <input type='checkbox'/> Dumbbell
        </label>
        <label>
          <input type='checkbox'/> Body only 
        </label>
        <label>
          <input type='checkbox'/> Olympic WeightLifting 
        </label>
        <label>
          <input type='checkbox'/> Kettlebells
        </label>
        <label>
          <input type='checkbox'/> Foam roll
        </label>
        <label>
          <input type='checkbox'/> Cable 
        </label>
        <label>
          <input type='checkbox'/> Machine
        </label>
        <label>
          <input type='checkbox'/> Barbell
        </label>
        <label>
          <input type='checkbox'/> Exercise ball
        </label>
        <label>
          <input type='checkbox'/> E-Z curl bar
        </label>
        <label>
          <input type='checkbox'/> Other
        </label>

        <input type='number' placeholder='rest duration'/>

        <span>Cardio Days</span>
        <label>
          <input type='checkbox'/> Sunday 
        </label>

        <label>
          <input type='checkbox'/> Monday 
        </label>

        <label>
          <input type='checkbox'/> Tuesday 
        </label>

        <label>
          <input type='checkbox'/> Wednesday
        </label>

        <label>
          <input type='checkbox'/> Thursday 
        </label>

        <label>
          <input type='checkbox'/> Friday 
        </label>

        <label>
          <input type='checkbox'/> Saturday 
        </label>

        <input type='number' placeholder='cardio duration'/>

        <input type='text' placeholder='exercise to exclude'/>

        <input type='submit' value={'Create Workout Template'} />
      </form>
    </div>
  );
}

export default Template;
