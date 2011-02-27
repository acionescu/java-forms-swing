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

import ro.zg.java.forms.ComponentCreatorsRepository;
import ro.zg.java.forms.FormUi;
import ro.zg.util.swing.components.JComboPane;

public class SwingComboPaneFormUi extends SwingFormUi{
    private FormUi parent;
    private String title;
    private JComboPane comboPane;
    public SwingComboPaneFormUi(ComponentCreatorsRepository<JComponent> cc) {
	super(cc);
    }
    
    public SwingComboPaneFormUi(FormUi parent, String title){
	super(((SwingFormUi)parent).getComponentCreatorsRepository());
	this.parent = parent;
	this.title = title;
	init();
    }
    
    private void init(){
	comboPane = new JComboPane();
	comboPane.setTitle(title);
	comboPane.setContent((JComponent)super.getHolder());
	((JComponent)parent.getHolder()).add(comboPane);
    }
    
    public void validate(){
//	System.out.println("validate popup :"+title);
	((JComponent)getHolder()).revalidate();
	comboPane.validatePopup();
    }
    
    public void clear(){
//	System.out.println("clear popup :"+title);
	super.clear();
	comboPane.validatePopup();
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
