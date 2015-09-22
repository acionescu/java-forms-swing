/**
 * java-forms-swing - Support framework to generate Java Swing forms
 * Copyright (C) 2009  Adrian Cristian Ionescu - https://github.com/acionescu
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.segoia.java.forms.swing;

import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import net.segoia.java.forms.ComponentConfig;
import net.segoia.java.forms.ComponentCreator;
import net.segoia.java.forms.event.FormUiEventListener;
import net.segoia.java.forms.event.ValueChangedEvent;

public class SwingTextFieldCreator implements ComponentCreator<JComponent>{

    public JComponent create(ComponentConfig c) {
	JTextField tf = SwingUiElementsHelper.createStandardTextField();
	tf.setPreferredSize(new Dimension(100,20));
	if(c.getValue() != null){
	    tf.setText(c.getValue().toString());
	}
	final FormUiEventListener listener = c.getUiEventListener();
	if(listener != null){
	    tf.getDocument().addDocumentListener(new DocumentListener(){
		
		private void onChange(DocumentEvent e){
		    Document d = e.getDocument();
		    try {
			String value = d.getText(0, d.getLength());
			listener.onEvent(new ValueChangedEvent(value));
		    } catch (BadLocationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		    }
		}

		public void changedUpdate(DocumentEvent e) {
		    onChange(e);
		}

		public void insertUpdate(DocumentEvent e) {
		    onChange(e);
		}

		public void removeUpdate(DocumentEvent e) {
		    onChange(e);
		}
		
	    });
	}
	return tf;
    }

}
