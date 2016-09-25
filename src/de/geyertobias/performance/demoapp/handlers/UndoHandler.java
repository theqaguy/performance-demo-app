package de.geyertobias.performance.demoapp.handlers;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

import de.geyertobias.performance.demoapp.model.ModelProvider;

public class UndoHandler {

	@CanExecute
	public boolean canExecute(EPartService partService) {
		return ModelProvider.INSTANCE.canUndo();
	}

	@Execute
	public void execute(EPartService partService) {
		ModelProvider.INSTANCE.undo();
	}
	
}
