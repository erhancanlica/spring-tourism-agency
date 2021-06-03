package bmmf.turzimProje.utils;

import bmmf.turzimProje.model.Client;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelGenerator {

    public static ByteArrayInputStream clientsToExcel(List<Client> clients) throws IOException {
        String[] COLUMNs = {"Name","SurName","Email","Phone", "Address"};
        try(
                Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream out = new ByteArrayOutputStream();
        ){
            CreationHelper createHelper = workbook.getCreationHelper();

            Sheet sheet = workbook.createSheet("Clients");

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.BLUE.getIndex());

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);

            // Row for Header
            Row headerRow = sheet.createRow(0);

            // Header
            for (int col = 0; col < COLUMNs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(COLUMNs[col]);
                cell.setCellStyle(headerCellStyle);
            }


            int rowIdx = 1;
            for (Client client : clients) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(client.getName());
                row.createCell(1).setCellValue(client.getSurname());
                row.createCell(2).setCellValue(client.getEmail());
                row.createCell(3).setCellValue(client.getPhone());
                row.createCell(4).setCellValue(client.getAddress());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }
}
