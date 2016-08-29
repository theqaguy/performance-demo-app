package de.geyertobias.performance.demoapp.internal;

import de.geyertobias.performance.demoapp.model.ModelProvider;
import de.geyertobias.performance.demoapp.parts.SamplePart;

public class CSVImporter {

	public void importFile(String selectedFile, ModelProvider modelProvider) {
		modelProvider.addAddress("f", "l", "other", false);
		
	}

}
