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

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;

import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.TitledBorder;

import ro.zg.java.forms.AbstractFormUi;
import ro.zg.java.forms.ComponentConfig;
import ro.zg.java.forms.ComponentCreator;
import ro.zg.java.forms.ComponentCreatorsRepository;
import ro.zg.java.forms.FormLayoutConstraints;
import ro.zg.java.forms.FormUi;

public class SwingFormUi extends AbstractFormUi<JComponent> {
    private JPanel holder;
    private GridBagConstraints constraints;

    public SwingFormUi(ComponentCreatorsRepository<JComponent> cc) {
	super(cc);
	init();
    }

    private void init() {
	holder = new JPanel(new GridBagLayout());
	constraints = new GridBagConstraints();
    }
    
    

    public void addComponent(ComponentConfig config, FormLayoutConstraints fconst) {
	translateConstraints(fconst);
	ComponentCreator<JComponent> cc = componentCreatorsRepository.get(config.getType());
	if (cc != null) {
	    holder.add(cc.create(config), constraints);
	}
    }

    public void addSubform(FormUi ui, FormLayoutConstraints fconst) {
	translateConstraints(fconst);
	holder.add((JComponent) ui.getHolder(), constraints);
    }

    private void translateConstraints(FormLayoutConstraints fconst) {
	constraints = fconst;
    }

    public Object getHolder() {
	return holder;
    }

    public void validate() {
	 holder.revalidate();
//	holder.invalidate();
//	Container parent = holder.getParent();
//	// if (parent != null) {
//	// parent.invalidate();
//	// parent.validate();
//	// }
//	refreshParent(parent);
//	holder.validate();
    }

    private void refreshParent(Container c) {
	if (c == null) {
	    return;
	}

	if (c.getParent() != null) {
	    refreshParent(c.getParent());
	}

	c.validate();

    }

    public void clear() {
	holder.removeAll();
	Container parent = holder.getParent();
	if (parent != null) {
	    parent.invalidate();
	    parent.validate();
	}
    }

    public void addSeparator(FormLayoutConstraints constraints) {
	translateConstraints(constraints);
	JSeparator s = new JSeparator();
	holder.add(s, this.constraints);
    }

    public void addVerticalSeparator(FormLayoutConstraints constraints) {
	translateConstraints(constraints);
	JSeparator s = new JSeparator(JSeparator.VERTICAL);
	holder.add(s, this.constraints);
    }

    public void removeComponent(Object component) {
	if (component instanceof JComponent) {
	    holder.remove((JComponent) component);
	}
    }

    public void setBorderName(String name) {
//	TitledBorder b = new TitledBorder(new LineBorder(Color.black),name);
	TitledBorder b = new TitledBorder(name);
	holder.setBorder(b);
	
    }
    
    public void addEmptySpace(FormLayoutConstraints constraints){
	translateConstraints(constraints);
	Dimension d = new Dimension(0,0);
	holder.add( new Box.Filler(d,d,d),this.constraints);
    }

    public Object getLayout() {
	return holder.getLayout();
    }

    public void setLayout(Object l) {
	holder.setLayout((LayoutManager)l);
    }

}
