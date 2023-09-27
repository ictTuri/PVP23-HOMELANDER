import COVER_IMAGE from "../../assets/cover.jpg";

export default function Cover() {
  return (
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
  );
}
