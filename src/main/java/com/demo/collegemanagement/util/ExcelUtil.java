package com.demo.collegemanagement.util;

import com.demo.collegemanagement.model.Student;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Component
public class ExcelUtil {


    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    public ExcelUtil() {
        this.workbook = new XSSFWorkbook();
    }

    private void createCell(Row row, int columnCount, Object valueOfCell, CellStyle style) {
        this.sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (valueOfCell instanceof Integer) {
            cell.setCellValue((Integer) valueOfCell);
        } else if (valueOfCell instanceof Long) {
            cell.setCellValue((Long) valueOfCell);
        } else if (valueOfCell instanceof String) {
            cell.setCellValue((String) valueOfCell);
        } else {
            cell.setCellValue((Boolean) valueOfCell);
        }
        cell.setCellStyle(style);
    }

    private void writeHeader() {
        this.sheet = this.workbook.createSheet("Student");
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(14);
        style.setFont(font);
        createCell(row, 0, "First Name", style);
        createCell(row, 1, "Last Name", style);
        createCell(row, 2, "Email", style);

    }

    private void write(List<Student> students) {
        int rowCount = 1;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(12);
        style.setFont(font);
        for (Student student: students) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(row, columnCount++, student.getFirstName(), style);
            createCell(row, columnCount++, student.getLastName(), style);
            createCell(row, columnCount++, student.getEmail(), style);
        }
    }

    public void generateExcelFile(List<Student> students, HttpServletResponse response) throws IOException {
        this.writeHeader();
        this.write(students);
        ServletOutputStream outputStream = response.getOutputStream();
        this.workbook.write(outputStream);
        this.workbook.close();
        outputStream.close();

    }
}