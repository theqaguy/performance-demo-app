package de.geyertobias.performance.demoapp.parts;

import javax.swing.event.ChangeEvent;

public class DirtyEvent extends ChangeEvent {

	private static final long serialVersionUID = -1763366537983342063L;

	public DirtyEvent(Object arg0) {
		super(arg0);
	}

}
