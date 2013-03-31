package com.darkside.mojave.web.mvp.workspace.view;

import org.pakhama.vaadin.mvp.view.impl.View;

import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

public class WorkspaceView extends View implements IWorkspaceView {
	private static final long serialVersionUID = 6108913514927565131L;
	private static final String WORKSPACE_VIEW_STYLE = "mojave-workspace";
	
	private VerticalLayout topPlaceHolder = new VerticalLayout();
	private VerticalLayout leftPlaceHolder = new VerticalLayout();
	private VerticalLayout contentPlaceHolder = new VerticalLayout();
	
	public WorkspaceView() {
		this.topPlaceHolder.setHeight("45px");
		this.topPlaceHolder.setWidth("100%");
		this.leftPlaceHolder.setHeight("100%");
		this.leftPlaceHolder.setWidth(-1, Component.UNITS_PIXELS);
		this.contentPlaceHolder.setSizeFull();
		
		addStyleName(WORKSPACE_VIEW_STYLE);
		
		HorizontalLayout bottomLayout = new HorizontalLayout();
		bottomLayout.setSizeFull();
		
		bottomLayout.addComponent(this.leftPlaceHolder);
		bottomLayout.addComponent(this.contentPlaceHolder);
		bottomLayout.setExpandRatio(this.contentPlaceHolder, 1.0f);
		
		addComponent(this.topPlaceHolder);
		addComponent(bottomLayout);
		setExpandRatio(bottomLayout, 1.0f);
		
		setSizeFull();
	}

	@Override
	public void setTop(Component component) {
		this.topPlaceHolder.removeAllComponents();
		this.topPlaceHolder.addComponent(component);
	}

	@Override
	public void setLeft(Component component) {
		this.leftPlaceHolder.removeAllComponents();
		this.leftPlaceHolder.addComponent(component);
	}

	@Override
	public void setContent(Component component) {
		this.contentPlaceHolder.removeAllComponents();
		this.contentPlaceHolder.addComponent(component);
	}
}
