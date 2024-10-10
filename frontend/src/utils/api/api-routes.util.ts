export const API_BASE_URL = "http://localhost:8080/";

export enum AUTHENTICATION {
  authencticateMember = 'auth'
};

export enum MEMBER_API_ROUTES {
  createMember = 'member/create',
  getMember = 'member/',
  getRefreshToken = 'member/auth_refresh/'
};

export enum EXERCISE_API_ROUTES {
  getTotalExercises = 'exercise/count',
  getExerciseByPage = 'exercise',
  getExerciseById = 'exercise/',
  getImage = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises'
};
