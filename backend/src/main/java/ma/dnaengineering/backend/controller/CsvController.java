package ma.dnaengineering.backend.controller;

import ma.dnaengineering.backend.model.Employee;
import ma.dnaengineering.backend.service.CsvParserService;
import ma.dnaengineering.backend.service.SummaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class CsvController {

    private static final Logger logger = LoggerFactory.getLogger(CsvController.class);

    @Autowired
    private CsvParserService csvParserService;

    @Autowired
    private SummaryService summaryService;

    @PostMapping("/parse")
    public ResponseEntity<List<Employee>> parseCsv(@RequestParam("file") MultipartFile file) {
        try {
            logger.info("Received request to parse CSV file.");
            List<Employee> employees = csvParserService.parseCsv(file.getInputStream());
            logger.info("CSV file parsed successfully.");
            return ResponseEntity.ok(employees);
        } catch (Exception e) {
            logger.error("Error processing CSV file.", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/summary")
    public ResponseEntity<Map<String, Double>> getSalarySummary(@RequestParam("file") MultipartFile file) {
        try {
            logger.info("Received request to calculate salary summary.");
            List<Employee> employees = csvParserService.parseCsv(file.getInputStream());
            Map<String, Double> summary = summaryService.calculateAverageSalaryByJobTitle(employees);
            logger.info("Salary summary calculated successfully.");
            return ResponseEntity.ok(summary);
        } catch (Exception e) {
            logger.error("Error calculating salary summary.", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
