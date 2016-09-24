package de.geyertobias.performance.demoapp.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.eclipse.core.runtime.Platform;

import de.geyertobias.performance.demoapp.parts.DirtyEvent;

public enum ModelProvider {
	INSTANCE;

	private List<Address> addresses;
	private List<ChangeListener> changeListeners;
	private List<UndoElement> undoStack;
	private boolean causeOOME = false;
	private boolean highAlloc = false;

	private ModelProvider() {
		String[] args = Platform.getApplicationArgs();

		for (String arg : args) {
			if (arg.equals("OOME")) {
				this.causeOOME = true;
			}
			if (arg.equals("ALLOC")) {
				this.highAlloc = true;
			}
		}

		addresses = new ArrayList<Address>();

		changeListeners = new ArrayList<ChangeListener>();

		undoStack = new ArrayList<UndoElement>();
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void addAddressWithUndo(String firstname, String lastname, String gender, boolean married) {
		Address address = new Address(firstname, lastname, gender, married);
		if(causeOOME || highAlloc) {
			undoStack.add(new UndoElement(addresses, address));
		} else {
			undoStack.add(new UndoElement(address));
		}
		addresses.add(address);
		if(!highAlloc) {
			for (ChangeListener listener : changeListeners) {
				listener.stateChanged(new ChangeEvent("refresh"));
				listener.stateChanged(new DirtyEvent("dirty"));
			}
		}
	}

	public void addAddressWithoutUndo(String firstname, String lastname, String gender, boolean married) {
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

	public void clearUndoStack() {
		undoStack.clear();
	}

}
