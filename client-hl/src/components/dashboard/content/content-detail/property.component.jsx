import { Link } from "react-router-dom";

export default function Property({ props }) {
  const { imageSrc, imageAlt, price, name } = props;

  return (
    <div>
      <a href="#" className="flex flex-col bg-white border border-gray-200 shadow md:flex-row hover:bg-gray-100 dark:border-gray-700 dark:bg-gray-800 dark:hover:bg-gray-700">
        <img className="w-screen h-40 object-cover md:w-40" src={imageSrc} alt={imageAlt} />
        <div className="flex flex-col justify-between p-4 leading-normal">
          <h5 className="mb-2 text-2xl font-bold tracking-tight text-gray-900 dark:text-white">Noteworthy technology acquisitions 2021</h5>
          <p className="mb-3 font-normal text-gray-700 dark:text-gray-400">Here are the biggest enterprise technology acquisitions of 2021 so far, in reverse chronological order.</p>
        </div>
      </a>
    </div>
  );
}
