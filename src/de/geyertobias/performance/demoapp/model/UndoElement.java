package de.geyertobias.performance.demoapp.model;

import java.util.ArrayList;
import java.util.List;

public class UndoElement {

	private List<Address> previousState;
	private int hashCodeToRemove;
	private String toString;

	public UndoElement(List<Address> addresses, Address toRemove) {
		previousState = new ArrayList<>();
		previousState.addAll(addresses);
		hashCodeToRemove = toRemove.hashCode();
		StringBuilder sb = new StringBuilder();
		for(Address address: addresses) {
			sb.append("["+address.getFirstName()+",");
			sb.append(address.getLastName()+",");
			sb.append(address.getGender()+",");
			sb.append(address.getAge()+",");
			sb.append(address.isMarried()+"]");
		}
		toString = sb.toString();
	}

	public UndoElement(Address address) {
		previousState = null;
		hashCodeToRemove = address.hashCode();
		toString = address.toString();
	}

	public List<Address> getPreviousState() {
		return previousState;
	}
	
	public int getHashCodeToRemove() {
		return hashCodeToRemove;
	}

	public String toString() {
		return toString;
	}

}
