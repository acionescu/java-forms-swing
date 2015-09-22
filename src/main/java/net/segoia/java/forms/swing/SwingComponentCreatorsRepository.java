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

import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;

import net.segoia.java.forms.ComponentCreator;
import net.segoia.java.forms.ComponentCreatorsRepository;
import net.segoia.java.forms.ComponentTypes;

public class SwingComponentCreatorsRepository implements ComponentCreatorsRepository<JComponent>{
    private Map<String,ComponentCreator<JComponent>> componentCreators = new HashMap<String, ComponentCreator<JComponent>>();
    
    public SwingComponentCreatorsRepository(){
	componentCreators.put(ComponentTypes.LABEL, new SwingLabelCreator());
	componentCreators.put(ComponentTypes.TEXTFIELD, new SwingTextFieldCreator());
	componentCreators.put(ComponentTypes.BUTTON, new SwingButtonCreator());
	componentCreators.put(ComponentTypes.COMBOBOX, new SwingComboBoxCreator());
	componentCreators.put(ComponentTypes.CHECKBOX, new SwingCheckBoxCreator());
	componentCreators.put(ComponentTypes.TEXTAREA, new SwingTextAreaCreator());
	componentCreators.put(ComponentTypes.SECRET, new SwingSecretFieldCreator());
    }
    
    public ComponentCreator<JComponent> get(String name) {
	return componentCreators.get(name);
    }

}
