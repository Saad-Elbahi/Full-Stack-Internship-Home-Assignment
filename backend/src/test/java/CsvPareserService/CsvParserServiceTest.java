package CsvPareserService;

import ma.dnaengineering.backend.model.Employee;
import ma.dnaengineering.backend.service.CsvParserService;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CsvParserServiceTest {

    @Test
    void parseCsv_shouldParseCsvFile() throws IOException {
        // Given
        CsvParserService csvParserService = new CsvParserService();
        InputStream inputStream = null;
        try {
            inputStream = getTestCsvInputStream();
            // When
            List<Employee> employees = csvParserService.parseCsv(inputStream);

            // Then
            assertEquals(3, employees.size());
            // Add more assertions based on your test CSV file
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    private InputStream getTestCsvInputStream() throws IOException {
        String csvData = "id,employee_name,job_title,salary\n" +
                "1,John Doe,Developer,80000.0\n" +
                "2,Jane Smith,Tester,70000.0\n" +
                "3,Bob Johnson,Analyst,90000.0";
        return new MockMultipartFile("file", "employees.csv", "text/csv", csvData.getBytes(StandardCharsets.UTF_8)).getInputStream();
    }
}
