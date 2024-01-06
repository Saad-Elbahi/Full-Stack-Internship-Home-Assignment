package controller;

import model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.ParserService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ParserController {

    private final ParserService parserService;

    @Autowired
    public ParserController(ParserService parserService) {
        this.parserService = parserService;
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> parseCSV() {
        try {
            String filePath = "data/employees.csv";
            List<Employee> employees = parserService.parseCSV(filePath);
            return ResponseEntity.ok(employees);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/average-salaries")
    public ResponseEntity<Map<String, Double>> calculateAverageSalaries() {
        try {
            String filePath = "data/employees.csv";
            List<Employee> employees = parserService.parseCSV(filePath);
            Map<String, Double> averageSalaries = parserService.calculateAverageSalaryByJobTitle(employees);
            return ResponseEntity.ok(averageSalaries);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}