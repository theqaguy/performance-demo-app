package de.geyertobias.performance.demoapp.parts;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.model.application.ui.MDirtyable;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

import de.geyertobias.performance.demoapp.internal.ContentProvider;
import de.geyertobias.performance.demoapp.model.Address;
import de.geyertobias.performance.demoapp.model.ModelProvider;

public class AddressListPart implements ChangeListener {

	private TableViewer tableViewer;

	@Inject
	private MDirtyable dirty;

	@PostConstruct
	public void createComposite(Composite parent) {
		parent.setLayout(new GridLayout(1, false));

		tableViewer = new TableViewer(parent, SWT.VIRTUAL);
		
		createColumns();
		tableViewer.getTable().setHeaderVisible(true);
		tableViewer.getTable().setLinesVisible(true); 
		
		tableViewer.setContentProvider(new ContentProvider(tableViewer));

		// special settings for the lazy content provider
		tableViewer.setUseHashlookup(true);

		List<Address> addresses = ModelProvider.INSTANCE.getAddresses();
		tableViewer.setInput(addresses);
		tableViewer.setItemCount(addresses.size()); 

		tableViewer.getTable().setLayoutData(new GridData(GridData.FILL_BOTH));
		
		ModelProvider.INSTANCE.addChangeListener(this);
	}

	private void createColumns() {
		TableViewerColumn colFirstName = new TableViewerColumn(tableViewer, SWT.NONE);
		colFirstName.getColumn().setWidth(180);
		colFirstName.getColumn().setText("Firstname");
		colFirstName.setLabelProvider(new ColumnLabelProvider() {
		  @Override
		  public String getText(Object element) {
		    Address p = (Address) element;
		    return p.getFirstName();
		  }
		});		
		
		TableViewerColumn colLastName = new TableViewerColumn(tableViewer, SWT.NONE);
		colLastName.getColumn().setWidth(180);
		colLastName.getColumn().setText("Lastname");
		colLastName.setLabelProvider(new ColumnLabelProvider() {
		  @Override
		  public String getText(Object element) {
		    Address p = (Address) element;
		    return p.getLastName();
		  }
		});	
		
		TableViewerColumn colGender = new TableViewerColumn(tableViewer, SWT.NONE);
		colGender.getColumn().setWidth(40);
		colGender.getColumn().setText("Gender");
		colGender.setLabelProvider(new ColumnLabelProvider() {
		  @Override
		  public String getText(Object element) {
		    Address p = (Address) element;
		    return p.getGender();
		  }
		});	
		
		TableViewerColumn colMarried = new TableViewerColumn(tableViewer, SWT.NONE);
		colMarried.getColumn().setWidth(40);
		colMarried.getColumn().setText("Married");
		colMarried.setLabelProvider(new ColumnLabelProvider() {
		  @Override
		  public String getText(Object element) {
		    Address p = (Address) element;
		    return String.valueOf(p.isMarried());
		  }
		});	
	}

	public TableViewer getViewer() {
		return tableViewer;
	}

	private void refresh() {
		List<Address> addresses = ModelProvider.INSTANCE.getAddresses();
		tableViewer.setInput(addresses);
		tableViewer.setItemCount(addresses.size()); 
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
		if(e instanceof DirtyEvent) {
			dirty.setDirty(true);
		} else {
			refresh();
		}
	}
}