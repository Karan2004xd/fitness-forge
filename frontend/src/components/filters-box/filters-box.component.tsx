import FilterOptions, { EXERCISE_FILTER_TYPES }  from '../filter-options/filter-options.component';
import './filters-box.styles.css'

const FiltersBox = () => {
  return (
    <div className='filters-box-container'>
      <h1>Apply Filters</h1>
      {
        Object.keys(EXERCISE_FILTER_TYPES).map((filterType) => (
          <details key={filterType}>
            <summary>{filterType.at(0)?.toUpperCase() + filterType.substring(1).toLowerCase()}</summary>
            <FilterOptions filterType={filterType as keyof typeof EXERCISE_FILTER_TYPES} />
          </details>
        ))
      }
    </div>
  );
};

export default FiltersBox;
