import { useContext, useState } from "react";
import COVER_IMAGE from "../../assets/cover.jpg";
import { UserContext } from "../../context/user.context";
import { login } from "../../routes/services/auth.service";

const defaultFormFields = {
  credential: "",
  password: "",
};

export default function Login() {
  const { setCurrentUser } = useContext(UserContext);

  const [data, setData] = useState({
    credential: "",
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
      credential: data.credential,
      password: data.password,
    };

    try {
      login(userData).then((response) => setCurrentUser(response.data));
      resetFormFields();
    } catch (error) {
      console.log(error.code);
    }
  };

  return (
    <div className="w-full h-screen flex items-start">
      <div className="relative w-1/2 h-full flex flex-col">
        <div className="absolute top-[25%] left-[10%] flex flex-col">
          <h1 className="text-4xl text-[#ffffff] font-extrabold my-4 drop-shadow-lg">
            Make your dreams true with Home-Lander
          </h1>
          <p className="text-3xl font-mono text-white font-normal drop-shadow-lg">
            Get started for free.
          </p>
        </div>
        <img
          src={COVER_IMAGE}
          className="w-full h-full object-cover"
          alt="cover"
        />
      </div>

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
          <div className="w-full flex flex-col">
            <input
              value={data.credential}
              onChange={handleChange}
              name="credential"
              type="text"
              placeholder="Email"
              className="w-full text-black py-2 my-2 bg-transparent border-b border-black outline-none focus:outline-none"
            />

            <input
              value={data.password}
              onChange={handleChange}
              name="password"
              type="password"
              placeholder="Password"
              className="w-full text-black py-2 my-2 bg-transparent border-b border-black outline-none focus:outline-none"
            />
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

            <button className="w-full my-4 cursor-pointer text-[#060606] font-semibold bg-white border-2 border-black rounded-md p-4 text-center flex items-center justify-center">
              Sign up
            </button>
          </div>
        </form>

        <div className="w-full flex items-center justify-center">
          <p className="text-sm font-normal text-[#060606]">
            Trouble with the form!{" "}
            <span
              className="font-semibold
                    underline underline-offset-2 cursor-pointer"
            >
              Contact our support!
            </span>
          </p>
        </div>
      </div>
    </div>
  );
}