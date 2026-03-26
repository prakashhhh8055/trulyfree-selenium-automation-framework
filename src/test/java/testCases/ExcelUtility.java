package testCases;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

    public FileInputStream fi;
    public FileOutputStream fo;
    public XSSFWorkbook wb;
    public XSSFSheet ws;
    public XSSFRow row;
    public XSSFCell cell;
    public CellStyle style;
    String path;
    
    public ExcelUtility(String path)
    {
    	this.path=path;
    }
    
    
    public int getRowCount(String xlsheet) throws IOException 
    {
        System.out.println("Opening Excel file...");

        fi = new FileInputStream(path);

        System.out.println("FileInputStream created");

        wb = new XSSFWorkbook(fi);

        System.out.println("Workbook opened");

        ws = wb.getSheet(xlsheet);

        System.out.println("Sheet accessed");

        int rowcount = ws.getLastRowNum();

        wb.close();
        fi.close();

        return rowcount;
    }

    // Get total cells in a row
    public int getCellCount(String xlsheet, int rownum) throws IOException {
        fi = new FileInputStream(path);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(xlsheet);
        row = ws.getRow(rownum);
        if(row == null)
        {
            return 0;
        }
        int cellcount = row.getLastCellNum();
        wb.close();
        fi.close();
        return cellcount;
    } 
    
    
    
    
    // Get cell data
    public String getCellData(String xlsheet, int rownum, int colnum) throws IOException {
        fi = new FileInputStream(path);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(xlsheet);
        row = ws.getRow(rownum);
        cell = row.getCell(colnum);

        String data;
        try {
            DataFormatter formatter = new DataFormatter();
            data = formatter.formatCellValue(cell);
        } catch (Exception e) {
            data = "";
        }

        wb.close();
        fi.close();
        return data;
    }

    // Set cell data
    public void setCellData(String xlsheet, int rownum, int colnum, String data)
            throws IOException {
        fi = new FileInputStream(path);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(xlsheet);

        row = ws.getRow(rownum);
        if (row == null) {
            row = ws.createRow(rownum);
        }

        cell = row.createCell(colnum);
        cell.setCellValue(data);

        fo = new FileOutputStream(path);
        wb.write(fo);

        wb.close();
        fi.close();
        fo.close();
    }

    // Fill green color
    public void fillGreenColor(String xlsheet, int rownum, int colnum) throws IOException {
        fi = new FileInputStream(path);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(xlsheet);
        row = ws.getRow(rownum);
        cell = row.getCell(colnum);

        style = wb.createCellStyle();
        style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cell.setCellStyle(style);

        fo = new FileOutputStream(path);
        wb.write(fo);

        wb.close();
        fi.close();
        fo.close();
    }

    // Fill red color
    public void fillRedColor(String xlsheet, int rownum, int colnum) throws IOException {
        fi = new FileInputStream(path);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(xlsheet);
        row = ws.getRow(rownum);
        cell = row.getCell(colnum);

        style = wb.createCellStyle();
        style.setFillForegroundColor(IndexedColors.RED.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cell.setCellStyle(style);

        fo = new FileOutputStream(path);
        wb.write(fo);

        wb.close();
        fi.close();
        fo.close();
    }
}