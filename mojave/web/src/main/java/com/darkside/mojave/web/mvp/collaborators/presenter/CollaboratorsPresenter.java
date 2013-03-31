package com.darkside.mojave.web.mvp.collaborators.presenter;

import org.pakhama.vaadin.mvp.annotation.event.EventListener;
import org.pakhama.vaadin.mvp.presenter.impl.Presenter;
import org.vaadin.artur.icepush.ICEPush;

import com.darkside.mojave.web.mvp.collaborators.event.ProjectSessionJoinEvent;
import com.darkside.mojave.web.mvp.collaborators.event.ProjectSessionLeaveEvent;
import com.darkside.mojave.web.mvp.collaborators.view.ICollaboratorsView;

public class CollaboratorsPresenter extends Presenter<ICollaboratorsView>{
	private static final long serialVersionUID = 7105573403984020137L;

	private ICEPush pusher;
	private long sessionId;

	public void init(long sessionId, ICEPush pusher){
		this.pusher = pusher;
		this.sessionId = sessionId;
	}

	@EventListener(event = ProjectSessionJoinEvent.class)
	public void onUserLogin(ProjectSessionJoinEvent event){
		if(event.getSessionId() == sessionId){
			getView().login(event.getUser());
			pusher.push();
		}

	}

	@EventListener(event = ProjectSessionLeaveEvent.class)
	public void onUserLogout(ProjectSessionLeaveEvent event){
		if(event.getSessionId() == sessionId){
			getView().logout(event.getUser());
			pusher.push();
		}
	}
}
