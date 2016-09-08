package de.geyertobias.performance.demoapp.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.eclipse.core.runtime.Platform;

public enum ModelProvider {
	INSTANCE;

	private List<Address> addresses;
	private List<ChangeListener> changeListeners;
	private List<UndoElement> undoStack;
	private boolean causeOOME = false;

	private ModelProvider() {
		String[] args = Platform.getApplicationArgs();

		for (String arg : args) {
			if (arg.equals("OOME")) {
				this.causeOOME = true;
			}
		}

		addresses = new ArrayList<Address>();

		changeListeners = new ArrayList<ChangeListener>();

		undoStack = new ArrayList<UndoElement>();
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void addAddress(String firstname, String lastname, String gender, boolean married) {
		Address address = new Address(firstname, lastname, gender, married);
		if(causeOOME) {
			undoStack.add(new UndoElement(addresses, address));
		} else {
			undoStack.add(new UndoElement(address));
		}
		addresses.add(address);
		for (ChangeListener listener : changeListeners) {
			listener.stateChanged(new ChangeEvent("refresh"));
		}
	}

	public void addAddressFromOpenFile(String firstname, String lastname, String gender, boolean married) {
		Address address = new Address(firstname, lastname, gender, married);
		addresses.add(address);
	}

	public void refreshUI() {
		for (ChangeListener listener : changeListeners) {
			listener.stateChanged(new ChangeEvent("refresh"));
		}
	}

	public void addChangeListener(ChangeListener changeListener) {
		changeListeners.add(changeListener);
	}

	public void clearAddresses() {
		addresses = new ArrayList<>();
	}

}
