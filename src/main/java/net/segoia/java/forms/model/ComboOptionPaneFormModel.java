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
package net.segoia.java.forms.model;

import java.util.Map;

import net.segoia.java.forms.ConfigParam;
import net.segoia.java.forms.FormUi;
import net.segoia.java.forms.model.DefaultFormModel;
import net.segoia.java.forms.swing.SwingComboOptionPaneFormUi;

public class ComboOptionPaneFormModel extends DefaultFormModel {
    private String comboPaneTitle;
    private FormUi ui;

    public void init() {
	super.init();
	initNestedForm();
    }

    public void construct() throws Exception {
	
	System.out.println("combo pane construct");
	super.construct();
	
    }

    private void initNestedForm() {
	Map<String, ConfigParam> configParams = formConfig.getConfigParams();
	getComboPaneTitle(configParams);
	System.out.println("combo pane init");
	System.out.println("combo pane ui "+owner.getUi());
	owner.setUi(new SwingComboOptionPaneFormUi(owner.getUi(), comboPaneTitle));
//	 System.out.println("created combo pane :"+comboPaneTitle);
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
	    Object titleObj = getValueForProperty(p.getSourceField());
	    comboPaneTitle = (titleObj != null)?titleObj.toString():null;
	}
    }

}
