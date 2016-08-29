package de.geyertobias.performance.demoapp.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.model.application.ui.MDirtyable;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;

import de.geyertobias.performance.demoapp.model.ModelProvider;

public class SamplePart implements ChangeListener {

	private Text txtInput;
	private TableViewer tableViewer;

	@Inject
	private MDirtyable dirty;

	@PostConstruct
	public void createComposite(Composite parent) {
		parent.setLayout(new GridLayout(1, false));

		txtInput = new Text(parent, SWT.BORDER);
		txtInput.setMessage("Enter text to mark part as dirty");
		txtInput.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				dirty.setDirty(true);
			}
		});
		txtInput.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		tableViewer = new TableViewer(parent);

		// ContentProvider cp = new ContentProvider();

		tableViewer.setContentProvider(new ArrayContentProvider());
		tableViewer.setInput(ModelProvider.INSTANCE.getAddresses());
		// cp.getElements(tableViewer));

		tableViewer.getTable().setLayoutData(new GridData(GridData.FILL_BOTH));
		
		ModelProvider.INSTANCE.addChangeListener(this);
	}

	public TableViewer getViewer() {
		return tableViewer;
	}

	private void refresh() {
		tableViewer.refresh();
		Table table = tableViewer.getTable();
		for (int i = 0, n = table.getColumnCount(); i < n; i++) {
			table.getColumn(i).pack();
		}
	}

	@Focus
	public void setFocus() {
		tableViewer.getTable().setFocus();
	}

	@Persist
	public void save() {
		dirty.setDirty(false);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		refresh();
	}
}