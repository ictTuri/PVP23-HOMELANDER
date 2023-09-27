import { useState } from "react";
import Upload from "./upload.component";
import { upload_profile_pic } from "../../routes/services/profile.service";
import ProfilePic from "./profile-pic.component";

export default function ProfilePicModal(props) {
  const { currentImage, handleClose } = props;
  const [image, setImage] = useState("");
  const [preview, setPreview] = useState(
    `data:image/jpeg;base64,${currentImage}`
  );

  const handleImage = () => {
    if (image != null) {
      upload_profile_pic(image);
    }
  };

  const handleImageUpload = (e) => {
    const objectUrl = URL.createObjectURL(e.target.files[0]);
    setPreview(objectUrl);
    const formData = new FormData();
    formData.append("pt_file", e.target.files[0]);
    setImage(formData);
  };

  return (
    <>
      <div className="justify-center items-center flex overflow-x-hidden overflow-y-auto fixed inset-0 z-50 outline-none focus:outline-none">
        <div className="relative w-auto my-6 mx-auto max-w-3xl">
          {/*content*/}
          <div className="border-0 rounded-lg shadow-lg relative flex flex-col w-full bg-white outline-none focus:outline-none">
            {/*header*/}
            <div className="flex justify-center p-5 border-b border-solid border-slate-200 rounded-t object-cover">
              <ProfilePic data={preview} />
            </div>
            {/*body*/}
            <div className="relative p-6 flex-auto">
              <Upload onHandleUpload={handleImageUpload} />
            </div>
            {/*footer*/}
            <div className="flex items-center justify-end p-6 border-t border-solid border-slate-200 rounded-b">
              <button
                className="text-red-500 background-transparent font-bold uppercase px-6 py-2 text-sm outline-none focus:outline-none mr-1 mb-1 ease-linear transition-all duration-150"
                type="button"
                onClick={handleClose}
              >
                Close
              </button>
              <button
                className="bg-emerald-500 text-white active:bg-emerald-600 font-bold uppercase text-sm px-6 py-3 rounded shadow hover:shadow-lg outline-none focus:outline-none mr-1 mb-1 ease-linear transition-all duration-150"
                type="button"
                onClick={handleImage}
              >
                Save Changes
              </button>
            </div>
          </div>
        </div>
      </div>
      <div className="opacity-25 fixed inset-0 z-40 bg-black"></div>
    </>
  );
}
