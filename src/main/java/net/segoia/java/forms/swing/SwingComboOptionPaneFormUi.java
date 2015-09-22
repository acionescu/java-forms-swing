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

import javax.swing.JComponent;

import net.segoia.java.forms.ComponentCreatorsRepository;
import net.segoia.java.forms.FormUi;
import net.segoia.util.swing.components.JComboOptionPane;

public class SwingComboOptionPaneFormUi extends SwingFormUi{
    private FormUi parent;
    private String title;
    private JComboOptionPane comboPane;
    public SwingComboOptionPaneFormUi(ComponentCreatorsRepository<JComponent> cc) {
	super(cc);
    }
    
    public SwingComboOptionPaneFormUi(FormUi parent, String title){
	super(((SwingFormUi)parent).getComponentCreatorsRepository());
	this.parent = parent;
	this.title = title;
	init();
    }
    
    private void init(){
	comboPane = new JComboOptionPane();
	comboPane.setTitle(title);
	comboPane.setContent((JComponent)super.getHolder());
	((JComponent)parent.getHolder()).add(comboPane);
    }
    
    public void validate(){
	System.out.println("validate popup :"+title);
	((JComponent)getHolder()).revalidate();
	comboPane.validateOptionPane();
    }
    
    public void clear(){
//	System.out.println("clear popup :"+title);
	super.clear();
	comboPane.validateOptionPane();
    }
    
    public Object getHolder(){
	return comboPane;
    }

    /**
     * @return the parent
     */
    public FormUi getParent() {
        return parent;
    }

}
