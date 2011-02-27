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

import javax.swing.JComponent;
import javax.swing.JLabel;

import ro.zg.java.forms.ComponentConfig;
import ro.zg.java.forms.ComponentCreator;

public class SwingLabelCreator implements ComponentCreator<JComponent>{

    public JComponent create(ComponentConfig c) {
	JLabel label = new JLabel();
	if(c.getValue() != null){
	    label.setText(c.getValue().toString());
	}
	return label;
    }

}
