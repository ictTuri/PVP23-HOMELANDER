import Properties from "../../components/dashboard/content/properties.component";
import Filter from "../../components/dashboard/filter/filter.component";
import backgroud from "../../assets/pcover.jpg";

export default function Home() {
  return (
    <div>
      <div className="bg-white">

        <div className="h-full p-40 bg-cover bg-center" style={{
          backgroundImage: `url(${backgroud})`,
        }}>
          <code className="bg-gray-100">
            <Filter />
          </code>
        </div>
        <div className="p-4 bg-gray-200 h-screen">
          <Properties />
        </div>
      </div>
    </div>
  );
}
