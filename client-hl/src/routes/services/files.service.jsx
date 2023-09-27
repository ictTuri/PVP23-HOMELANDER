import axios from "axios";

export const get_profile_pic = async (uuid) => {
    const token = localStorage.getItem("token");
  
    return await axios
      .get("http://localhost:8081/files/user", {
        params: { uuid: uuid },
        headers: {
          Authorization: `Bearer ${token}`,
        }
      })
  };
  