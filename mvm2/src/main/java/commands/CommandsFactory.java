package commands;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.PatternSyntaxException;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileFilter;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import com.sun.speech.freetts.FreeTTS;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

import text2Speech.GUI2;


public class CommandsFactory {
	
	
	JTextArea text;
	
	public void createCommand(String command,JTextArea textArea) {
		text = textArea;
		switch (command) {
		case "File":
			break;
		case "SaveXL":
			//JFileChooser save = new JFileChooser(new File("C:\\"));
			System.out.println("You selected Save file into excel");
			
			saveXLDocument();
			
			break;
			
		case "SaveWord":
			System.out.println("You selected Save file into word");
			saveWordDocument();
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
				
				text.setText("");
				
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
			
			
		
		case "Atbash Encode/Decode":
			System.out.println("You selected Atbash Encode");
			if (text.getText() != null){
				String encoded = Atbashencrypt(text.getText());
				text.setText(encoded);
				break;
			}else {
				break;
			}
		case "Rot13 Encode/Decode":
			System.out.println("You selected Rot13 Encode");
			if (text.getText() != null){
				String decoded = Rot13decrypt(text.getText());
				text.setText(decoded);
				break;
			}else {
				break;
			}
			
		
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
		String s = text.getText();
	      XWPFDocument document = new XWPFDocument(); 
	      
	      //Write the Document in file system
	      FileOutputStream out = null;
		try {
			out = new FileOutputStream(new File("C:\\Users\\shargath\\Desktop\\par.docx"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	        
	      //create Paragraph
	      XWPFParagraph paragraph = document.createParagraph();
	      XWPFRun run = paragraph.createRun();
	      run.setText(s);
				
	      try {
			document.write(out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      try {
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      System.out.println("par.docx written successfully to Desktop");
	      
	}
	
	public void saveXLDocument() {
		 XSSFWorkbook workbook = new XSSFWorkbook();

		    //Create a blank sheet
		    XSSFSheet sheet = workbook.createSheet("New Text");

		    String newText = text.getText();
		    String[] splitArray = null;
		    try {
		        splitArray = newText.split("\\s+");
		    } catch (PatternSyntaxException ex) {
		       
		    }
		    	
		    //This data needs to be written (Object[])
		    Map<String, Object[]> data = new TreeMap<String, Object[]>();
		    for (int i = 0;i < splitArray.length; i += 2) {
		    	data.put(Integer.toString(i), new Object[]{splitArray[i],splitArray[i + 1]});
			    
		    }
		    

		    //Iterate over data and write to sheet
		    Set<String> keyset = data.keySet();

		    int rownum = 0;
		    for (String key : keyset) 
		    {
		        //create a row of excelsheet
		        Row row = sheet.createRow(rownum++);

		        //get object array of prerticuler key
		        Object[] objArr = data.get(key);

		        int cellnum = 0;

		        for (Object obj : objArr) 
		        {
		            Cell cell = row.createCell(cellnum++);
		            if (obj instanceof String) 
		            {
		                cell.setCellValue((String) obj);
		            }
		            else if (obj instanceof Integer) 
		            {
		                cell.setCellValue((Integer) obj);
		            }
		        }
		    }
		    try 
		    {
		        //Write the workbook in file system
		        FileOutputStream out = new FileOutputStream(new File("C:\\Users\\shargath\\Desktop\\par.xlsx"));
		        workbook.write(out);
		        out.close();
		        System.out.println("par.xlsx written successfully on disk.");
		    } 
		    catch (Exception e)
		    {
		        e.printStackTrace();
		    }
	}

	public void text2Speech(String msg) {
		Voice voice;
		System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
	    voice = VoiceManager.getInstance().getVoice("kevin16");
	    if (voice != null) {
	        voice.allocate();// Allocating Voice
	        try {
	            voice.setRate(190);// Setting the rate of the voice
	            voice.setPitch(150);// Setting the Pitch of the voice
	            voice.setVolume(2);// Setting the volume of the voice
	            voice.speak(msg);// Calling speak() method

	        } catch (Exception e1) {
	            e1.printStackTrace();
	        }

	    } else {
	        throw new IllegalStateException("Cannot find voice: kevin16");
	    }
	}
	
	
	
	private String Rot13decrypt(String text2) {
		// TODO Auto-generated method stub
		StringBuilder decoded = new StringBuilder();
        for (int i = 0; i < text2.length(); i++) {
            char c = text2.charAt(i);
            if       (c >= 'a' && c <= 'm') c += 13;
            else if  (c >= 'A' && c <= 'M') c += 13;
            else if  (c >= 'n' && c <= 'z') c -= 13;
            else if  (c >= 'N' && c <= 'Z') c -= 13;
            decoded.append(c);
        }
        return decoded.toString();
	}

	private String Atbashencrypt(String message) {
		 message = message.toLowerCase();
	        StringBuilder encoded = new StringBuilder();
	        for(char c : message.toCharArray())
	        {
	            if(Character.isLetter(c))
	            {
	                c = (char) ('a' + ('z' - c));
	                encoded.append(c);
	            }
	            else
	            {
	                encoded.append(c);
	            }
	          
	        }
	        return encoded.toString();
	}
	
	
	
}
