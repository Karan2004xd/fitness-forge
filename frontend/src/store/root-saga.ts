import { all, call } from "@redux-saga/core/effects"
import { memberSagas } from "./member/member.saga"

export function* rootSaga() {
  yield all([call(memberSagas)]);
}
