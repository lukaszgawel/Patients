import { useEffect, useState } from "react";
import { getAllPatients } from "../services/patients.service";
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

  return (
    <div className="container mx-auto">
      <h1 className="text-center mt-10 text-3xl font-bold">List of Patients</h1>
      {patients.length === 0 ? (
        <h2 className="text-center mt-10 text-xl font-bold">No data</h2>
      ) : (
        <div className="mt-10">
          <div className="flex flex-col items-center gap-y-4">
            {patients.map((patient) => (
              <div key={patient.id} className="bg-gray-100 p-4 rounded-md">
                <h2 className="text-lg font-bold">{patient.name}</h2>
              </div>
            ))}
          </div>
        </div>
      )}
    </div>
  );
}

export default Home;
