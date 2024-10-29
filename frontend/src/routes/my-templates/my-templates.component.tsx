import { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate } from 'react-router';
import Button, { BUTTON_TYPE_CLASSES } from '../../components/button/button.component';
import TemplateDetails from '../../components/template-details/template-details.component';
import { selectWorkouts } from '../../store/member/member.selector';
import { fetchCurrentTemplatesStart, setFormFields } from '../../store/workout/workout.reducer';
import { selectCurrentTemplates } from '../../store/workout/workout.selector';
import { Workout } from '../../store/workout/workout.types';
import './my-templates.styles.css';

const MyTemplates = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const currentTemplates = useSelector(selectCurrentTemplates);
  const workouts = useSelector(selectWorkouts);

  useEffect(() => {
    dispatch(fetchCurrentTemplatesStart());
  }, [workouts]);

  const templateDetailsClick = (template: Workout) => {
    dispatch(setFormFields({formFields: template}))
    navigate('/workout/template')
  };

  const addNewTemplate = () => {
    dispatch(setFormFields({formFields: null}))
    navigate('/workout/template');
  }

  return (
    <div className='mytemplates-container'>
      <h1>My Templates</h1>
      <Button buttonType={BUTTON_TYPE_CLASSES.base} onClick={addNewTemplate}>Add new Template</Button>
      <div className='mytemplates-container__title'>
        <span>ID</span>
        <span>Name</span>
        <span>Applied</span>
        <span>Delete</span>
      </div>
      {
        currentTemplates.length ? (
        currentTemplates.map((template, index) => (
          <TemplateDetails 
            template={template}
            key={index}
            onDoubleClick={() => templateDetailsClick(template)}
          />
        ))
        ) : (
          <h1>No Templates created yet!</h1>
        )
      }
    </div>
  );
};

export default MyTemplates;
