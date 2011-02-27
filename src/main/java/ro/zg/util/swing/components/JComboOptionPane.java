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
package ro.zg.util.swing.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.plaf.metal.MetalBorders;

import ro.zg.java.forms.swing.SwingUiElementsHelper;

public class JComboOptionPane extends JPanel {
    /**
     * 
     */
    private static final long serialVersionUID = -2372630646532041587L;
    private JComponent content;
    private String title = "OptionPane";
    private JLabel titleLabel;
    private JButton controlButton;
    private JOptionPane optionPane;
    private JDialog dialog;
    public JComboOptionPane() {
	init();
    }

    private void init() {
	initTitleLable();
	initControlButton();
	setUpLayout();
	setPreferredSize(getFullSize());
    }

    private void initTitleLable() {
	titleLabel = new JLabel(title);
	LineBorder border = new LineBorder(Color.BLACK);
	titleLabel.setBorder(new MetalBorders.TextFieldBorder());
    }

    private void initControlButton() {
	controlButton = new JButton("...");
	Insets insets = controlButton.getMargin();
	controlButton.setMargin(new Insets(insets.top, insets.top, insets.bottom, insets.top));
	controlButton.addActionListener(new ControlButtonListener());
	// controlButton.addMouseListener(new ButtonMouseListener());
    }

    private void setUpLayout() {
	GridBagLayout layout = new GridBagLayout();
	GridBagConstraints constraints = new GridBagConstraints();
	constraints.fill = GridBagConstraints.BOTH;
	constraints.gridwidth = GridBagConstraints.RELATIVE;
	constraints.weightx = 1;
	setLayout(layout);
	add(titleLabel, constraints);
	constraints.weightx = 0;
	
	add(controlButton, constraints);

    }

    protected void update() {
	titleLabel.setText(title);
    }

    private Dimension getFullSize() {
	Dimension labelDim = titleLabel.getPreferredSize();
	Dimension cbDim = controlButton.getPreferredSize();
	double w = labelDim.width + cbDim.width;
	double h = Math.max(labelDim.height, cbDim.height);
	return new Dimension((int) w, (int) h);
    }

    public void open() {
	optionPane = new JOptionPane(content);
	dialog = optionPane.createDialog("Configure "+title);
	dialog.setVisible(true);
    }
    
    public void validateOptionPane() {
	
	optionPane.validate();
	dialog.pack();
    }

    class ControlButtonListener implements ActionListener {

	public void actionPerformed(ActionEvent e) {
	    open();
	}

    }

    /**
     * @return the content
     */
    public JComponent getContent() {
	return content;
    }

    /**
     * @return the title
     */
    public String getTitle() {
	return title;
    }

    /**
     * @return the titleLabel
     */
    public JLabel getTitleLabel() {
	return titleLabel;
    }

    /**
     * @return the controlButton
     */
    public JButton getControlButton() {
	return controlButton;
    }

    /**
     * @param content
     *            the content to set
     */
    public void setContent(JComponent content) {
	this.content = content;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
	this.title = title;
	update();
    }

    /**
     * @param titleLabel
     *            the titleLabel to set
     */
    public void setTitleLabel(JLabel titleLabel) {
	this.titleLabel = titleLabel;
    }

    /**
     * @param controlButton
     *            the controlButton to set
     */
    public void setControlButton(JButton controlButton) {
	this.controlButton = controlButton;
    }
    
    
    public static void main(String[] args) {
	JFrame f = new JFrame();
	f.setLayout(new FlowLayout());
	f.setSize(800, 600);

	JPanel content = new JPanel(new GridBagLayout());
	content.add(SwingUiElementsHelper.createStandardCombo(new Object[] { "JComboPane" }));
	content.add(SwingUiElementsHelper.createStandartButton("bang"));
	content.add(SwingUiElementsHelper.createStandartButton("bang"));
	content.add(SwingUiElementsHelper.createStandartButton("bang"));
	JComboPane nestedCp = new JComboPane();
	JPanel content2 = new JPanel(new GridBagLayout());
	content2.add(SwingUiElementsHelper.createStandardCombo(new Object[] { "JComboPane2" }));
	nestedCp.setContent(content2);
	content.add(nestedCp);
	JComboOptionPane cp = new JComboOptionPane();
	cp.setTitle("ComboOptionPane");
	cp.setContent(content);
	f.add(cp);
	f.add(new JButton("test"));
	f.setVisible(true);
    }

}
