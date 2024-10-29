import { put, select, call, takeLatest, all } from "@redux-saga/core/effects";
import { PayloadAction } from "@reduxjs/toolkit";
import { AxiosResponse } from "axios";
import { getRequestParamsUrl, makeRequest, REQUEST_TYPE } from "../../utils/api/api-calls.utils";
import { WORKOUT_API_ROUTES } from "../../utils/api/api-routes.util";
import { refreshCurrentMemberStart } from "../member/member.reducer";
import { selectCurrentMember } from "../member/member.selector";
import { selectLastWorkout } from "./workout.selector";
import { 
  createWorkoutFailed,
  createWorkoutStart,
  createWorkoutSuccess,
  deleteWorkoutFailed,
  deleteWorkoutStart,
  deleteWorkoutSuccess,
  fetchCurrentTemplatesFailed,
  fetchCurrentTemplatesStart,
  fetchCurrentTemplatesSuccess,
  updateWorkoutFailed,
  updateWorkoutStart,
  updateWorkoutSuccess
} from "./workout.reducer";
import { Workout } from "./workout.types";
import { Member } from "../member/member.types";

function* createWorkout(action: PayloadAction<{ workout: Workout }>) {
  try {
    const { id, accessToken } = yield select(selectCurrentMember);

    if (id && accessToken) {
      const map = new Map<string, number | string | string[]>();
      map.set('memberId', id);

      const url: string = getRequestParamsUrl(map, WORKOUT_API_ROUTES.createWorkout);
      const response: AxiosResponse = yield call(
        makeRequest, 
        url, 
        {
          type: REQUEST_TYPE.POST,
          data: action.payload.workout,
          accessToken: accessToken
        }
      );

      yield put(createWorkoutSuccess({ workout: response.data }));
      yield put(refreshCurrentMemberStart());
    }
  } catch (error: any) {
    yield put(createWorkoutFailed(error));
  }
};

function* deleteWorkout(action: PayloadAction<{ workoutId: number }>) {
  try {
    const { id, accessToken } = yield select(selectCurrentMember);

    if (id && accessToken) {
      const { workoutId } = action.payload;
      const map = new Map<string, number | string | string[]>();
      map.set('memberId', id);
      map.set('workoutId', workoutId);

      const url: string = getRequestParamsUrl(map, WORKOUT_API_ROUTES.deleteWorkout);
      yield call(makeRequest, url, {
        type: REQUEST_TYPE.DEL,
        accessToken: accessToken
      });

      yield put(deleteWorkoutSuccess({ workoutId: workoutId }));
      yield put(refreshCurrentMemberStart());
    }
  } catch (error: any) {
    yield put(deleteWorkoutFailed(error));
  }
}

function* updateWorkout(action: PayloadAction<{ workout: Workout }>) {
  try {
    const { accessToken } = yield select(selectCurrentMember);
    const { id } = yield select(selectLastWorkout);

    if (accessToken && id) {
      const workoutToSend: Workout = {
        ...action.payload.workout,
        id: id
      };

      yield call(makeRequest, WORKOUT_API_ROUTES.updateWorkout, {
        type: REQUEST_TYPE.PUT,
        accessToken: accessToken,
        data: workoutToSend
      });

      yield put(updateWorkoutSuccess({ workout: workoutToSend }));
    }
  } catch (error: any) {
    yield put(updateWorkoutFailed(error));
  }
}

function* fetchCurrentTemplates() {
  try {
    const { accessToken, workouts }: Member = yield select(selectCurrentMember);
    if (accessToken && workouts) {
      const resultWorkouts: Workout[] = []

      for (let i = 0; i < workouts.length; i++) {
        const url = `${WORKOUT_API_ROUTES.getWorkoutTemplate}${workouts.at(i)}`;

        const response: AxiosResponse = yield call(makeRequest, url, {
          type: REQUEST_TYPE.GET,
          accessToken: accessToken
        });

        resultWorkouts.push(response.data);
      }

      yield put(fetchCurrentTemplatesSuccess({ currentTemplates: resultWorkouts }));
    }
  } catch (error: any) {
    yield put(fetchCurrentTemplatesFailed(error.response.data));
  }
}

export function* onCreateWorkoutStart() {
  yield takeLatest(createWorkoutStart.type, createWorkout);
}

export function* onDeleteWorkoutStart() {
  yield takeLatest(deleteWorkoutStart.type, deleteWorkout);
}

export function* onUpdateWorkoutStart() {
  yield takeLatest(updateWorkoutStart.type, updateWorkout);
}

export function* onFetchCurrentTemplates() {
  yield takeLatest(fetchCurrentTemplatesStart.type, fetchCurrentTemplates);
}

export function* workoutSagas() {
  yield all([
    call(onCreateWorkoutStart),
    call(onDeleteWorkoutStart),
    call(onUpdateWorkoutStart),
    call(onFetchCurrentTemplates)
  ]);
};
