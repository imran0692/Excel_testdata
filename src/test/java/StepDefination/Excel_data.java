package StepDefination;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel_data {
	public static LinkedHashMap<String, String> or_properties = new LinkedHashMap<String, String>();
	public static void getproperty(String pagename) throws IOException{
		
		try
        {
		
			or_properties.clear();
			
            FileInputStream file = new FileInputStream(new File("src/test/resources/resources/testdata/Axa_Limelight.xlsx"));
 
            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            Sheet sheet1 = workbook.getSheetAt(0);
           /* System.out.println(sheet1.getRow(20).getCell(4).getStringCellValue());
            Sheet sheet2 = workbook.getSheetAt(0);
            for (int i = 0; i < sheet2.getPhysicalNumberOfRows(); i++) {
                Row row = sheet2.getRow(i);
                
               //System.out.println(row);
               	for (int j = 0; j <=4; j++) {
               			Cell cell = row.getCell(j);
               			System.out.print(cell.toString()+" !! ");
                   //your logic
               		}
               	System.out.println();
                }
                */
            
            getPageProperties(sheet1,pagename);
           // System.out.println(or_properties);
            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);
 
            int rows=findRow(sheet,"Create Group");
            
           // System.out.println("filtered rows :"+rows);

            
            file.close();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
}
	public static void getPageProperties(Sheet sheet1,String pagename){
		int columnIndex=1;
		int total_rows=sheet1.getPhysicalNumberOfRows();
		
		int rowIndex=0;
		for (rowIndex=0; rowIndex<total_rows; rowIndex++){
		    Row row = CellUtil.getRow(rowIndex, sheet1);
		    Cell cell = CellUtil.getCell(row, columnIndex);
		    //System.out.println(cell.getStringCellValue());
		    if(cell.getStringCellValue().equals(pagename)){
		    	 	Cell cell1 = CellUtil.getCell(row, 3);
		    	 	String fieldName=cell1.getStringCellValue();
		    	 	Cell cell2 = CellUtil.getCell(row, 4);
		    	 	String fieldProperty=cell2.getStringCellValue();
		    	 	if(fieldName != null && !fieldName.trim().isEmpty())
		    	 	{
		    	 		or_properties.put(fieldName, fieldProperty);
		    	 		
		    	 	}
		    }
		}
	}
	private static int findRow(XSSFSheet sheet, String cellContent) {
	    for (Row row : sheet) {
	        for (Cell cell : row) {
	            if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
	                if (cell.getRichStringCellValue().getString().trim().equals(cellContent)) {
	                	
	                    return row.getRowNum();  
	                }
	            }
	        }
	    }               
	    return 0;
	}
	
	public static int search1(XSSFWorkbook workbook) throws Exception{
		Sheet s = workbook.getSheetAt(0);
		Row r = s.getRow(0);
		System.out.println(s.getRow(0).getPhysicalNumberOfCells());
	System.out.println(s.getRow(0).getLastCellNum()); 
	System.out.println(s.getPhysicalNumberOfRows()); 
		int patchColumn = -1;
		for (int cn=0; cn<r.getLastCellNum(); cn++) {
		   Cell c = r.getCell(cn);
		   if (c == null || c.getCellType() == Cell.CELL_TYPE_BLANK) {
		       // Can't be this cell - it's empty
		       continue;
		   }
		   if (c.getCellType() == Cell.CELL_TYPE_STRING) {
		      String text = c.getStringCellValue();
		      if ("TypeOfObject".equals(text)) {
		         patchColumn = cn;
		         break;
		      }
		   }
		}
		if (patchColumn == -1) {
		   throw new Exception("None of the cells in the first row were Patch");
		}
		return patchColumn;
	}
}
