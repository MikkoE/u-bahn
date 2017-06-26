/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 *
 * @author mikko
 */
public class Item {
		private String name ;
		private int value ;
		
		private final PropertyChangeSupport propertySupport ;
		
		public Item(String name, int value) {
			this.name = name;
			this.value = value;
			
			this.propertySupport = new PropertyChangeSupport(this);
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			String oldName = this.name ;
			this.name = name;
			propertySupport.firePropertyChange("name", oldName, name);
		}
		public int getValue() {
			return value;
		}
		public void setValue(int value) {
			int oldValue = this.value ;
			this.value = value;
			propertySupport.firePropertyChange("value", oldValue, value);
		}

		
		public void addPropertyChangeListener(PropertyChangeListener listener) {
			propertySupport.addPropertyChangeListener(listener);
		}
		
		@Override
		public String toString() {
			return name +": "+value ;
		}
		
	}
