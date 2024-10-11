export type Exercise = {
  exerciseId?: number;
  id?: string;
  name?: string;
  force?: string | null;
  level?: string;
  mechanic?: string | null;
  equipment?: string | null;
  primaryMuscles?: string[];
  secondaryMuscles?: string[];
  instructions?: string[];
  category?: string;
  images?: string[];
};

export type ExerciseFilter = {
  category?: string;
  name?: string;
  level?: string;
  force?: string;
  mechanic?: string;
  equipment?: string;
};
