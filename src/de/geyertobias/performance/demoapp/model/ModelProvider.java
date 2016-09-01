package de.geyertobias.performance.demoapp.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public enum ModelProvider {
	  INSTANCE;

	  private List<Address> addresses;
	  private List<ChangeListener> changeListeners;

	  private ModelProvider() {
	    addresses = new ArrayList<Address>();
	    
	    changeListeners = new ArrayList<ChangeListener>();
	  }

	  public List<Address> getAddresses() {
	    return addresses;
	  }
	  
	  public void addAddress(String firstname, String lastname, String gender, boolean married) {
		  addresses.add(new Address(firstname, lastname, gender, married));
		  for(ChangeListener listener: changeListeners) {
			  listener.stateChanged(new ChangeEvent("refresh"));
		  }
	  }

	  public void addAddressWithoutUIUpdate(String firstname, String lastname, String gender, boolean married) {
		  addresses.add(new Address(firstname, lastname, gender, married));
	  }
	  
	  public void refreshUI() {
		  for(ChangeListener listener: changeListeners) {
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
