import { UnknownAction } from "redux";
import { UserData } from "../../utils/database/database.utils";

export type UserState = {
  readonly currentUser: UserData | null;
  readonly isLoading: boolean;
  readonly error: Error | null
};

const INITIAL_STATE = {
  currentUser: null,
  isLoading: false,
  error: null
};

export const memberReducer = (
  state = INITIAL_STATE,
  action = {} as UnknownAction
) => {

};
