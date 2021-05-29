package commands;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileFilter;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import text2Speech.GUI2;


public class CommandsFactory {
	
	
	JTextArea text;
	
	public void createCommand(String command,JTextArea textArea) {
		text = textArea;
		switch (command) {
		case "File":
			break;
		case "Save":
			JFileChooser save = new JFileChooser(new File("C:\\"));
			System.out.println("You selected Save");
			save.setDialogTitle("Save file");
			
			save.setFileFilter(new FileFilter() {

				   public String getDescription() {
				       return "excel files (*.xlsx)";
				   }

				   public boolean accept(File f) {
				       if (f.isDirectory()) {
				           return true;
				       } else {
				           String filename = f.getName().toLowerCase();
				           return filename.endsWith(".xlsx") || filename.endsWith(".xls") ;
				       }
				   }
				});
			int result = save.showSaveDialog(null);
			if (result == JFileChooser.APPROVE_OPTION) {
				//saveXLDocument();
			}else {
				System.out.println("Wrong file extension");
			}
			
			break;
			
		case "Open":
			JFileChooser chooser = new JFileChooser();
			System.out.println("You selected open");
			int result1 = chooser.showOpenDialog(null);
			if (result1 == JFileChooser.APPROVE_OPTION) {
			File f = chooser.getSelectedFile();
				
				String filename = f.getAbsolutePath();
				
				System.out.println(filename);
				
				String extension = filename.substring(filename.lastIndexOf("."),filename.length());
				
				if (!extension.equals(".xlsx") & !extension.equals(".docx")) {
					
					System.out.println("Only supports excel and word documents " + extension);
					break;
					
				}else if (extension.equals(".docx")) {
					
						openWordDocument(filename);
						break;
					
					
					
				}else{
					
					openXLDocument(filename);
					break;												
				}
				
			}else {
				System.out.println("You pressed cancel");
				break;
			}
			
		case "Close":
			System.out.println("You selected close");
			break;
		case "Exit":
			System.out.println("You selected exit");
			System.exit(0);
			break;
		}
		
	}
	
	public void openWordDocument(String path) {
		
		try {
            
            FileInputStream fis = new FileInputStream(new File(path));

            XWPFDocument document = new XWPFDocument(fis);

            List<XWPFParagraph> paragraphs = document.getParagraphs();


            for (XWPFParagraph para : paragraphs) {
            	text.append(para.getText());
                System.out.println(para.getText());
            }
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
	                        	text.append(cell.getNumericCellValue() + " ");
	                            System.out.print(cell.getNumericCellValue() + "\t");
	                            break;
	                        case STRING:
	                        	text.append(cell.getStringCellValue() + " ");
	                            System.out.print(cell.getStringCellValue() + "\t");
	                            break;
	                    }
	                }
	                //text.append("");
	                System.out.println("");
	            }
	            file.close();
	        } 
	        catch (Exception e) 
	        {
	            e.printStackTrace();
	        }
	    }
		
	
	
	public void saveWordDocument() {
		
	}
	
	public void saveXLDocument() {
		
	}

	public void text2Speech() {
		// TODO Auto-generated method stub
		
	}
	
		
	

	

}
