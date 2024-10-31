import { all, call } from "@redux-saga/core/effects"
import { exerciseSagas } from "./exercise/exercise.saga";
import { memberSagas } from "./member/member.saga"
import { workoutSagas } from "./workout/workout.saga";

export function* rootSaga() {
  yield all([
    call(memberSagas),
    call(exerciseSagas),
    call(workoutSagas)
  ]);
}
