package de.geyertobias.performance.demoapp.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

import de.geyertobias.performance.demoapp.internal.CSVLoader;
import de.geyertobias.performance.demoapp.model.ModelProvider;
public class OpenHandler {

	@Execute
	public void execute(Shell shell){
		FileDialog dialog = new FileDialog(shell);
		dialog.setText("Select CSV file");
		dialog.setFilterExtensions(new String[] {"*.csv"});
		String selectedFile = dialog.open();
		
		if (selectedFile != null) {
			// start import of CSV file
			CSVLoader csvLoader = new CSVLoader();
			csvLoader.loadFile(selectedFile, ModelProvider.INSTANCE);
		}
	}
}
