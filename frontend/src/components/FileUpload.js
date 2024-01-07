import { useState } from 'react';
import axios from 'axios';

const FileUpload = ({ onUpload, onProcess }) => {
  const [selectedFile, setSelectedFile] = useState(null);

  const handleFileChange = (event) => {
    const file = event.target.files[0];
    setSelectedFile(file);
    onUpload(file);
  };

  const handleProcessClick = () => {
    if (selectedFile) {
      onProcess();
    }
  };

  return (
    <div>
      <input type="file" onChange={handleFileChange} />
      {selectedFile && <button onClick={handleProcessClick}>Process</button>}
    </div>
  );
};

export default FileUpload;
