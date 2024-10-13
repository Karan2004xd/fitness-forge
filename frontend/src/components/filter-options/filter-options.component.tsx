import { HTMLAttributes, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { setFiltersStart } from '../../store/exercise/exercise.reducer';
import { selectFilters } from '../../store/exercise/exersice.selector';
import './filter-options.styles.css';

export const EXERCISE_FILTER_TYPES = {
  level: [
    "Beginner",
    "Intermediate",
    "Expert"
  ],

  category: [
    "Powerlifting",
    "Strength",
    "Stretching",
    "Cardio",
    "Olympic weightlifting",
    "Strongman",
    "Plyometrics"
  ],

  force: [
    "Static",
    "Pull",
    "Push"
  ],

  mechanic: [
    "Isolation",
    "Compound",
  ],

  equipment: [
    "Medicine ball",
    "Dumbbell",
    "Body only",
    "Bands",
    "Kettlebells",
    "Foam roll",
    "Cable",
    "Machine",
    "Barbell",
    "Exercise ball",
    "E-Z curl bar",
    "Other"
  ]
};

type FilterOptionsProps = {
  filterType: keyof typeof EXERCISE_FILTER_TYPES;
} & HTMLAttributes<HTMLDivElement>;

const FilterOptions = ({ filterType, ...otherProps }: FilterOptionsProps) => {
  const options = EXERCISE_FILTER_TYPES[filterType];
  const dispatch = useDispatch();
  const existingFilters = useSelector(selectFilters);

  const [isChecked, setIsChecked] = useState(false);

  const checkboxClickHandler = (option: string) => {
    let newFilter = {
      ...existingFilters
    };

    if (!isChecked) {
      newFilter = {
        [filterType]: option.toLowerCase()
      };
    } else {
      newFilter = {
        [filterType]: undefined
      };
    }
    dispatch(setFiltersStart({filters: newFilter}));
    setIsChecked(!isChecked);
  };

  return (
    <div className='filter-options-container' {...otherProps}>
      <span>{filterType}</span>
      {
        options.map((option, index) => (
          <label key={index}>
            <input 
              type='checkbox'
              defaultChecked={existingFilters ? existingFilters[filterType] === undefined : false}
              onChange={() => checkboxClickHandler(option)}
            /> {option}
          </label>
        ))
      }
    </div>
  );
};

export default FilterOptions;
