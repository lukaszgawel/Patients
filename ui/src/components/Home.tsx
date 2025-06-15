import { useEffect, useState } from "react";
import { getAllPatients, deletePatient } from "../services/patients.service";
import { PatientDto } from "../types/Patient";

function Home() {
  const [patients, setPatients] = useState<PatientDto[]>([]);

  useEffect(() => {
    getPatients();
  }, []);

  const getPatients = async () => {
    try {
      const response = await getAllPatients();
      if (!response.ok) {
        throw new Error(`Response status: ${response.status}`);
      }
      const data = await response.json();
      setPatients(data);
    } catch (error) {
      console.log("error = ", error);
    }
  };

  const deletePatientById = async (id: string) => {
    try {
      const response = await deletePatient(id);
      if (!response.ok) {
        throw new Error(`Response status: ${response.status}`);
      }
      await getPatients();
    } catch (error) {
      console.log("error = ", error);
    }
  };

  return (
    <div className="container mx-auto">
      <h1 className="text-center mt-10 text-3xl font-bold">List of Patients</h1>
      {patients.length === 0 ? (
        <h2 className="text-center mt-10 text-xl font-bold">No data found</h2>
      ) : (
        <div className="mx-auto flex flex-col w-2/3 gap-y-4 mt-10">
          {patients.map((patient) => (
            <div
              key={patient.id}
              className="flex gap-2 justify-between items-center bg-gray-100 p-4 rounded-md"
            >
              <h2 className="text-lg font-bold">{patient.name}</h2>
              <button
                className="px-8 py-3 bg-red-500 rounded-xl font-medium text-white hover:bg-red-700"
                onClick={() => deletePatientById(patient.id)}
              >
                delete
              </button>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}

export default Home;
