package com.darkside.mojave.web.mvp.collaborators.view;

import org.pakhama.vaadin.mvp.view.IView;

import com.darkside.mojave.web.mvp.model.User;

public interface ICollaboratorsView extends IView{
	public void login(User user);
	public void logout(User user);
}
