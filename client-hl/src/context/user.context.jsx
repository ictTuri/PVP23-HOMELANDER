import { createContext, useEffect, useState } from "react";

export const UserContext = createContext({
  currentUser: null,
  setCurrentUser: () => null,
});

export const UserProvider = ({ children }) => {
  const [currentUser, setCurrentUser] = useState(getInitialState());
  const value = { currentUser, setCurrentUser };

  useEffect(() => {
    localStorage.setItem('currentUser', JSON.stringify(currentUser))
  }, [currentUser])

  return <UserContext.Provider value={value}>{children}</UserContext.Provider>;
};

function getInitialState() {
  const user = localStorage.getItem('currentUser')
  return user ? JSON.parse(user) : []
};
