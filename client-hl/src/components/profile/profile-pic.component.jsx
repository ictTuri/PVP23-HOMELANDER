import userIcon from "../../assets/userIcon.png";

export default function ProfilePic({ data }) {
  return (
    <div className="lg:w-3/12 lg:order-2 flex justify-center rounded-full">
      <div>
        {data === "" ? (
          <img
            src={userIcon}
            alt="..."
            className="shadow-xl rounded-full max-w-full align-middle border-none -m-16 -ml-20 lg:-ml-16"
            style={{ maxWidth: "200px" }}
          />
        ) : (
          <img
            src={data}
            alt="..."
            className="shadow-xl rounded-full max-w-full align-middle border-none -m-16 -ml-20 lg:-ml-16"
            style={{ maxWidth: "200px" }}
          />
        )}
      </div>
    </div>
  );
}
