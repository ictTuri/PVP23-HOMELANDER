import { createAction, props } from "@ngrx/store";
import { LoginUser, Token } from "../../models/app.interface";

export const LOGIN = '[Auth] Login';
export const LOGIN_SUCCESS = '[Auth] Login success';
export const LOGIN_FAILURE = '[Auth] Login failure';
export const LOGOUT = '[Auth] Logout';

export const login = createAction(
    LOGIN,
    props<{ credentials: LoginUser }>()
);

export const loginSuccess = createAction(
    LOGIN_SUCCESS,
    props<{ user: Token }>()
);

export const loginFailure = createAction(
    LOGIN_FAILURE,
    props<{ error: string }>()
);

export const logout = createAction(
    LOGOUT
);