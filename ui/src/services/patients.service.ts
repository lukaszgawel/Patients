import { AddPatientDto } from "../types/Patient";

const BASE_URL = import.meta.env.VITE_BACKEND_URL;

export const getAllPatients = async () => {
  return await fetch(`${BASE_URL}/api/v1/patients`);
};

export const addPatient = async (formData: AddPatientDto) => {
  const reqData = {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(formData),
  }
  return await fetch(`${BASE_URL}/api/v1/patients`, reqData);
};
