import FilterOptions, { EXERCISE_FILTER_TYPES }  from '../filter-options/filter-options.component';
import { useDispatch, useSelector } from 'react-redux';
import { selectToggleFilterBox } from '../../store/exercise/exersice.selector';
import { setFilters, setToggleFilterBox } from '../../store/exercise/exercise.reducer';

import { 
  BoxCloseIcon, 
  ClearFilters, 
  FilterBoxContainer, 
  FilterBoxContentLabel 
} from './filters-box.styles';

const FiltersBox = () => {
  const toggleFilterBox = useSelector(selectToggleFilterBox);
  const dispatch = useDispatch();

  const handleFilterBoxClose = () => {
    dispatch(setToggleFilterBox({toggleFilterBox: !toggleFilterBox}));
  };

  const handleClearFilters = () => {
    dispatch(setFilters({filters: {}}));
    dispatch(setToggleFilterBox({toggleFilterBox: !toggleFilterBox}));
  };

  return (
    <FilterBoxContainer>
      <BoxCloseIcon onClick={handleFilterBoxClose} />
      <h1>Apply Filters</h1>
      {
        Object.keys(EXERCISE_FILTER_TYPES).map((filterType) => (
          <details key={filterType}>
            <FilterBoxContentLabel>
              {filterType.at(0)?.toUpperCase() + filterType.substring(1).toLowerCase()}
            </FilterBoxContentLabel>
            <FilterOptions filterType={filterType as keyof typeof EXERCISE_FILTER_TYPES} />
          </details>
        ))
      }
      <ClearFilters to={'#'} animate={true} onClick={handleClearFilters}>
        Clear Filters
      </ClearFilters>

    </FilterBoxContainer>
  );
};

export default FiltersBox;
