package de.geyertobias.performance.demoapp.internal;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import de.geyertobias.performance.demoapp.model.ModelProvider;

public class CSVImporter {

	ArrayList<String> csvLines;
	ArrayList<String[]> parsedLines;
	ArrayList<String> debugOutput = new ArrayList<>();
	boolean causeOOME = false;
	boolean highCPUload = false;
	
	public CSVImporter(boolean causeOOME, boolean highCPUload) {
		this.causeOOME = causeOOME;
		this.highCPUload = highCPUload;
	}

	public void importFile(String selectedFile, ModelProvider modelProvider) {
		if(causeOOME) {
			importFileWithOOME(selectedFile, modelProvider);
		} else if (highCPUload) {
			importFileWithoutOOME(selectedFile, modelProvider);
		} else {
			importFileHighPerformance(selectedFile, modelProvider);
		}
	}
	
	private void importFileHighPerformance(String selectedFile, ModelProvider modelProvider) {
		try (BufferedReader br = new BufferedReader(new FileReader(selectedFile))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		    	String[] parsedLine = parseLine(line);
				if(parsedLine.length==4) {
					modelProvider.addAddressWithoutUIUpdate(parsedLine[0], parsedLine[1], parsedLine[2], Boolean.getBoolean(parsedLine[3]));
					modelProvider.refreshUI();
				}
		    }
		} catch (IOException e) {
			System.out.println("Reading the file failed with an exception:");
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace().toString());
		}		
	}
	
	private void importFileWithoutOOME(String selectedFile, ModelProvider modelProvider) {
		try (BufferedReader br = new BufferedReader(new FileReader(selectedFile))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		    	String[] parsedLine = parseLine(line);
				if(parsedLine.length==4) {
					modelProvider.addAddress(parsedLine[0], parsedLine[1], parsedLine[2], Boolean.getBoolean(parsedLine[3]));
				}
		    }
		} catch (IOException e) {
			System.out.println("Reading the file failed with an exception:");
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace().toString());
		}
	}
	
	private void importFileWithOOME(String selectedFile, ModelProvider modelProvider) {
		addDebugOutput("Starting import of file "+selectedFile);
		addDebugOutput("Setting up List for csv Lines");
		csvLines = new ArrayList<>();
		addDebugOutput("Setting up List for parsed Lines");
		parsedLines = new ArrayList<>();
		
		addDebugOutput("Attempting to read file");
		try (BufferedReader br = new BufferedReader(new FileReader(selectedFile))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		    	addDebugOutput("Read line |"+line+"| from file. Storing it for later parsing.");
		    	// store line for later parsing
		    	csvLines.add(line);
		    }
		} catch (IOException e) {
			addDebugOutput("Reading the file failed with an exception:");
			addDebugOutput(e.getMessage());
			addDebugOutput(e.getStackTrace().toString());
		}
		
		addDebugOutput("Parsing stored lines");
		
		for(String line: csvLines) {
			addDebugOutput("Parsing line |"+line+"|");
			parsedLines.add(parseLine(line));
		}
		
		addDebugOutput("Adding parsed lines to model");
		int i = 0;
		for(String[] parsedLine : parsedLines) {
			addDebugOutput("Checking if line "+i+" was parsed correctly");
			if(parsedLine.length==4) {
				addDebugOutput("Adding line "+i+" to model - parameters: "+parsedLine[0]+", "+parsedLine[1]+", "+parsedLine[2]+", "+parsedLine[3]);
				modelProvider.addAddress(parsedLine[0], parsedLine[1], parsedLine[2], Boolean.getBoolean(parsedLine[3]));
			}
			i++;
		}
	}

	private void addDebugOutput(String string) {
		debugOutput.add(new Date().toString()+ " - "+string);
	}

	String[] parseLine(String line) {
		return line.split(",");
	}
}
