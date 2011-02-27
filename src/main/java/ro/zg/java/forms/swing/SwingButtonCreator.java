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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;

import ro.zg.java.forms.ComponentConfig;
import ro.zg.java.forms.ComponentCreator;
import ro.zg.java.forms.event.FormUiEventListener;

public class SwingButtonCreator implements ComponentCreator<JComponent> {

    public JComponent create(ComponentConfig c) {
	
	JButton b = SwingUiElementsHelper.createStandartButton(c.getValue().toString());
	final FormUiEventListener listener = c.getUiEventListener();
	if (listener != null) {
	    b.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    listener.onEvent(e);
		}

	    });
	}
	return b;
    }

}
