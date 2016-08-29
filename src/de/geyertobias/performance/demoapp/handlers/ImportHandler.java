package de.geyertobias.performance.demoapp.handlers;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

import de.geyertobias.performance.demoapp.internal.CSVImporter;
import de.geyertobias.performance.demoapp.model.ModelProvider;
public class ImportHandler {

	@Inject
	EPartService partService;
	
	@Execute
	public void execute(Shell shell){
		FileDialog dialog = new FileDialog(shell);
		dialog.setText("Select CSV file for import");
		dialog.setFilterExtensions(new String[] {"*.csv"});
		String selectedFile = dialog.open();
		
		if (selectedFile != null) {
			// start import of CSV file
			CSVImporter csvImporter = new CSVImporter();
			csvImporter.importFile(selectedFile, ModelProvider.INSTANCE);
		}
	}
}
