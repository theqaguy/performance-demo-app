package de.geyertobias.performance.demoapp.internal;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import de.geyertobias.performance.demoapp.model.ModelProvider;

public class CSVImporter {

	ArrayList<String> csvLines;
	ArrayList<String[]> parsedLines;
	
	public void importFile(String selectedFile, ModelProvider modelProvider) {
		csvLines = new ArrayList<>();
		parsedLines = new ArrayList<>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(selectedFile))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		    	// store line for later parsing
		    	csvLines.add(line);
		    }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(String line: csvLines) {
			parsedLines.add(parseLine(line));
		}
		
		for(String[] parsedLine : parsedLines) {
			if(parsedLine.length==4) {
				modelProvider.addAddress(parsedLine[0], parsedLine[1], parsedLine[2], Boolean.getBoolean(parsedLine[3]));
			}
		}
		
	}

	String[] parseLine(String line) {
		return line.split(",");
	}
}
