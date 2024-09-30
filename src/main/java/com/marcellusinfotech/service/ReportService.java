package com.marcellusinfotech.service;

import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.springframework.stereotype.Service;

import com.marcellusinfotech.entity.Employee;
import com.marcellusinfotech.repository.EmployeeRepository;

import jakarta.servlet.ServletOutputStream; // Use 'jakarta' if applicable
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final EmployeeRepository employeeRepository;

    public void generateExcel(HttpServletResponse response) throws Exception {
        List<Employee> employees = employeeRepository.findAll();
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Employee Info");
        HSSFRow row = sheet.createRow(0);

     // Create header row
        String[] headers = { "ID", "Name", "Email", "Date of Birth", "Date of Joining", "Designation", "Last Login",
                "Nationality", "Gender", "Blood Group", "Degree", "Status" };

        // Create a cell style for the header
        HSSFCellStyle headerStyle = workbook.createCellStyle();

        // Set the background color
        headerStyle.setFillForegroundColor(HSSFColor.HSSFColorPredefined.GREY_25_PERCENT.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        // Create a font for the header
        HSSFFont font = workbook.createFont();
        font.setBold(true); // Make the font bold
        headerStyle.setFont(font);

        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle); // Apply the header style to each header cell
        }


        int dataRowIndex = 1;

        // Date formatters for LocalDate and LocalDateTime
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        for (Employee employee : employees) {
            HSSFRow dataRow = sheet.createRow(dataRowIndex);

            // Ensure employee is not null
            if (employee == null) {
                System.err.println("Employee object is null at index: " + dataRowIndex);
                continue; // Skip this iteration
            }

            dataRow.createCell(0).setCellValue(employee.getEmployeeId() != null ? employee.getEmployeeId() : "N/A");
            dataRow.createCell(1).setCellValue(employee.getEmployeeName() != null ? employee.getEmployeeName() : "N/A");
            dataRow.createCell(2).setCellValue(employee.getEmployeeEmail() != null ? employee.getEmployeeEmail() : "N/A");

            // Check Date of Birth
            if (employee.getEmployeeDateOfBirth() != null) {
                dataRow.createCell(3).setCellValue(employee.getEmployeeDateOfBirth().format(dateFormatter));
            } else {
                dataRow.createCell(3).setCellValue("N/A");
            }

            // Check Date of Joining
            if (employee.getEmployeeDateOfJoining() != null) {
                dataRow.createCell(4).setCellValue(employee.getEmployeeDateOfJoining().format(dateFormatter));
            } else {
                dataRow.createCell(4).setCellValue("N/A");
            }

            dataRow.createCell(5).setCellValue(employee.getEmployeeDesignation() != null ? employee.getEmployeeDesignation() : "N/A");

            // Check Last Login
            if (employee.getEmployeeLastLogin() != null) {
                dataRow.createCell(6).setCellValue(employee.getEmployeeLastLogin().format(dateTimeFormatter));
            } else {
                dataRow.createCell(6).setCellValue("N/A");
            }

            dataRow.createCell(7).setCellValue(
                    employee.getEmployeeNationality() != null ? employee.getEmployeeNationality().toString() : "N/A");
            dataRow.createCell(8)
                    .setCellValue(employee.getEmployeeGender() != null ? employee.getEmployeeGender().toString() : "N/A");
            dataRow.createCell(9)
                    .setCellValue(employee.getEmployeeBloodGroup() != null ? employee.getEmployeeBloodGroup().toString() : "N/A");
            dataRow.createCell(10)
                    .setCellValue(employee.getEmployeeDegree() != null ? employee.getEmployeeDegree().toString() : "N/A");
            dataRow.createCell(11)
                    .setCellValue(employee.getEmployeeStatus() != null ? employee.getEmployeeStatus().toString() : "N/A");

            dataRowIndex++;
        }

        try (ServletOutputStream ops = response.getOutputStream()) {
            workbook.write(ops);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error generating Excel file.");
        } finally {
            workbook.close();
        }
    }
}
