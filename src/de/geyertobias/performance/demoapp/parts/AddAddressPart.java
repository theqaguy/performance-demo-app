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
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

import de.geyertobias.performance.demoapp.model.ModelProvider;

public class AddAddressPart {

	private Text firstNameInput;
	private Text lastNameInput;
	private Text genderInput;
	private Button addAddress;
	private Button marriedButton;

	@Inject
	private MDirtyable dirty;

	@PostConstruct
	public void createComposite(Composite parent) {
		parent.setLayout(new GridLayout(2, false));
		
		//TODO: add labels!
		Label firstNameLabel = new Label(parent, SWT.NONE);
		firstNameLabel.setText("First name: ");

		firstNameInput = new Text(parent, SWT.BORDER);
//		firstNameInput.setMessage("First name");
//		firstNameInput.addModifyListener(new ModifyListener() {
//			@Override
//			public void modifyText(ModifyEvent e) {
//				dirty.setDirty(true);
//			}
//		});
		firstNameInput.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Label lastNameLabel = new Label(parent, SWT.NONE);
		lastNameLabel.setText("Last name: ");
		
		lastNameInput = new Text(parent, SWT.BORDER);
//		lastNameInput.setMessage("Last name");
//		firstNameInput.addModifyListener(new ModifyListener() {
//			@Override
//			public void modifyText(ModifyEvent e) {
//				dirty.setDirty(true);
//			}
//		});
		lastNameInput.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Label genderLabel = new Label(parent, SWT.NONE);
		genderLabel.setText("Gender: ");
		
		genderInput = new Text(parent, SWT.BORDER);
		genderInput.setMessage("Male / Female / Other");
//		firstNameInput.addModifyListener(new ModifyListener() {
//			@Override
//			public void modifyText(ModifyEvent e) {
//				dirty.setDirty(true);
//			}
//		});
		genderInput.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Label marriedLabel = new Label(parent, SWT.NONE);
		marriedLabel.setText("Married: ");
		
		marriedButton = new Button(parent, SWT.CHECK);
//		firstNameInput.addModifyListener(new ModifyListener() {
//			@Override
//			public void modifyText(ModifyEvent e) {
//				dirty.setDirty(true);
//			}
//		});
		marriedButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		
		addAddress = new Button(parent, SWT.PUSH);
		addAddress.setText("Add to list");
		addAddress.addListener(SWT.Selection, new Listener() {
		      public void handleEvent(Event e) {
		        switch (e.type) {
		        case SWT.Selection:
		          System.out.println("Button pressed");
		          ModelProvider modelProvider = ModelProvider.INSTANCE;
					modelProvider.addAddressWithUndo(firstNameInput.getText(), lastNameInput.getText(), genderInput.getText(), marriedButton.getSelection());
					modelProvider.refreshUI();
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