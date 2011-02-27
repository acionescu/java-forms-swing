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
package ro.zg.java.forms.model;

import java.awt.Container;
import java.util.Map;

import ro.zg.java.forms.ConfigParam;
import ro.zg.java.forms.swing.SwingComboPaneFormUi;
import ro.zg.util.swing.components.JComboPane;

public class ComboPaneFormModel extends DefaultFormModel {
    private String comboPaneTitle;
    private JComboPane pane;

    public void init() {
	super.init();
	initNestedForm();
    }

    public void construct() throws Exception {
	super.construct();
	if (pane != null) {
	    pane.validatePopup();
	}
    }

    private void initNestedForm() {
	Map<String, ConfigParam> configParams = formConfig.getConfigParams();
	getComboPaneTitle(configParams);
	JComboPane upperCombo = checkInsideComboPane((Container) owner.getUi().getHolder());
//	if (pane == null) {
	    if (upperCombo == null) {
		owner.setUi(new SwingComboPaneFormUi(owner.getUi(), comboPaneTitle));
//		System.out.println("created combo pane :"+comboPaneTitle);
	    } 
	    else {
//		System.out.println("already in a combopane :"+comboPaneTitle);
		SwingComboPaneFormUi newUi = new SwingComboPaneFormUi(((SwingComboPaneFormUi) owner.getUi())
			.getParent(), comboPaneTitle);
		final JComboPane newCp = (JComboPane) newUi.getHolder();
		newCp.receivePopup(upperCombo.deliverPopup());
		
		owner.setUi(newUi);

	    }
//	    pane = (JComboPane) owner.getUi().getHolder();
//	}
    }

    private JComboPane checkInsideComboPane(Container c) {
	if (c == null) {
	    return null;
	}
	if (c instanceof JComboPane) {
	    return (JComboPane) c;
	}
	return checkInsideComboPane(c.getParent());
    }

    private void getComboPaneTitle(Map<String, ConfigParam> configParams) {
	ConfigParam p = configParams.get("comboPaneTitle");
	if (p == null) {
	    return;
	}
	if (p.getValue() != null) {
	    comboPaneTitle = p.getValue();
	} else if (p.getSource() != null) {
//	    comboPaneTitle = (String) getSourceData(p.getSource());
	    comboPaneTitle = (String) dataContext.getValue(p.getSource());
	} else if (p.getSourceField() != null) {
	    comboPaneTitle = getValueForProperty(p.getSourceField()).toString();
	}
    }

}
