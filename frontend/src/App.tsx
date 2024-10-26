import { 
  Routes,
  Route 
} from 'react-router-dom';
import Home from './routes/home/home.component';
import Login from './routes/login/login.component';
import Register from './routes/register/register.component';
import Exercises from './routes/exercises/exercises.component';
import Navigation from './routes/navigation/navigation.component';
import ExerciseInfo from './routes/exercise-info/exercise-info.commponent';
import Workout from './routes/workout/workout.component';
import Template from './routes/template/template.component';

const App = () => {
  return (
    <Routes>
      <Route path='/' element={<Navigation />}>
        <Route index element={<Home />} />
        <Route path='/login' element={<Login />} />
        <Route path='/register' element={<Register />} />
        <Route path='/exercises' element={<Exercises />} />
        <Route path='/workout' element={<Workout />} />
        <Route path='/workout/template' element={<Template />} />
        <Route path='/exercise/info/:exerciseName' element={<ExerciseInfo />} />
      </Route>
    </Routes>
  );
}

export default App;
