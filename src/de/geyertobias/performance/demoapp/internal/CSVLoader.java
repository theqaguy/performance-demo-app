package de.geyertobias.performance.demoapp.internal;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.eclipse.core.runtime.Platform;

import de.geyertobias.performance.demoapp.model.ModelProvider;

public class CSVLoader {

	ArrayList<String> csvLines;
	ArrayList<String[]> parsedLines;
	boolean causeOOME = false;
	boolean highCPUload = false;

	public CSVLoader() {
		String[] args = Platform.getApplicationArgs();

		for (String arg : args) {
			if (arg.equals("OOME")) {
				this.causeOOME = true;
			}
			if (arg.equals("CPULOAD")) {
				this.highCPUload = true;
			}
		}
	}

	public void loadFile(String selectedFile, ModelProvider modelProvider) {
		modelProvider.clearAddresses();
		if (causeOOME) {
			importFileWithOOME(selectedFile, modelProvider);
		} else if (highCPUload) {
			importFileHighCPULoad(selectedFile, modelProvider);
		} else {
			importFileHighPerformance(selectedFile, modelProvider);
		}
	}

	private void importFileHighPerformance(String selectedFile, ModelProvider modelProvider) {
		try (BufferedReader br = new BufferedReader(new FileReader(selectedFile))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] parsedLine = parseLine(line);
				if (parsedLine.length == 4) {
					modelProvider.addAddressFromOpenFile(parsedLine[0], parsedLine[1], parsedLine[2],
							Boolean.getBoolean(parsedLine[3]));
				}
			}
		} catch (IOException e) {
			System.out.println("Reading the file failed with an exception:");
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace().toString());
		}
		modelProvider.refreshUI();
	}

	private void importFileHighCPULoad(String selectedFile, ModelProvider modelProvider) {
		try (BufferedReader br = new BufferedReader(new FileReader(selectedFile))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] parsedLine = parseLine(line);
				if (parsedLine.length == 4) {
					modelProvider.addAddressFromOpenFile(parsedLine[0], parsedLine[1], parsedLine[2],
							Boolean.getBoolean(parsedLine[3]));
					modelProvider.refreshUI();
				}
			}
		} catch (IOException e) {
			System.out.println("Reading the file failed with an exception:");
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace().toString());
		}
	}

	private void importFileWithOOME(String selectedFile, ModelProvider modelProvider) {
		try (BufferedReader br = new BufferedReader(new FileReader(selectedFile))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] parsedLine = parseLine(line);
				if (parsedLine.length == 4) {
					modelProvider.addAddress(parsedLine[0], parsedLine[1], parsedLine[2],
							Boolean.getBoolean(parsedLine[3]));
				}
			}
		} catch (IOException e) {
			System.out.println("Reading the file failed with an exception:");
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace().toString());
		}
		modelProvider.refreshUI();
	}

	String[] parseLine(String line) {
		return line.split(",");
	}
}
