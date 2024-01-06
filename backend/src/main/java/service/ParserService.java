package service;

import model.Employee;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ParserService {

    public List<Employee> parseCSV(String filePath) throws IOException {
        List<Employee> employees = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;

            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue; // Skip header line
                }

                String[] fields = line.split(",");
                if (fields.length == 4) {
                    int id = Integer.parseInt(fields[0]);
                    String name = fields[1];
                    String jobTitle = fields[2];
                    double salary = Double.parseDouble(fields[3]);

                    Employee employee = new Employee(id, name, jobTitle, salary);
                    employees.add(employee);
                }
            }
        }

        return employees;
    }

    public Map<String, Double> calculateAverageSalaryByJobTitle(List<Employee> employees) {
        Map<String, Double> averageSalaries = new HashMap<>();
        Map<String, Integer> jobTitleCounts = new HashMap<>();
        Map<String, Double> jobTitleTotalSalaries = new HashMap<>();

        for (Employee employee : employees) {
            String jobTitle = employee.getJobTitle();
            double salary = employee.getSalary();

            jobTitleCounts.put(jobTitle, jobTitleCounts.getOrDefault(jobTitle, 0) + 1);
            jobTitleTotalSalaries.put(jobTitle, jobTitleTotalSalaries.getOrDefault(jobTitle, 0.0) + salary);
        }

        for (String jobTitle : jobTitleCounts.keySet()) {
            int count = jobTitleCounts.get(jobTitle);
            double totalSalary = jobTitleTotalSalaries.get(jobTitle);
            double averageSalary = totalSalary / count;
            averageSalaries.put(jobTitle, averageSalary);
        }

        return averageSalaries;
    }
}