import { useEffect, useState } from "react";

import ProfilePic from "./profile-pic.component";
import ProfilePicModal from "./profile-pic-modal.component";
import { get_profile_pic } from "../../routes/services/files.service";
import cover from "../../assets/pcover.jpg";
import { useSelector } from "react-redux";
import { selectCurrentUser } from "../../redux/user/user.selector";

export default function Profile() {
  const currentUser = useSelector(selectCurrentUser);
  const [showModal, setShowModal] = useState(false);
  const [profilePic, setProfilePic] = useState("");

  const handleModalOpen = () => {
    setShowModal(true);
  };
  const handleModalClose = () => {
    setShowModal(false);
  };

  const getProfilePic = (uuid) => {
    get_profile_pic(uuid)
      .then((res) => {
        setProfilePic(res.data);
      })
      .catch((error) => {
        console.log("Error", error.message);
      });
  };

  useEffect(() => {
    getProfilePic(currentUser.profilePicUUID);
  }, []);

  return (
    <>
      {showModal ? (
        <ProfilePicModal
          currentImage={profilePic}
          handleClose={handleModalClose}
        />
      ) : null}
      <main className="profile-page">
        <section className="relative block" style={{ height: "500px" }}>
          <div
            className="absolute top-0 w-full h-full bg-center bg-cover"
            style={{
              backgroundImage: `url(${cover})`,
            }}
          >
            <span
              id="blackOverlay"
              className="w-full h-full absolute opacity-50 bg-black"
            ></span>
          </div>
        </section>
        <section className="relative py-16 bg-gray-300">
          <div className="container mx-auto px-4">
            <div className="relative flex flex-col min-w-0 break-words bg-white w-full mb-6 shadow-xl rounded-lg -mt-64">
              <div className="px-6">
                <div
                  className="flex flex-wrap justify-center"
                  onClick={handleModalOpen}
                >
                  <ProfilePic data={`data:image/jpeg;base64,${profilePic}`} />
                  <div className="flex w-full lg:w-4/12 px-4 lg:order-3 lg:text-right lg:self-center justify-center">
                    <div className="py-6 px-3 mt-32 sm:mt-0">
                      <button
                        className="bg-blue-500 active:bg-blue-600 uppercase text-white font-bold hover:shadow-md shadow text-xs px-4 py-2 rounded outline-none focus:outline-none sm:mr-2 mb-1"
                        type="button"
                        style={{ transition: "all .15s ease" }}
                      >
                        Update Profile
                      </button>
                    </div>
                    <div className="py-6 px-3 mt-32 sm:mt-0">
                      <button
                        className="bg-pink-500 active:bg-pink-600 uppercase text-white font-bold hover:shadow-md shadow text-xs px-4 py-2 rounded outline-none focus:outline-none sm:mr-2 mb-1"
                        type="button"
                        style={{ transition: "all .15s ease" }}
                      >
                        Send Message
                      </button>
                    </div>
                  </div>
                  <div className="w-full lg:w-4/12 px-4 lg:order-1">
                    <div className="flex justify-center py-4 lg:pt-4 pt-8">
                      <div className="mr-4 p-3 text-center">
                        <span className="text-xl font-bold block tracking-wide text-gray-700">
                          {currentUser.propertyOwned.length}
                        </span>
                        <span className="text-sm text-gray-500">
                          Owned Properties
                        </span>
                      </div>
                      <div className="mr-4 p-3 text-center">
                        <span className="text-xl font-bold block tracking-wide text-gray-700">
                          {currentUser.propertyRented.length}
                        </span>
                        <span className="text-sm text-gray-500">
                          Rented Properties
                        </span>
                      </div>
                    </div>
                  </div>
                </div>
                <div className="text-center mt-12">
                  <h3 className="text-4xl font-semibold leading-normal text-gray-800 mb-2">
                    {currentUser.firstName === null
                      ? ""
                      : currentUser.firstName}
                    <span> </span>
                    {currentUser.lastName === null ? "" : currentUser.lastName}
                  </h3>
                  <div className="text-sm leading-normal mt-0 mb-2 text-gray-500 font-bold uppercase">
                    <i className="fas fa-map-marker-alt mr-2 text-lg text-gray-500"></i>{" "}
                    {currentUser.city === null ? "" : currentUser.city}
                    <span> </span>
                    {currentUser.country === null ? "" : currentUser.country}
                  </div>
                </div>
              </div>
            </div>
          </div>
        </section>
      </main>
    </>
  );
}


