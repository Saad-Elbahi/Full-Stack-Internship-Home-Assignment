
package ma.dnaengineering.backend.service;


import ma.dnaengineering.backend.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SummaryService {

    public Map<String, Double> calculateAverageSalaryByJobTitle(List<Employee> employees) {
        return employees.stream()
                .collect(Collectors.groupingBy(Employee::getJobTitle,
                        Collectors.averagingDouble(Employee::getSalary)));
    }
}
