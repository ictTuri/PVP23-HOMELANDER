export const get_properties_public = async (filter) => {
  
    return await axios
      .post("http://localhost:8081/properties/search", filter)
      .then((response) => {
        console.log(response);
      })
      .catch((error) => {
        console.log("Error", error.message);
      });
  };
  