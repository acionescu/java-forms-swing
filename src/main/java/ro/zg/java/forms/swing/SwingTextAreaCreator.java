/*******************************************************************************
 * Copyright 2011 Adrian Cristian Ionescu
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package ro.zg.java.forms.swing;

import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import ro.zg.java.forms.ComponentConfig;
import ro.zg.java.forms.ComponentCreator;
import ro.zg.java.forms.event.FormUiEventListener;
import ro.zg.java.forms.event.ValueChangedEvent;

public class SwingTextAreaCreator implements ComponentCreator<JComponent> {

    public JComponent create(ComponentConfig c) {
	JTextArea ta = new JTextArea();
//	ta.setRows(5);
	ta.setLineWrap(true);
//	ta.setPreferredSize(new Dimension(150, ta.getPreferredSize().height));
	ta.setAutoscrolls(true);
	if (c.getValue() != null) {
	    ta.setText(c.getValue().toString());
	}
	/* add listener */
	final FormUiEventListener listener = c.getUiEventListener();
	if (listener != null) {
	    ta.getDocument().addDocumentListener(new DocumentListener() {

		private void onChange(DocumentEvent e) {
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
	JScrollPane sp = new JScrollPane(ta);
	sp.setPreferredSize(new Dimension(100,150));
	sp.setMinimumSize(new Dimension(100,100));
	return sp;
    }

}