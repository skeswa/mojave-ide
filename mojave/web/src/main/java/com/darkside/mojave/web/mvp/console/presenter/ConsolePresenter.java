package com.darkside.mojave.web.mvp.console.presenter;

import org.pakhama.vaadin.mvp.annotation.event.EventListener;
import org.pakhama.vaadin.mvp.presenter.impl.Presenter;
import org.vaadin.artur.icepush.ICEPush;

import com.darkside.mojave.web.mvp.console.event.ConsoleUpdateEvent;
import com.darkside.mojave.web.mvp.console.view.IConsoleView;

public class ConsolePresenter extends Presenter<IConsoleView>{
	private static final long serialVersionUID = -8256687937882496443L;
	
	private long sessionId;
	private ICEPush pusher;

	public void init(long sessionId, ICEPush pusher){
		this.sessionId = sessionId;
		this.pusher = pusher;
	}
	
	@EventListener(event = ConsoleUpdateEvent.class)
	public void onConsoleUpdate(ConsoleUpdateEvent e) {
		if(e.getSessionId() == sessionId){
			getView().printBuildRecord(e.getConsoleContent());
			pusher.push();
		}
	}
}
