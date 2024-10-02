import './App.css';
import { 
  BrowserRouter as Router,
  Routes,
  Route 
} from 'react-router-dom';
import Home from './routes/home/home.component';
import Login from './routes/login/login.component';
import Register from './routes/register/register.component';
import Exercises from './routes/exercises/exercises.component';
import About from './routes/about/about.component';

const App = () => {
  return (
    <Router>
      <Routes>
        <Route path='/' element={<Home />} />
        <Route path='/login' element={<Login />} />
        <Route path='/register' element={<Register />} />
        <Route path='/exercises' element={<Exercises />} />
        <Route path='/about' element={<About />} />
      </Routes>
    </Router>
  );
}

export default App;
