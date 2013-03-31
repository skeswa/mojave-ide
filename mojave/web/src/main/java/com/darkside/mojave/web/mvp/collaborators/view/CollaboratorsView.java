package com.darkside.mojave.web.mvp.collaborators.view;


import java.util.HashMap;

import org.pakhama.vaadin.mvp.view.impl.View;

import com.darkside.mojave.domain.User;
import com.vaadin.ui.Label;

public class CollaboratorsView extends View implements ICollaboratorsView{
	private static final long serialVersionUID = 173366751958760994L;

	HashMap<User, Label> labels = new HashMap<User, Label>();
	
	public CollaboratorsView(){
		Label title = new Label("Current Collaborators:");
		title.setStyleName("collaborators-title");
		addComponent(title);
	}
	
	@Override
	public void login(User user) {
		Label label = new Label(user.getUserName());
		label.setStyleName("collaborators-text");
		labels.put(user, label);
		addComponent(label);
	}

	@Override
	public void logout(User user) {
		removeComponent(labels.get(user));
		labels.remove(user);
	}
}
