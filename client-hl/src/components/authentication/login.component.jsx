import { useState } from "react";
import { useDispatch } from "react-redux";
import { login } from "../../routes/services/auth.service";
import { Link } from "react-router-dom";
import Cover from "./cover.component";
import ContactSupport from "../info-tabs/contact-support.component";
import { setCurrentUser } from "../../redux/user/user.action";

const defaultFormFields = {
  credential: "",
  password: ""
};

export default function Login() {
  const dispatch = useDispatch();

  const [data, setData] = useState({
    credential: "",
    password: ""
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
      credential: data.credential,
      password: data.password,
    };

    try {
      login(userData).then((response) => dispatch(setCurrentUser(response.data)));
      resetFormFields();
    } catch (error) {
      console.log(error.code);
    }
  };

  return (
    <div className="w-full h-screen flex items-start">
      <div className="w-1/2 h-full bg-[#F5F5F5] flex flex-col p-20 justify-between items-center">
        <h1 className="w-full max-w-[500px] mx-auto text-x1 text-[#060606] font-semibold mr-auto">
          HOME-LANDER
        </h1>

        <form
          onSubmit={handleSubmit}
          className="w-full flex flex-col max-w-[500px]"
        >
          <div className="w-full flex flex-col mb-2">
            <h3 className="text-3xl font-semibold mb-2">Login</h3>
            <p className="text-base mb-2">
              Welcome back! Please enter your details.
            </p>
          </div>
          <div className="w-full flex flex-col mt-4">
            <div className="relative z-0 w-full mb-6 group">
              <input
                value={data.credential}
                onChange={handleChange}
                name="credential"
                type="text"
                placeholder=" "
                className="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-blue-500 focus:outline-none focus:ring-0 focus:border-blue-600 peer"
              />
              <label
                htmlFor="credential"
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
                className="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-blue-500 focus:outline-none focus:ring-0 focus:border-blue-600 peer"
              />
              <label
                htmlFor="credential"
                className="peer-focus:font-medium absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-8 scale-75 top-3 -z-10 origin-[0] peer-focus:left-0 peer-focus:text-blue-600 peer-focus:dark:text-blue-500 peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-8"
              >
                Password
              </label>
            </div>
          </div>
          <div className="w-full flex items-center justify-between">
            <div className="w-full flex items-center">
              <input type="checkbox" className="w-4 h-4 mr-2" />
              <p className="text-sm">Remember Me</p>
            </div>
            <p className="text-sm font-medium whitespace-nowrap cursor-pointer underline underline-offset-2">
              Forgot Password ?
            </p>
          </div>

          <div className="w-full flex flex-col my-4">
            <button
              type="submit"
              className="w-full my-4 cursor-pointer text-white font-semibold bg-[#060606] rounded-md p-4 text-center flex items-center justify-center"
            >
              Log in
            </button>

            <div className="w-full flex items-center justify-center">
              <div className="w-full h-[1px] bg-black"></div>
              <p className="text-lg absolute text-[#000000]/80 bg-[#F5F5F5] ">
                or
              </p>
            </div>
            <Link to="/register">
              <button className="w-full my-4 cursor-pointer text-[#060606] font-semibold bg-white border-2 border-black rounded-md p-4 text-center flex items-center justify-center">
                Sign up
              </button>
            </Link>
          </div>
        </form>
        <ContactSupport />
      </div>
      <Cover />
    </div>
  );
}
