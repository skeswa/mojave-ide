package com.darkside.mojave.web.mvp.workspace.view;

import org.pakhama.vaadin.mvp.view.IView;

import com.vaadin.ui.Component;

public interface IWorkspaceView extends IView {
	public void setTop(Component component);
	public void setLeft(Component component);
	public void setContent(Component component);
}
