package com.darkside.mojave.web.mvp.ide.view;

import org.pakhama.vaadin.mvp.view.IView;

import com.vaadin.ui.Component;

public interface IIDEView extends IView{
	public void setProjectName(String name);
	public void setTop(Component c);
	public void setMid(Component c);
	public void setLeft(Component c);
	public void setRight(Component c);
	public void setBottom(Component c);
	public void setCollaboratorsView(Component c);
	public void printToast(String message);
}
