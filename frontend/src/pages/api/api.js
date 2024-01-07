import axios from 'axios';

export default async function handler(req, res) {
  try {
    const formData = new FormData();
    formData.append('file', yourFileObject);  

    const response = await axios.post('http://localhost:8081/api', formData);
    const data = response.data;

    res.status(200).json(data);
  } catch (error) {
    console.error('Error fetching data from Spring Boot:', error);
    res.status(500).json({ error: 'Internal Server Error' });
  }
}
