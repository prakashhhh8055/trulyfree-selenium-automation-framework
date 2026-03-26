package testCases;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class testingPractice2 
{
	
	public static void main(String[] args) throws IOException
	{
		
		//Reading Values from Xl
	/*	FileInputStream file=new FileInputStream(System.getProperty("user.dir")+"\\testData\\loginTestData.xlsx");
		XSSFWorkbook workbook=new XSSFWorkbook(file);
		XSSFSheet sheet=workbook.getSheet("Sheet1");
		int totalRows=sheet.getLastRowNum();
		int totalCells=sheet.getRow(1).getLastCellNum();
		
		for(int r=0;r<totalRows;r++)
		{
			XSSFRow currentRow=sheet.getRow(r);
			for(int c=0;c<totalCells;c++)
			{
				XSSFCell cell=currentRow.getCell(c);
				System.out.print(cell.toString()+"  ");
			}
			System.out.println();
		}
		
		workbook.close();
		file.close(); */
		
		
		//create New file. Read  No of columns, and Rows and Write into file
		FileOutputStream file=new FileOutputStream(System.getProperty("user.dir")+"\\testData\\exampletest.xlsx");
		XSSFWorkbook workbook=new XSSFWorkbook();
		XSSFSheet sheet=workbook.createSheet("contacts");
		
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the Number of rows you want to enter");
		int totalRows=sc.nextInt();
		
		System.out.println("Enter the Number of Columns you want to enter");
		int totalCells=sc.nextInt();
		
		for(int r=0;r<totalRows;r++)
		{
			XSSFRow row=sheet.createRow(r);
			
			for(int c=0;c<totalCells;c++)
			{
				row.createCell(c).setCellValue(sc.next());
			}
		}
		
		System.out.println("File Created");
		workbook.write(file);
		workbook.close();
		file.close();
		
		
		
	}
	

}
