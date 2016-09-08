package de.geyertobias.performance.demoapp.model;

import java.util.List;

public class UndoElement {

	private List<Address> previousState;
	private Address addressToRemove;
	private String toString;

	public UndoElement(List<Address> addresses, Address toRemove) {
		previousState = addresses;
		addressToRemove = toRemove;
		StringBuilder sb = new StringBuilder();
		for(Address address: addresses) {
			sb.append("["+address.getFirstName()+",");
			sb.append(address.getLastName()+",");
			sb.append(address.getGender()+",");
			sb.append(address.isMarried()+"]");
		}
		toString = sb.toString();
	}

	public UndoElement(Address address) {
		previousState = null;
		addressToRemove = address;
		toString = address.toString();
	}

	public List<Address> getPreviousState() {
		return previousState;
	}
	
	public Address getAddressToRemove() {
		return addressToRemove;
	}

	public String toString() {
		return toString;
	}

}
