package mBret.misc;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.Properties;
import java.util.Vector;

import mBret.database.SelectOptionsData;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * Class is used to write output to an Excel file using the 
 * Apache POI - the Java API for Microsoft Documents.  The Apache POI
 * provides pure Java libraries for reading and writing files in Microsoft 
 * Office formats, such as Word, PowerPoint and Excel.
 * 
 * @author mblackford Michael Bret Blackford (mBret)
 *
 */
public class WriteToExcel {

	Properties props = null;
	String filePath = "";
	String fileName = "Default.txt";
	String fullFileName = filePath + fileName;
	Workbook workbook = new HSSFWorkbook();
	Sheet sheet1;
	int rowCount = 0;
	int numColumns = 0;

	/**
	 * Requires a Properties file and the Vector of data to be passed in.
	 * Method will then attempt to format the data in the Vector into an
	 * Excel file and write it to the location and name specified in the
	 * Properties file passed in.
	 *  
	 * @param _props 
	 * @param data
	 */
	public WriteToExcel(Properties _props, Vector data) {
		props = _props;
		
		filePath =  props.getProperty("out.file.path");
		fileName = props.getProperty("out.file.name");
		fullFileName = filePath + fileName + "-" + DateUtils.now() + ".xls";
		
		write(data);
	}
	
	
	public void write(Vector _inVec) {
		sheet1 = workbook.createSheet("Option Valuation");
		System.out.println("in WriteToExcel.write() ");
		System.out.println("  attempting to write Excel to ... [" + fullFileName + "]");
		
		
		// get an Iterator object for Vector using iterator() method.
		Iterator itrROW = _inVec.iterator();
		
		// Obtain column headers
		PrepDataForOutput prep = new PrepDataForOutput();
		addHeaders(prep.prepHeaders() );
		
		
		System.out.println("  Iterating through Vector elements...");
		while (itrROW.hasNext()) {
			
			Row row = sheet1.createRow(rowCount);
			int cellCount = 0;
			
			SelectOptionsData dataROW =  (SelectOptionsData) itrROW.next();
			
			Cell cellA = row.createCell(cellCount++);
			cellA.setCellValue((String) StringUtil.
					check(dataROW.trade));
			Cell cellB = row.createCell(cellCount++);
			cellB.setCellValue((String) StringUtil.
					check(dataROW.tradebook));
			Cell cellC = row.createCell(cellCount++);
			cellC.setCellValue((String) StringUtil.
					check(dataROW.product));
			Cell cellD = row.createCell(cellCount++);
			cellD.setCellValue((String) StringUtil.
					check(dataROW.settlementDate));
			Cell cellE = row.createCell(cellCount++);
			cellE.setCellValue((String) StringUtil.
					check(dataROW.counterparty));
			Cell cellF = row.createCell(cellCount++);
			cellF.setCellValue((String) StringUtil.
					check(dataROW.execution));
			Cell cellG = row.createCell(cellCount++);
			cellG.setCellValue((String) StringUtil.
					check(dataROW.positionType));
			Cell cellH = row.createCell(cellCount++);
			cellH.setCellValue((String) StringUtil.
					check(dataROW.priceStatus));
			Cell cellI = row.createCell(cellCount++);
			cellI.setCellValue((Double) new Double(StringUtil
					.check(dataROW.quantity)));
			Cell cellJ = row.createCell(cellCount++);
			cellJ.setCellValue((Double) new Double(StringUtil
					.check(dataROW.priceQuantity)));
			Cell cellK = row.createCell(cellCount++);
			cellK.setCellValue((Double) new Double(StringUtil
					.check(dataROW.value)));
			Cell cellL = row.createCell(cellCount++);
			cellL.setCellValue((Double) new Double(StringUtil
					.check(dataROW.optionValue)));
			Cell cellM = row.createCell(cellCount++);
			cellM.setCellValue((Double) new Double(StringUtil
					.check(dataROW.marketPrice)));
			Cell cellN = row.createCell(cellCount++);
			cellN.setCellValue((Double) new Double(StringUtil
					.check(dataROW.optionValue_Black)));
			Cell cellO = row.createCell(cellCount++);
			cellO.setCellValue((Double) new Double(StringUtil
					.check(dataROW.optionValue_BlackScholes)));
			Cell cellP = row.createCell(cellCount++);
			cellP.setCellValue((Double) new Double(StringUtil
					.check(dataROW.delta)));
			Cell cellQ = row.createCell(cellCount++);
			cellQ.setCellValue((Double) new Double(StringUtil
					.check(dataROW.gamma)));
			Cell cellR = row.createCell(cellCount++);
			cellR.setCellValue((Double) new Double(StringUtil
					.check(dataROW.theta)));
			Cell cellS = row.createCell(cellCount++);
			cellS.setCellValue((Double) new Double(StringUtil
					.check(dataROW.vega)));
			Cell cellT = row.createCell(cellCount++);
			cellT.setCellValue((Double) new Double(StringUtil
					.check(dataROW.rho)));
			Cell cellU = row.createCell(cellCount++);
			cellU.setCellValue((Double) new Double(StringUtil
					.check(dataROW.delta_s)));
			Cell cellV = row.createCell(cellCount++);
			cellV.setCellValue((Double) new Double(StringUtil
					.check(dataROW.gamma_s)));
			Cell cellW = row.createCell(cellCount++);
			cellW.setCellValue((Double) new Double(StringUtil
					.check(dataROW.theta_s)));
			Cell cellX = row.createCell(cellCount++);
			cellX.setCellValue((Double) new Double(StringUtil
					.check(dataROW.vega_s)));
			Cell cellY = row.createCell(cellCount++);
			cellY.setCellValue((Double) new Double(StringUtil
					.check(dataROW.rho_s)));
			Cell cellZ = row.createCell(cellCount++);
			cellZ.setCellValue((Double) new Double(StringUtil
					.check(dataROW.volatility)));
			Cell cellAA = row.createCell(cellCount++);
			cellAA.setCellValue((Double) new Double(StringUtil
					.check(dataROW.strikePrice)));
			Cell cellAB = row.createCell(cellCount++);
			cellAB.setCellValue((String) StringUtil.
					check(dataROW.expDate));
			Cell cellAC = row.createCell(cellCount++);
			cellAC.setCellValue((String) StringUtil.
					check(dataROW.begTime));
			Cell cellAD = row.createCell(cellCount++);
			cellAD.setCellValue((String) StringUtil.
					check(dataROW.endTime));
			Cell cellAE = row.createCell(cellCount++);
			cellAE.setCellValue((Double) new Double(StringUtil
					.check(dataROW.price)));
			Cell cellAF = row.createCell(cellCount++);
			cellAF.setCellValue((String) StringUtil.
					check(dataROW.location));
			Cell cellAG = row.createCell(cellCount++);
			cellAG.setCellValue((String) StringUtil.
					check(dataROW.optionType));
			Cell cellAH = row.createCell(cellCount++);
			cellAH.setCellValue((String) StringUtil.
					check(dataROW.optionStyle));
			Cell cellAI = row.createCell(cellCount++);
			cellAI.setCellValue((String) StringUtil
					.check(dataROW.accountingMethod));
			Cell cellAJ = row.createCell(cellCount++);
			cellAJ.setCellValue((Double) new Double(StringUtil
					.check(dataROW.strike_s)));
			Cell cellAK = row.createCell(cellCount++);
			cellAK.setCellValue((Double) new Double(StringUtil
					.check(dataROW.market_s)));
			Cell cellAL = row.createCell(cellCount++);
			cellAL.setCellValue((Double) new Double(StringUtil
					.check(dataROW.intRate_s)));
			Cell cellAM = row.createCell(cellCount++);
			cellAM.setCellValue((Double) new Double(StringUtil
					.check(dataROW.expTime_s)));
			Cell cellAN = row.createCell(cellCount++);
			cellAN.setCellValue((Double) new Double(StringUtil
					.check(dataROW.expTime)));
			Cell cellAO = row.createCell(cellCount++);
			cellAO.setCellValue((String) StringUtil.
					check(dataROW.description));
			
		
			rowCount++;
		}
		
		format();
		
		try {
			//This is where we actually write the Workbook to a file
			FileOutputStream output = new FileOutputStream(fullFileName);
			workbook.write(output);
			output.close();
		} catch (FileNotFoundException e) {
			System.out.println("ERROR in WriteToExcel.write() *1*");
			e.printStackTrace();
		} catch(Exception e) {
			System.out.println("ERROR in WriteToExcel.write() *2*");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Adds the header row (Column Titles) to the Excel spreadsheet
	 * @param _inVec
	 */
	private void addHeaders(Vector _inVec) {
		
		Row row = sheet1.createRow(rowCount);
		int cellCount_H = 0;
		numColumns = _inVec.size();
		
		// get an Iterator object for Vector using iterator() method.
		Iterator column = _inVec.iterator();
		
		while (column.hasNext()) {
			String data = (String) column.next();
			Cell cell = row.createCell(cellCount_H);
			cell.setCellValue(data);
			
			cellCount_H++;
		}
		rowCount++;
	}
	
	
	/**
	 * Method provides background color to the header row.
	 */
	public void format() {
		
		System.out.println("in WriteToExcel.format()");
		
		CellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		
		Cell cell;
		
		for(int i=0; i<numColumns; i++){
			cell = sheet1.getRow(0).getCell(i);
			cell.setCellStyle(style);
		}

	}
	

}
