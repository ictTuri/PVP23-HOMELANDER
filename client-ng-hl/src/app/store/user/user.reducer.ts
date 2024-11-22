import { createReducer, on } from "@ngrx/store";
import { login, loginFailure, loginSuccess } from "./user.actions";
import { Token } from "../../models/app.interface";

export interface TokenState {
    user: Token;
    error: string;
    isLoading: boolean;
}

const initialState: TokenState = {
    user: null,
    error: null,
    isLoading: false
};

export const userReducer = createReducer(
    initialState,
    on(login, state => ({ ...state, isLoading: true })),
    on(loginSuccess, (state, { user }) => ({ ...state, user, isLoading: false })),
    on(loginFailure, (state, { error }) => ({ ...state, error, isLoading: false }))
);

