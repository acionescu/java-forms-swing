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

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.AWTEventListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.metal.MetalBorders;

import ro.zg.java.forms.swing.SwingUiElementsHelper;

public class JComboPane extends JPanel {
    /**
     * 
     */
    private static final long serialVersionUID = 6552471190971978570L;
    private JComponent content;
    private String title;
    private ComboPanePopup popup;
    private JLabel titleLabel;
    private JButton controlButton;
    private boolean opened;
    private MouseGrabber mouseGrabber;

    public JComboPane() {
	title = "JComboPane";
	content = new JPanel();
	init();
    }

    private void init() {
	initTitleLable();
	initControlButton();
	setUpLayout();
	mouseGrabber = new MouseGrabber();
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

    protected Window getParentWindow(Component comp) {
	if (comp == null) {
	    return null;
	}
	if (comp.getParent() instanceof Window) {
	    return (Window) comp.getParent();
	}
	return getParentWindow(comp.getParent());
    }

    public void open() {
	createNewPopup();
	// popup.setLayout(new FlowLayout());
	// content.setBorder(new LineBorder(Color.BLACK));
	showNewPopup();
    }

    private void createNewPopup() {
	opened = true;
	popup = new ComboPanePopup(getParentWindow(this));
//	JPanel container = new JPanel();
//	container.setBorder(new LineBorder(Color.black));
//	popup.setContentPane(container);
//	container.add(content);
	Point refPos = titleLabel.getLocationOnScreen();
//	popup.setLocation(new Point(refPos.x + getFullSize().width / 2 - content.getPreferredSize().width/2, refPos.y));
	popup.setAnchorPoint(new Point(refPos.x + getFullSize().width / 2 , refPos.y));
	
    }

    private void showNewPopup() {
//	validatePopup();
	popup.setContent(content);
	popup.setVisible(true);
	mouseGrabber.grabWindow();
    }

    public void validatePopup() {
	if (popup != null) {
//	    popup.pack();
//	    popup.invalidate();
//	    popup.validate();
	    popup.refresh();
	}
    }

    public void close() {
	if (popup != null) {
	    opened = false;
	    popup.dispose();
	}
    }

    public ComboPanePopup deliverPopup() {
	mouseGrabber.ungrabWindow();
	ComboPanePopup popupref = popup;
	popup = null;
	opened = false;
	return popupref;
    }

    public void receivePopup(ComboPanePopup p) {
	if (p == null) {
	    return;
	}
	/* close any opened popup */
	close();
	popup = p;
//	popup.getContentPane().removeAll();
//	popup.getContentPane().add(content);
	showNewPopup();
    }

    /**
     * @return the opened
     */
    public boolean isOpened() {
	return opened;
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
     * @param content
     *            the content to set
     */
    public void setContent(JComponent content) {
	this.content = content;
	content.setDoubleBuffered(true);
	update();
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
	this.title = title;
	update();
    }

    class ControlButtonListener implements ActionListener {

	public void actionPerformed(ActionEvent e) {
	    if (!isOpened()) {
		open();
	    }
	}

    }

    class PopupFocusListener implements WindowFocusListener {

	public void windowGainedFocus(WindowEvent e) {
	    opened = true;
	}

	public void windowLostFocus(WindowEvent e) {
	    if (!popup.isAncestorOf(e.getOppositeWindow())) {
		opened = false;
		close();
	    } else {
		e.getOppositeWindow().addWindowFocusListener(new WindowFocusListener() {

		    public void windowGainedFocus(WindowEvent e) {
			// TODO Auto-generated method stub

		    }

		    public void windowLostFocus(WindowEvent e) {
			/*
			 * if the focus is not returned to the main window or a child of the main window, dispose
			 */
			Window focusedWindow = e.getOppositeWindow();
			if (!popup.equals(focusedWindow) && !popup.isAncestorOf(focusedWindow)) {
			    close();
			    opened = false;
			}
		    }
		});
	    }
	}
    }

    class MouseGrabber implements ChangeListener, AWTEventListener, ComponentListener, WindowListener {

	private boolean underMouse;

	public MouseGrabber() {
	}

	void grabWindow() {
	    // A grab needs to be added
	    final Toolkit tk = Toolkit.getDefaultToolkit();
	    java.security.AccessController.doPrivileged(new java.security.PrivilegedAction() {
		public Object run() {
		    tk.addAWTEventListener(MouseGrabber.this, AWTEvent.MOUSE_EVENT_MASK
		    /* | AWTEvent.MOUSE_MOTION_EVENT_MASK | AWTEvent.MOUSE_WHEEL_EVENT_MASK */
		    | AWTEvent.WINDOW_EVENT_MASK);
		    return null;
		}
	    });

	}

	void ungrabWindow() {
	    final Toolkit tk = Toolkit.getDefaultToolkit();
	    // The grab should be removed
	    java.security.AccessController.doPrivileged(new java.security.PrivilegedAction() {
		public Object run() {
		    tk.removeAWTEventListener(MouseGrabber.this);
		    return null;
		}
	    });
	}

	public void eventDispatched(AWTEvent ev) {
	    if ((ev instanceof MouseEvent)) {
		MouseEvent me = (MouseEvent) ev;
		Component src = me.getComponent();
//		System.out.println("19.04.2010: "+me.getID());
		switch (me.getID()) {
		case MouseEvent.MOUSE_PRESSED:
		    if (!underMouse) {
			cancelPopupMenu();
		    }
		    break;

		case MouseEvent.MOUSE_ENTERED:
		    underMouse = popup.equals(ev.getSource()) || popup.isAncestorOf((Component) ev.getSource());
//		    System.out.println("19.04.2010: "+underMouse);
		    break;

		}
	    } else if (ev instanceof WindowEvent) {
		WindowEvent wev = (WindowEvent) ev;
		if (wev.getWindow().equals(popup)) {
		    switch (wev.getID()) {
		    case WindowEvent.WINDOW_LOST_FOCUS:
			if (!popup.isAncestorOf(wev.getOppositeWindow()) && !popup.equals(wev.getWindow())) {
			    cancelPopupMenu();
			}
			break;
		    }
		}
	    }

	}

	void cancelPopupMenu() {
	    // We should ungrab window if a user code throws
	    // an unexpected runtime exception. See 6495920.
//	    System.out.println("19.04.2010: close window");
	    try {
		close();
	    } finally {
		ungrabWindow();
	    }
	}

	public void componentResized(ComponentEvent e) {
	    cancelPopupMenu();
	}

	public void componentMoved(ComponentEvent e) {
	    cancelPopupMenu();
	}

	public void componentShown(ComponentEvent e) {
	    cancelPopupMenu();
	}

	public void componentHidden(ComponentEvent e) {
	    cancelPopupMenu();
	}

	public void windowClosing(WindowEvent e) {
	    cancelPopupMenu();
	}

	public void windowClosed(WindowEvent e) {
	    cancelPopupMenu();
	}

	public void windowIconified(WindowEvent e) {
	    cancelPopupMenu();
	}

	public void windowDeactivated(WindowEvent e) {
	    cancelPopupMenu();
	}

	public void windowOpened(WindowEvent e) {
	}

	public void windowDeiconified(WindowEvent e) {
	}

	public void windowActivated(WindowEvent e) {
	}

	public void stateChanged(ChangeEvent e) {
	    // TODO Auto-generated method stub

	}
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
	JComboPane cp = new JComboPane();
	
	cp.setContent(content);
	f.add(cp);
	f.add(new JButton("test"));
	f.setVisible(true);
    }
}
