import FilterOptions from '../filter-options/filter-options.component';
import './filters-box.styles.css'

const FiltersBox = () => {
  return (
    <div className='filters-menu-container'>
      <FilterOptions filterType='level' />
    </div>
  );
};

export default FiltersBox;
