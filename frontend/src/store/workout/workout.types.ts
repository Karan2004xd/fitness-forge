export interface Workout {
  id?: number;
  level: string;
  name: string;
  workoutCategory: string;
  workoutDays: string[];
  duration: number;
  equipments: string[];
  restDuration: number;
  cardioDays: string[];
  cardioDuration: number;
  exerciseToExclude: string[];
};
