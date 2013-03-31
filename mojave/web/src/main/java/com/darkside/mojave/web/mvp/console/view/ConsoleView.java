package com.darkside.mojave.web.mvp.console.view;

import org.pakhama.vaadin.mvp.view.impl.View;

import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class ConsoleView extends View implements IConsoleView{
	private static final long serialVersionUID = -4487283198454955930L;
	
	private VerticalLayout consoleLabelLayout = new VerticalLayout();
	private Panel consoleContainer = new Panel();
	
	public ConsoleView(){
		this.consoleLabelLayout.setWidth("100%");
		this.consoleLabelLayout.setHeight(-1.0f, Component.UNITS_PIXELS);
		this.consoleContainer.setSizeFull();
		this.consoleContainer.setContent(this.consoleLabelLayout);
		this.consoleContainer.setScrollable(true);
		this.consoleContainer.setScrollTop(consoleLabelLayout.getComponentCount()*100);
		addComponent(this.consoleContainer);
		setExpandRatio(this.consoleContainer, 1.0f);
		setSizeFull();
	}
	
	@Override
	public void printBuildRecord(String consoleContent) {
		consoleContent = consoleContent.replace("\n", "<br>");
		Label consoleContentLabel = new Label(consoleContent);
		consoleContentLabel.setContentMode(Label.CONTENT_XHTML);
		consoleContentLabel.setWidth(-1, Component.UNITS_PIXELS);
		this.consoleLabelLayout.addComponent(consoleContentLabel);
		this.consoleContainer.setScrollTop(consoleLabelLayout.getComponentCount()*100);
	}
}
