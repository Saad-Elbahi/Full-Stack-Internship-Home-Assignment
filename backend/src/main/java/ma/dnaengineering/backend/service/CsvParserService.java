// CsvParserService.java
package ma.dnaengineering.backend.service;


import ma.dnaengineering.backend.model.Employee;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvParserService {

    public List<Employee> parseCsv(InputStream inputStream) {
        List<Employee> employees = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String header = reader.readLine();

            employees = reader.lines()
                    .map(line -> line.split(","))
                    .map(data -> new Employee(
                            Integer.parseInt(data[0].trim()),
                            data[1].trim(),
                            data[2].trim(),
                            Double.parseDouble(data[3].trim())
                    ))
                    .toList();

        } catch (Exception e) {
            throw new RuntimeException("Error parsing CSV file", e);
        }

        return employees;
    }
}
