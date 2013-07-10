package mBret.misc;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.Properties;
import java.util.Vector;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * 
 * @author mblackford mBret Bret Blackford
 * @deprecated
 */
public class WriteToExcel_OLD {

	Properties props = null;
	String filePath = "";
	String fileName = "Default.txt";
	String fullFileName = filePath + fileName;
	Workbook workbook = new HSSFWorkbook();
	Sheet sheet1;

	public WriteToExcel_OLD(Properties _props, Vector data) {
		props = _props;

		filePath = props.getProperty("out.file.path");
		fileName = props.getProperty("out.file.name");
		fullFileName = filePath + fileName + "-" + DateUtils.now() + ".xls";

		write(data);
	}

	public void write(Vector _inVec) {
		sheet1 = workbook.createSheet("Option Valuation");
		System.out.println("attempting to write Excel to ... [" + fullFileName
				+ "]");

		PrepDataForOutput prep = new PrepDataForOutput();
		Vector outData = prep.prepData(_inVec);

		// get an Iterator object for Vector using iterator() method.
		Iterator itrROW = outData.iterator();
		int rowCount = 0;

		System.out.println("Iterating through Vector elements...");
		while (itrROW.hasNext()) {

			Vector vecROW = (Vector) itrROW.next();
			Row row = sheet1.createRow(rowCount);
			int cellCount = 0;

			// get an Iterator object for Vector using iterator() method.
			Iterator column = vecROW.iterator();

			while (column.hasNext()) {
				String data = (String) column.next();
				Cell cell = row.createCell(cellCount);
				System.out
						.println("row[" + rowCount + "] column[" + data + "]");
				cell.setCellValue(data);

				cellCount++;
			}

			rowCount++;
		}

		format();

		try {
			FileOutputStream output = new FileOutputStream(fullFileName);
			workbook.write(output);
			output.close();
		} catch (FileNotFoundException e) {
			System.out.println("ERROR in WriteToExcel.write() *1*");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("ERROR in WriteToExcel.write() *2*");
			e.printStackTrace();
		}

	}

	public void format() {

		System.out.println("in WriteToExcel.format()");

		CellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);

		Cell cell;

		for (int i = 0; i < 41; i++) {
			cell = sheet1.getRow(0).getCell(i);
			cell.setCellStyle(style);
		}

	}

}

