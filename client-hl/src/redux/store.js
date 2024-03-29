import { compose, legacy_createStore as createStore, applyMiddleware } from "redux";
import logger from "redux-logger";
import { rootReducer } from "./root-reducer";
import { persistStore, persistReducer } from "redux-persist";
import storage from "redux-persist/lib/storage";
import thunk from "redux-thunk";

const midlewares = [process.env.NODE_ENV !== 'production' && logger, thunk,].filter(Boolean);

const persistConfig = {
    key: "root",
    storage, 
    blacklist: []
}

const persistedReducer = persistReducer(persistConfig, rootReducer);

const composeEnhancer = (process.env.NODE_ENV !== 'production' && 
window && 
window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__) || 
compose;

const composedEnhancers = composeEnhancer(applyMiddleware(...midlewares));

export const store = createStore(persistedReducer, undefined, composedEnhancers);

export const persistor = persistStore(store);