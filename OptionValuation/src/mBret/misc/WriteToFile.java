package mBret.misc;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Vector;

import mBret.database.SelectOptionsData;

/**
 * Class used to write output from a Vector to a text file. File
 * will be pipe (|) delimited for use with Excel.
 * 
 * @author mblackford mBret Bret Blackford
 *
 */
public class WriteToFile {
	
	String filePath = "\\\\STLSRV2\\midoffice\\";
	String fileName = "optionValuationCheck.";
	String fullFileName = filePath + fileName + DateUtils.now() + ".txt";
	
	Properties props = null;
	File file;
	
	public WriteToFile(Properties _props) {
		props = _props;
		
		filePath =  props.getProperty("out.file.path");
		fileName = props.getProperty("out.file.name");
		file = new File(fullFileName);
	}

	public void write(Vector output) {
		try {
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write(new SelectOptionsData().printHeaders());
			
			// get an Iterator object for Vector using iterator() method.
			Iterator itr = output.iterator();
			
			System.out.println("Iterating through Vector elements...");
			while (itr.hasNext()) {
				SelectOptionsData data = (SelectOptionsData) itr.next();
				
				bw.write("\n");
				bw.write(data.toString());	
			}
			
			bw.close();
			
		} catch (IOException e) {
			System.out.println("ERROR in WriteToFile.write()");
			e.printStackTrace();
		}
	}

}
