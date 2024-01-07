import { useState } from 'react';
import axios from 'axios';
import Layout from '../components/Layout';
import FileUpload from '../components/FileUpload';
import ProcessingResult from '../components/ProcessingResult';

const Home = () => {
  const [selectedFile, setSelectedFile] = useState(null);
  const [employees, setEmployees] = useState([]);
  const [summary, setSummary] = useState({});
  const [error, setError] = useState(null);

  const handleFileUpload = (file) => {
    setSelectedFile(file);
    setEmployees([]);
    setSummary({});
  };

  const handleProcess = async () => {
    try {
      const formDataParse = new FormData();
      formDataParse.append('file', selectedFile);
  
      const responseEmployees = await axios.post('http://localhost:8081/api/parse', formDataParse);
      setEmployees(responseEmployees.data);
  
      const formDataSummary = new FormData();
      formDataSummary.append('file', selectedFile);
  
      const responseSummary = await axios.post('http://localhost:8081/api/summary', formDataSummary);
      setSummary(responseSummary.data);
    } catch (error) {
      setError('Error processing the file. Please try again.');
      console.error('Error processing file:', error);
    }
  };

  return (
    <Layout>
      <FileUpload onUpload={handleFileUpload} onProcess={handleProcess} />
      {error && <p style={{ color: 'red' }}>{error}</p>}
      {employees.length > 0 && <ProcessingResult employees={employees} summary={summary} />}
    </Layout>
  );
};

export default Home;
