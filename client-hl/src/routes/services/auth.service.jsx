import axios from "axios";

export const login = async (userData) => {
  return axios
    .post("http://localhost:8081/users/auth/_login", userData)
    .then((response) => {
      localStorage.setItem('token', JSON.stringify(response.data.token).replace("\"", ""))
      console.log(response);
      return user(response.data);
    })
    .catch((error) => {
      console.log("Login Error", error.message);
    });
};

export const user = (request) => {
  return axios
    .get(
      `http://localhost:8081/users/user/get-full-user-context/${request.email}`,
      { headers: { Authorization: `Bearer ${request.token}` } }
    );
};

export const register = async (userData) => {
  return axios
    .post("http://localhost:8081/users/auth/register", userData)
    .then((response) => {
      localStorage.setItem('token', JSON.stringify(response.data.token).replace("\"", ""))
      return user(response.data);
    })
    .catch((error) => {
      console.log("Error", error.message);
    });
};