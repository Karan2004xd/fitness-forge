import { HTMLAttributes } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { setFilters } from '../../store/exercise/exercise.reducer';
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

  const checkIfOptionExists = (option: string) => {
    if (existingFilters) {
      const filterArray = existingFilters[filterType];

      if (Array.isArray(filterArray)) {
        if (filterArray.indexOf(option) !== -1) {
          return true;
        }
      } else {
        return false;
      }
    }
    return false;
  };

  const checkboxClickHandler = (option: string) => {
    let newFilter = { ...existingFilters };

    let existingFilterOptions = newFilter[filterType] || [];

    if (!Array.isArray(existingFilterOptions)) {
      existingFilterOptions = [];
    }

    if (!checkIfOptionExists(option)) {
      existingFilterOptions = [...existingFilterOptions, option];
    } else {
      existingFilterOptions = existingFilterOptions.filter(
        (item) => item !== option
      );
    }
    newFilter[filterType] = existingFilterOptions.length > 0 ? existingFilterOptions : undefined;
    dispatch(setFilters({filters: newFilter}));
  };

  return (
    <div className='filter-options-container' {...otherProps}>
      {
        options.map((option, index) => (
          <label key={index}>
            <input 
              type='checkbox'
              checked={checkIfOptionExists(option.toLowerCase())}
              onChange={() => checkboxClickHandler(option.toLowerCase())}
            /> {option}
          </label>
        ))
      }
    </div>
  );
};

export default FilterOptions;
