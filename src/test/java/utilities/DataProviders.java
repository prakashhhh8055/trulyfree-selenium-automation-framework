package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;



public class DataProviders {
	
	@DataProvider(name="TestData")
	public Object[][] getData() throws IOException
	{
		System.out.println("Reading Excel Data...");
	    String path="C:\\Users\\Prakash\\eclipse-workspace\\trulyfreeV1\\testData\\loginTestData.xlsx";

	    ExcelUtility xl = new ExcelUtility(path);

	    int rows = xl.getRowCount("Sheet1");
	    int cols = xl.getCellCount("Sheet1",1);
	    
	    System.out.println("Total rows: "+rows);
	    System.out.println("Total cols: "+cols);

	    Object data[][] = new Object[rows][cols];

	    for(int i=1;i<=rows;i++)
	    {
	        for(int j=0;j<cols;j++)
	        {
	            data[i-1][j] = xl.getCellData("Sheet1", i, j);
	        }
	    }
	    
	    System.out.println("Finished Reading Excel Data...");

	    return data;
		
	}
	
	

}
