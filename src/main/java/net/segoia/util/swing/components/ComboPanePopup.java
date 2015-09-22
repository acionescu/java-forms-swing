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
package net.segoia.util.swing.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Point;
import java.awt.Window;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.border.LineBorder;

public class ComboPanePopup extends JWindow{
    private Point anchorPoint;
    private JPanel container;
    
    /**
     * 
     */
    private static final long serialVersionUID = 1407582121825831789L;
    
    public ComboPanePopup(){
	super();
	
    }
    
    public ComboPanePopup(Window parent){
	super(parent);
    }
    
    public ComboPanePopup(Frame parent){
	super(parent);
    }
    
    private void initContainer(){
	container = new JPanel();
	container.setBorder(new LineBorder(Color.black));
	setContentPane(container);
    }
    
    private void prepareContainer(){
	if(container == null){
	    initContainer();
	}
	else{
	    container.removeAll();
	}
    }

    /**
     * @return the anchorPoint
     */
    public Point getAnchorPoint() {
        return anchorPoint;
    }

    /**
     * @param anchorPoint the anchorPoint to set
     */
    public void setAnchorPoint(Point anchorPoint) {
        this.anchorPoint = anchorPoint;
    }
    
    public void setContent(JComponent c){
	prepareContainer();
	container.add(c);
	pack();
	relocate();
    }
    
    public void relocate(){
	if(anchorPoint != null){
	    Dimension size = container.getPreferredSize();
	    setLocation(anchorPoint.x - size.width/2, anchorPoint.y);
	}
    }
    
    public void refresh(){
	relocate();
	pack();
	validate();
    }
    
}
