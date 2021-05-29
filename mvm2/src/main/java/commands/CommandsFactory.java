package commands;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class CommandsFactory {
	
	
	
	public void createCommand(String command,String filename) {
		
		switch (command) {
		case "File":
			break;
		case "Save":
			System.out.println("You selected Save");
			saveDocument();
			break;
			
		case "Open":
			System.out.println("You selected open");
			String extension = filename.substring(filename.lastIndexOf("."),filename.length());
			if (!extension.equals(".xlsx") & !extension.equals(".docx")) {
				System.out.println("Only supports excel and word documents " + extension);
				
			}else if (extension.equals("docx")) {
				openWordDocument(filename);
			}else{
				openXLDocument(filename);
				
			}
			
			break;
			
		case "Close":
			System.out.println("You selected close");
			break;
		case "Exit":
			System.out.println("You selected exit");
			System.exit(0);
			break;
		}
		
	}
	
	private void openWordDocument(String filename) {
		// TODO Auto-generated method stub
		
	}

	public void openXLDocument(String path) {
		 try
	        {
	            FileInputStream file = new FileInputStream(new File(path));
	 
	            //Create Workbook instance holding reference to .xlsx file
	            XSSFWorkbook workbook = new XSSFWorkbook(file);
	 
	            //Get first/desired sheet from the workbook
	            XSSFSheet sheet = workbook.getSheetAt(0);
	 
	            //Iterate through each rows one by one
	            Iterator<Row> rowIterator = sheet.iterator();
	            while (rowIterator.hasNext()) 
	            {
	                Row row = rowIterator.next();
	                //For each row, iterate through all the columns
	                Iterator<Cell> cellIterator = row.cellIterator();
	                 
	                while (cellIterator.hasNext()) 
	                {
	                    Cell cell = cellIterator.next();
	                    //Check the cell type and format accordingly
	                    switch (cell.getCellType()) 
	                    {
	                        case NUMERIC:
	                            System.out.print(cell.getNumericCellValue() + "\t");
	                            break;
	                        case STRING:
	                            System.out.print(cell.getStringCellValue() + "\t");
	                            break;
	                    }
	                }
	                System.out.println("");
	            }
	            file.close();
	        } 
	        catch (Exception e) 
	        {
	            e.printStackTrace();
	        }
	    }
		
	
	
	
	public void saveDocument() {
		
	}


}
