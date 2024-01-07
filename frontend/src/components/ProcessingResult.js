import React from 'react';

const ProcessingResult = ({ employees, summary }) => {
  return (
    <div className="flex flex-col items-center justify-center min-h-screen bg-gradient-to-b from-bg-start to-bg-end">
      <div className="bg-white p-8 rounded-lg shadow-md w-full max-w-2xl">
        <h2 className="text-3xl font-extrabold mb-6 text-gray-800">Processed Employees</h2>
        <table className="min-w-full divide-y divide-gray-200">
          <thead className="bg-gray-100">
            <tr>
              <th className="py-2 px-4 text-left">ID</th>
              <th className="py-2 px-4 text-left">Name</th>
              <th className="py-2 px-4 text-left">Job Title</th>
              <th className="py-2 px-4 text-left">Salary</th>
            </tr>
          </thead>
          <tbody className="divide-y divide-gray-200">
            {employees.map((employee) => (
              
              <tr key={employee.id}>
                <td className="py-2 px-4">{employee.id}</td>
                <td className="py-2 px-4">{employee.Name}</td>
                <td className="py-2 px-4">{employee.jobTitle}</td>
                <td className="py-2 px-4">{employee.salary}</td>
              </tr>
            ))}
          </tbody>
        </table>

        <h2 className="text-3xl font-extrabold mt-8 mb-6 text-gray-800">Salary Summary</h2>
        <table className="min-w-full divide-y divide-gray-200">
          <thead className="bg-gray-100">
            <tr>
              <th className="py-2 px-4 text-left">Job Title</th>
              <th className="py-2 px-4 text-left">Average Salary</th>
            </tr>
          </thead>
          <tbody className="divide-y divide-gray-200">
            {Object.entries(summary).map(([jobTitle, averageSalary]) => (
              <tr key={jobTitle}>
                <td className="py-2 px-4">{jobTitle}</td>
                <td className="py-2 px-4">{averageSalary}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default ProcessingResult;
