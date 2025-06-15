import { useState } from "react";
import { addPatient } from "../services/patients.service";
import { useNavigate } from "react-router";

function AddPatient() {
  const navigate = useNavigate();

  const [formData, setFormData] = useState({
    name: "",
  });

  const handleFormDataChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setFormData((prevState) => ({
      ...prevState,
      [e.target.name]: e.target.value,
    }));
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    try {
      const response = await addPatient(formData);
      if (!response.ok) {
        throw new Error(`Response status: ${response.status}`);
      }
      navigate("/");
    } catch (error) {
      console.log("error = ", error);
    }
  };

  return (
    <div className="max-w-4xl mx-auto py-12 px-4">
      <div className="bg-white shadow-xl">
        <div className="bg-blue-600 px-8 py-6">
          <h1 className="text-3xl font-extrabold text-white text-center">
            Add New Patient
          </h1>
        </div>

        <form onSubmit={handleSubmit} className="p-8">
          <div>
            <label className="text-sm font-semibold text-gray-700 block mb-2">
              Name
            </label>
            <input
              type="text"
              name="name"
              value={formData.name}
              onChange={handleFormDataChange}
              className="w-full px-4 py-3 border border-gray-300 rounded-xl"
              placeholder="Enter name"
            />
          </div>

          <div className="mt-8 pt-6 border-t border-gray-200 flex justify-between">
            <button
              className="px-8 py-3 bg-red-500 rounded-xl text-white font-medium hover:bg-red-700 shadow-md"
              onClick={() => navigate("/")}
            >
              Cancel
            </button>
            <button
              type="submit"
              className="px-8 py-3 bg-blue-500 rounded-xl text-white font-medium hover:bg-blue-700 shadow-md disabled:bg-blue-200"
              disabled={formData.name == ""}
            >
              Add
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}

export default AddPatient;
