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
  category?: boolean;
  name?: string;
  level?: boolean;
  force?: boolean;
  mechanic?: boolean;
  equipment?: boolean;
};

export const DEFAULT_SIZE = 20;
