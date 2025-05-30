import { Route, BrowserRouter as Router, Routes } from "react-router";
import Home from "./components/Home";
import Navbar from "./components/Navbar";
import AddPatient from "./components/Add";

function App() {
  return (
    <Router>
      <Navbar />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/add" element={<AddPatient />} />
      </Routes>
    </Router>
  );
}

export default App;
