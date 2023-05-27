import { Fragment, useContext } from "react";
import { Link, Outlet } from "react-router-dom";
import ProfileNav from "./profile-nav.component";
import { UserContext } from "../../context/user.context";

export default function Navigation() {
  const { currentUser } = useContext(UserContext);
  console.log(currentUser);
  return (
    <Fragment>
      <div className="bg-gray-800">
        <div className="mx-auto px-2 sm:px-6 lg:px-8 w-full">
          <div className="relative flex h-16 items-center justify-between">
            <div className="flex flex-1 items-center sm:items-stretch sm:justify-start">
              <Link to="/">
                <img
                  className="h-8 w-auto"
                  src="https://tailwindui.com/img/logos/mark.svg?color=indigo&shade=500"
                  alt="Your Company"
                />
              </Link>
            </div>
            {currentUser ? (
              <ProfileNav />
            ) : (
              <div className="absolute inset-y-0 right-0 flex items-center pr-2 sm:static sm:inset-auto sm:ml-6 sm:pr-0">
                <Link to="/login">
                  <span className="inline-flex items-center rounded-md bg-gray-50 px-2 py-1 text-base font-medium text-gray-600 ring-1 ring-inset ring-gray-500/10">
                    login
                  </span>
                </Link>
              </div>
            )}
          </div>
        </div>
      </div>
      <Outlet />
    </Fragment>
  );
}
