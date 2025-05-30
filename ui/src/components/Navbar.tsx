import { Link } from "react-router";

function Navbar() {
  return (
    <nav className="bg-black text-white shadow-md">
      <div className="container mx-auto px-4 py-3 flex justify-between items-center">
        <Link to="/" className="text-2xl font-bold">
          PatientInfoApp
        </Link>
        <div className="space-x-6">
          <Link to="/" className="hover:text-gray-200">
            Home
          </Link>
          <Link to="/add" className="hover:text-gray-200">
            Add Patient
          </Link>
        </div>
      </div>
    </nav>
  );
}

export default Navbar;
