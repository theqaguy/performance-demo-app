package de.geyertobias.performance.demoapp.internal;

import java.util.List;

import org.eclipse.jface.viewers.ILazyContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;

import de.geyertobias.performance.demoapp.model.Address;

public class ContentProvider implements ILazyContentProvider {
  private TableViewer viewer;
  private List<Address> elements;

  public ContentProvider(TableViewer viewer) {
    this.viewer = viewer;
  }

  public void dispose() {
  }

  public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
    this.elements = (List<Address>) newInput;
  }

  public void updateElement(int index) {
    viewer.replace(elements.get(index), index);
  }
} 