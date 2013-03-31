package com.darkside.mojave.web.mvp.chat.presenter;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.pakhama.vaadin.mvp.annotation.event.EventListener;
import org.pakhama.vaadin.mvp.event.EventScope;
import org.pakhama.vaadin.mvp.presenter.impl.Presenter;
import org.vaadin.artur.icepush.ICEPush;

import com.darkside.mojave.web.mvp.chat.event.ChatInputEvent;
import com.darkside.mojave.web.mvp.chat.event.ChatUpdateEvent;
import com.darkside.mojave.web.mvp.chat.view.IChatView;

public class ChatPresenter extends Presenter<IChatView> {
	private static final long serialVersionUID = 9043221693367631201L;

	private long sessionId;
	private String username;
	private ICEPush pusher;

	private boolean initialized = false;

	@EventListener(event = ChatInputEvent.class)
	public void onChatInputEvent(ChatInputEvent e) {
		if (this.initialized) {
			// Catch the event from the view, tell all mah frenz
			SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
			dispatch(new ChatUpdateEvent(this.sessionId, this.username, e.getMessage(), dateFormat.format(new Date())), EventScope.UNIVERSAL);
		} else {
			throw new RuntimeException("This prez ain't init baybee.");
		}
	}

	@EventListener(event = ChatUpdateEvent.class)
	public void onChatUpdate(ChatUpdateEvent e) {
		if(e.getSessionId() == sessionId){
			getView().addChatLine(e.getUserName(), e.getMessage(), e.getTimeStamp());
			this.pusher.push();
		}
	}
	
	public void init(long sessionId, String username, ICEPush pusher) {
		this.sessionId = sessionId;
		this.username = username;
		this.pusher = pusher;
		this.initialized = true;
	}
}
