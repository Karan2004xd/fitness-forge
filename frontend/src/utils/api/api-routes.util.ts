export const API_BASE_URL = "http://localhost:8080/";

export enum AUTHENTICATION {
  authencticateMember = 'auth'
};

export enum MEMBER_API_ROUTES {
  createMember = 'member/create',
  getMember = 'member/',
  getRefreshToken = 'member/auth_refresh/',
  getWorkouts = 'member/workouts/'
};

export enum EXERCISE_API_ROUTES {
  getTotalExercises = 'exercise/count',
  getExerciseByPage = 'exercise/page?',
  getExerciseById = 'exercise/',
  getImage = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises',
  getExerciseByPageWithFilter = 'exercise/page/filter?',
  getExerciseInfo = '/exercise/info'
};

export enum WORKOUT_API_ROUTES {
  createWorkout = 'workout/create?',
  deleteWorkout = 'workout/delete?',
  updateWorkout = 'workout/update'
};
