package com.darkside.mojave.web;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.vaadin.artur.icepush.ICEPush;

import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class Chat extends VerticalLayout{

	private ChatSession chatSession;
	private String username;
	private ICEPush pusher;
	private Panel logPanel = new Panel("Chat");
	private VerticalLayout chatLayout  = new VerticalLayout();
	private HorizontalLayout footer = new HorizontalLayout();
	private TextField chatInput = new TextField();
	private ChatListener chatListener = new ChatListener();
	private Date latestupdate = new Date();

	public Chat(ChatSession session, String username, ICEPush pusher){
		this.chatSession = session;
		this.username = username;
		this.pusher = pusher;

		logPanel.setContent(chatLayout);
		chatLayout.setSizeFull();
		logPanel.setSizeFull();
		addComponent(logPanel);
		setExpandRatio(logPanel, 1.0f);

		footer.setWidth("100%");
		addComponent(footer);

		chatInput.focus();
		chatInput.setWidth("100%");
		chatInput.setImmediate(true);
		chatInput.addListener(chatListener);
		
		footer.addComponent(pusher);
		footer.setExpandRatio(pusher, 0.0f);
		footer.addComponent(chatInput);
		footer.setExpandRatio(chatInput, 1);
		footer.addComponent(new Button("Send", chatListener));
	}

	private class ChatListener implements ClickListener, TextChangeListener {
		//TODO: make sure only enter calls this listener
		public void textChange(TextChangeEvent event) {
			addNewChatLine();
		}

		public void buttonClick(ClickEvent event) {
			addNewChatLine();
		}

		private void addNewChatLine() {
			String message = chatInput.getValue().toString();
			Date timestamp = new Date();

//			if (message != null && !message.isEmpty()) {
//				chatSession.getChatLog().add(new ChatEntry(username, timestamp, message));
//				clearChat();
//				updateChatLog();
//			}
		}

		private void clearChat() {
			chatInput.setValue("");
			chatInput.focus();
		}
		
		private void print(ChatEntry entry) {
			Date timestamp = entry.getTimestamp();
			String name = entry.getName();
			String message = entry.getMessage();
			
			HorizontalLayout chatLine = new HorizontalLayout();
			chatLine.setWidth("100%");
			chatLine.setSpacing(true);

			SimpleDateFormat dateFormat = new SimpleDateFormat("[HH:mm]");
			
			Label timeLabel = new Label(dateFormat.format(timestamp));
			timeLabel.setWidth(-1, Component.UNITS_PIXELS);
			timeLabel.addStyleName("chat-time-field");
			chatLine.addComponent(timeLabel);

			Label nameLabel = new Label(name + ": ");
			nameLabel.addStyleName("chat-name-field");
			nameLabel.setWidth(100, Component.UNITS_PIXELS);
			chatLine.addComponent(nameLabel);

			Label messageLabel = new Label(message);
			messageLabel.addStyleName("chat-message-field");
			chatLine.addComponent(messageLabel);
			chatLine.setExpandRatio(messageLabel, 1.0f);
			
			chatLayout.addComponent(chatLine);
			pusher.push();
		}
		
		private void updateChatLog() {
//			Date newLatestUpdate = null;
//			for(ChatEntry entry : chatSession.getChatLog()) {
//				Date timestamp = entry.getTimestamp();
//				if (timestamp.after(latestupdate)) {
//					newLatestUpdate = timestamp;
//					print(entry);
//				}
//			}
//			if (newLatestUpdate != null) latestupdate = newLatestUpdate;
		}
	}
}
