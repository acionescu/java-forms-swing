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
import java.awt.FontMetrics;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class SwingUiElementsHelper {
    public static JTextField createStandardTextField() {
	JTextField tf = new JTextField();
	FontMetrics fm = tf.getFontMetrics(tf.getFont());
//	tf.setPreferredSize(new Dimension(150, fm.getHeight()));
	return tf;
    }
    
    public static JComboBox createStandardCombo(Object[] values) {
	JComboBox combo = new JComboBox(values);
	FontMetrics fm = combo.getFontMetrics(combo.getFont());
//	combo.setPreferredSize(new Dimension(combo.getPreferredSize().width, fm.getHeight()));
	return combo;
    }
    
    public static JButton createStandartButton(String name){
	JButton bt = new JButton(name);
	FontMetrics fm = bt.getFontMetrics(bt.getFont());
//	bt.setPreferredSize(new Dimension(bt.getPreferredSize().width, fm.getHeight()));
	return bt;
    }
}
