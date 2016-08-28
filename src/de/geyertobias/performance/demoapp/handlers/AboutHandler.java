package de.geyertobias.performance.demoapp.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

public class AboutHandler {
	@Execute
	public void execute(Shell shell) {
		MessageDialog.openInformation(shell, "About", "Demoapp by Tobias Geyer / @the_qa_guy\nUsed to showcase performance analysis tools");
	}
}
