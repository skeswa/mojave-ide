package com.darkside.mojave.web.mvp.ide.view;

import org.pakhama.vaadin.mvp.view.impl.View;

import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;
import com.vaadin.ui.Window.Notification;

public class IDEView extends View implements IIDEView{
	private static final long serialVersionUID = 8872717071186816793L;

	private VerticalLayout layout = new VerticalLayout();
	private HorizontalLayout mainLayout = new HorizontalLayout();
	private HorizontalSplitPanel redBlueSplit = new HorizontalSplitPanel();
	private HorizontalSplitPanel blueGreenSplit = new HorizontalSplitPanel();
	private VerticalLayout red = new VerticalLayout();
	private VerticalLayout blue = new VerticalLayout();
	private VerticalSplitPanel bluePanel = new VerticalSplitPanel();
	private VerticalLayout green = new VerticalLayout();
	private Label projectTitle = new Label();
	
	public IDEView(){
        redBlueSplit.setMinSplitPosition(150.0f, Component.UNITS_PIXELS);
        redBlueSplit.setSplitPosition(350.0f, Component.UNITS_PIXELS);
        redBlueSplit.setFirstComponent(red);
        red.setSizeFull();
        blue.setSizeFull();
        bluePanel.setSizeFull();
        bluePanel.setFirstComponent(blue);
        bluePanel.setSplitPosition(71.5f, Component.UNITS_PERCENTAGE);
        projectTitle.setHeight(25, Component.UNITS_PIXELS);
        red.addComponent(projectTitle);
        redBlueSplit.setSecondComponent(bluePanel);
        redBlueSplit.setSizeFull();
        blueGreenSplit.setFirstComponent(redBlueSplit);
        blueGreenSplit.setSecondComponent(green);
        green.setSizeFull();
        blueGreenSplit.setMinSplitPosition(150.0f, Component.UNITS_PIXELS);
        blueGreenSplit.setSplitPosition(250.0f, Component.UNITS_PIXELS, true);
        blueGreenSplit.setSizeFull();
        mainLayout.addComponent(blueGreenSplit);
        mainLayout.setExpandRatio(blueGreenSplit, 1.0f);
        layout.addComponent(mainLayout);
        mainLayout.setSizeFull();
        layout.setExpandRatio(mainLayout, 1.0f);
        layout.setSizeFull();
        addComponent(layout);
	}
	
	@Override
	public void setTop(Component c) {
		blue.addComponentAsFirst(c);
	}

	@Override
	public void setMid(Component c) {
		blue.addComponent(c);
		blue.setExpandRatio(c, 1.0f);
	}

	@Override
	public void setLeft(Component c) {
		red.addComponent(c);
		red.setExpandRatio(c, 1.0f);
	}

	@Override
	public void setRight(Component c) {
		green.addComponent(c);
		green.setExpandRatio(c, 1.0f);
	}

	@Override
	public void setProjectName(String name) {
		projectTitle.setValue(name);
	}

	@Override
	public void setCollaboratorsView(Component c) {
		green.addComponentAsFirst(c);
	}

	@Override
	public void setBottom(Component c) {
		bluePanel.setSecondComponent(c);
	}

	@Override
	public void printToast(String message) {
		getWindow().showNotification(message, Notification.TYPE_TRAY_NOTIFICATION);
	}
}
