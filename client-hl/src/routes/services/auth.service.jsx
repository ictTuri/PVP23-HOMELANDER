import axios from "axios";

export const login = async (userData) => {
  return axios
    .post("http://localhost:8081/users/auth/_login", userData)
    .then((response) => {
      return user(response.data);
    })
    .catch((error) => {
      console.log("Error", error.message);
    });
};

export const user = (request) => {
  return axios
    .get(
      `http://localhost:8081/users/user/get-full-user-context/${request.username}`,
      { headers: { Authorization: `Bearer ${request.token}` } }
    );
};

export const register = () => {};

export const logout = () => {};
