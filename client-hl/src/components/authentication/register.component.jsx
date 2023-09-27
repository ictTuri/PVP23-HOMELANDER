import { Link } from "react-router-dom";
import ContactSupport from "../info-tabs/contact-support.component";
import Cover from "./cover.component";
import { register } from "../../routes/services/auth.service";
import { useRef, useState } from "react";
import { useDispatch } from "react-redux";
import { setCurrentUser } from "../../redux/user/user.action";

const defaultFormFields = {
  username: "",
  email: "",
  password: "",
};

export default function Register() {

  const dispatch = useDispatch();

  const repeat_password = useRef(null);

  const [data, setData] = useState({
    username: "",
    email: "",
    password: "",
  });

  const handleChange = (e) => {
    const value = e.target.value;
    setData({
      ...data,
      [e.target.name]: value,
    });
  };

  const resetFormFields = () => {
    setData(defaultFormFields);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const userData = {
      username: data.username,
      email: data.email,
      password: data.password,
    };

    try {
      register(userData).then((response) => dispatch(setCurrentUser(response.data)));
      resetFormFields();
      repeat_password.current.value = "";
    } catch (error) {
      console.log(error.code);
    }
  };

  return (
    <div className="w-full h-screen flex items-start">
      <Cover />
      <div className="w-1/2 h-full bg-[#F5F5F5] flex flex-col p-20 justify-between items-center">
        <h1 className="w-full max-w-[500px] mx-auto text-x1 text-[#060606] font-semibold mr-auto">
          HOME-LANDER
        </h1>
        <form
          onSubmit={handleSubmit}
          className="w-full flex flex-col max-w-[500px]"
        >
          <div className="w-full flex flex-col mb-2">
            <h3 className="text-3xl font-semibold mb-2">Sign up</h3>
            <p className="text-base mb-2">
              Welcome! Please enter your details.
            </p>
          </div>
          <div className="w-full flex flex-col mt-4">
            <div className="relative z-0 w-full mb-6 group">
              <input
                value={data.username}
                onChange={handleChange}
                name="username"
                type="text"
                placeholder=" "
                required
                className="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-blue-500 focus:outline-none focus:ring-0 focus:border-blue-600 peer"
              />
              <label
                htmlFor="username"
                className="peer-focus:font-medium absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-8 scale-75 top-3 -z-10 origin-[0] peer-focus:left-0 peer-focus:text-blue-600 peer-focus:dark:text-blue-500 peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-8"
              >
                Username
              </label>
            </div>
            <div className="relative z-0 w-full mb-6 group">
              <input
                value={data.email}
                onChange={handleChange}
                name="email"
                type="email"
                placeholder=" "
                required
                className="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-blue-500 focus:outline-none focus:ring-0 focus:border-blue-600 peer"
              />
              <label
                htmlFor="email"
                className="peer-focus:font-medium absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-8 scale-75 top-3 -z-10 origin-[0] peer-focus:left-0 peer-focus:text-blue-600 peer-focus:dark:text-blue-500 peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-8"
              >
                Email address
              </label>
            </div>
            <div className="relative z-0 w-full mb-6 group">
              <input
                value={data.password}
                onChange={handleChange}
                name="password"
                type="password"
                placeholder=" "
                required
                className="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-blue-500 focus:outline-none focus:ring-0 focus:border-blue-600 peer"
              />
              <label
                htmlFor="password"
                className="peer-focus:font-medium absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-8 scale-75 top-3 -z-10 origin-[0] peer-focus:left-0 peer-focus:text-blue-600 peer-focus:dark:text-blue-500 peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-8"
              >
                Password
              </label>
            </div>
            <div className="relative z-0 w-full mb-6 group">
              <input
                ref={repeat_password}
                type="password"
                name="repeat_password"
                id="repeat_password"
                className="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-blue-500 focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                placeholder=" "
                required
              />
              <label
                htmlFor="repeat_password"
                className="peer-focus:font-medium absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:left-0 peer-focus:text-blue-600 peer-focus:dark:text-blue-500 peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6"
              >
                Confirm password
              </label>
            </div>
          </div>

          <div className="w-full flex flex-col my-4">
            <button
              type="submit"
              className="w-full my-4 cursor-pointer text-[#060606] font-semibold bg-white border-2 border-black rounded-md p-4 text-center flex items-center justify-center"
            >
              Sign Up
            </button>

            <div className="w-full flex items-center justify-center">
              <div className="w-full h-[1px] bg-black"></div>
              <p className="text-lg absolute text-[#000000]/80 bg-[#F5F5F5] ">
                or
              </p>
            </div>
            <Link to="/login">
              <button className="w-full my-4 cursor-pointer text-white font-semibold bg-[#060606] rounded-md p-4 text-center flex items-center justify-center">
                Log in
              </button>
            </Link>
          </div>
        </form>
        <ContactSupport />
      </div>
    </div>
  );
}
