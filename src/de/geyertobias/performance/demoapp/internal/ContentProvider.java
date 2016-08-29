package de.geyertobias.performance.demoapp.internal;

import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;

public class ContentProvider implements IStructuredContentProvider {

	private List entries = Arrays.asList("Sample item 1", "Sample item 2", "Sample item 3", "Sample item 4", "Sample item 5");
	
	@Override
	public Object[] getElements(Object inputElement) {
		return entries.toArray();
	}

}
