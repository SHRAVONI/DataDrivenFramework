package DataDriven.Framework.TestCases;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
public class DataTestCase {

	
	
	static File src=new File("C:\\Users\\Soni\\Downloads\\delete.xlsx");
	String TestCaseName="TestA";
	static String sheetName="das";
	static FileInputStream fis;
	static XSSFCell Cell;
	static XSSFSheet sh;
	static XSSFWorkbook wr;
	
			
		
	@Test(priority=1)
	public void readData() throws Exception
	{  
		String[] valueToWrite = {"Mr. E","Noida"};
		WriteData(valueToWrite);
		 fis=new FileInputStream(src);
		wr=new XSSFWorkbook(fis);
		 sh=wr.getSheet(sheetName);
		int rowcount=sh.getLastRowNum()-sh.getFirstRowNum();
		
		for(int i=0;i<rowcount;i++)
		{
			Row cellrow=sh.getRow(i);
			for(int j=0;j< cellrow.getLastCellNum();j++)
				System.out.println(cellrow.getCell(j).getStringCellValue());
			//System.out.println();
		}
		System.out.println();
		TestCaseData();
	//	getRowContains(TestCaseName,0,sheetName);
		
	}
	
	
	
	public void WriteData(String[] dataToWrite) throws Exception
	{
		
		 fis=new FileInputStream(src);
		XSSFWorkbook wr=new XSSFWorkbook(fis);
		 sh=wr.getSheet(sheetName);
		//Get the current count of rows in excel file
		int rowcount=sh.getLastRowNum()-sh.getFirstRowNum();
		//Get the first row from the sheet
		Row row = sh.getRow(0);
		 //Create a new row and append it at last of sheet
		 Row newRow = sh.createRow(rowcount+1);
		 
		 //Create a loop over the cell of newly created Row
		 for(int j = 0; j < row.getLastCellNum(); j++){

		        //Fill data in row

		        Cell cell = newRow.createCell(j);

		        cell.setCellValue(dataToWrite[j]);

		    }

		    //Close input stream

		// fis.close();

		    //Create an object of FileOutputStream class to create write data in excel file

		    FileOutputStream outputStream = new FileOutputStream(src);

		    //write data in the excel file

		    wr.write(outputStream);

		    //close output stream

		//    outputStream.close();
		
	}
	//This method is to read the test data from the Excel cell
    //In this we are passing Arguments as Row Num, Col Num & Sheet Name
	
	public static String getCellData(int RowNum, int ColNum, String SheetName ) throws Exception
		{
		System.out.println("1");
		try{
			System.out.println("2");
		 fis=new FileInputStream(src);
		 System.out.println("3");
		 wr=new XSSFWorkbook(fis);
		 System.out.println("4");
			 sh=wr.getSheet(sheetName);
			 System.out.println("5");
			 
				 Cell = sh.getRow(RowNum).getCell(ColNum);
				 System.out.println("6");
                     String CellData = Cell.getStringCellValue();
                     System.out.println("7");
                     return CellData;
                     
			 }catch (Exception e){
				 System.out.println("8");
                 return"Error";
			 }
			
		}
			 
	 //This method is to get the row count used of the excel sheet
			 public static int getRowCount(String sheetName) throws Exception{
				// XSSFWorkbook wr=new XSSFWorkbook(fis);
			 
		         sh = wr.getSheet(sheetName);
		         int number=sh.getLastRowNum()+1;
		         return number;
		         }
			 
			
			 public static int getRowContains(String TestCaseName, int colNum,String sheetName) throws Exception
			 {
		         int i;
		         int colNum1=0;
		       //  XSSFWorkbook wr=new XSSFWorkbook(fis);
				  sh = wr.getSheet(sheetName);
				  int rowCount = DataTestCase.getRowCount(sheetName);
				  for (i=0 ; i<rowCount; i++){
					  if  (DataTestCase.getCellData(i,colNum1,sheetName).equalsIgnoreCase(TestCaseName)){
					         break;
					         }
					         }
				  System.out.println(+i);
					         return i;
					         }
				 
		        
			 
			 
			
			 public void TestCaseData() throws Exception
			 {	 
				
				  fis=new FileInputStream(src);
					 wr=new XSSFWorkbook(fis);
					 sh=wr.getSheet(sheetName);
				 
		        
		         int TestStartRowNo=1;
		         try
		         {
		         while(!DataTestCase.getCellData(TestStartRowNo,0,sheetName).equals(TestCaseName))
		        		 {
		        	 TestStartRowNo++;
		        		 }
		         
				 System.out.println(+TestStartRowNo);
		         }
		         catch(Exception ex)
		         {
		         System.out.println(ex.getStackTrace());
		         }
			 }
	
			 }


	

