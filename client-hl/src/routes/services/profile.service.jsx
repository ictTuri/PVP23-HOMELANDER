import axios from "axios";

export const upload_profile_pic = async (image) => {
  const token = localStorage.getItem("token");
  return await axios
    .post("http://localhost:8081/files/user", image, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })
};

export const update_user_profile_pic = async (currentUUID, newUUID) => {
  const token = localStorage.getItem("token");
  return await axios
    .put("http://localhost:8081/users/profile/update-profile-pic", null, { params: {
      currentUUID: currentUUID,
      newUUID: newUUID
    },
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })
};
