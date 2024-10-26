import './template.styles.css';

const Template = () => {
  return (
    <div className="main-container">
      <form>

        <select>
          <option>Choose a level</option>
          <option>Beginner</option>
          <option>Intermediate</option>
          <option>Expert</option>
        </select>

        <input type='text' placeholder={"Name"}/>

        <label>
          <input type='checkbox'/> Strength
        </label>

        <label>
          <input type='checkbox'/> Powerlifting
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

      </form>
    </div>
  );
}

export default Template;
