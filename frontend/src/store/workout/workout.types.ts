export interface Workout {
  id?: number;
  level: string;
  name: string;
  workoutCategories: string[];
  workoutDays: string[];
  duration: number;
  equipments: string[];
  restDuration: number;
  cardioDays: string[];
  cardioDuration: number;
  exerciseToExclude: string[];
};
