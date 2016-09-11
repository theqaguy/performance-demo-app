package de.geyertobias.performance.demoapp.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.model.application.ui.MDirtyable;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

public class AddAddressPart {

	private Text firstNameInput;
	private Text lastNameInput;
	private Text genderInput;
	private Button addAddress;

	@Inject
	private MDirtyable dirty;

	@PostConstruct
	public void createComposite(Composite parent) {
		parent.setLayout(new GridLayout(2, false));
		
		//TODO: add labels!

		firstNameInput = new Text(parent, SWT.BORDER);
		firstNameInput.setMessage("First name");
//		firstNameInput.addModifyListener(new ModifyListener() {
//			@Override
//			public void modifyText(ModifyEvent e) {
//				dirty.setDirty(true);
//			}
//		});
		firstNameInput.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		lastNameInput = new Text(parent, SWT.BORDER);
		lastNameInput.setMessage("Last name");
//		firstNameInput.addModifyListener(new ModifyListener() {
//			@Override
//			public void modifyText(ModifyEvent e) {
//				dirty.setDirty(true);
//			}
//		});
		lastNameInput.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		genderInput = new Text(parent, SWT.BORDER);
		genderInput.setMessage("Gender");
//		firstNameInput.addModifyListener(new ModifyListener() {
//			@Override
//			public void modifyText(ModifyEvent e) {
//				dirty.setDirty(true);
//			}
//		});
		genderInput.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		addAddress = new Button(parent, SWT.PUSH);
		addAddress.setText("Add to list");
		addAddress.addListener(SWT.Selection, new Listener() {
		      public void handleEvent(Event e) {
		        switch (e.type) {
		        case SWT.Selection:
		          System.out.println("Button pressed");
		          break;
		        }
		      }
		    });

	}

	@Focus
	public void setFocus() {
		firstNameInput.setFocus();
	}

	@Persist
	public void save() {
		dirty.setDirty(false);
	}
}