package com.darkside.mojave.web.mvp.chat.view;

import org.pakhama.vaadin.mvp.event.EventScope;
import org.pakhama.vaadin.mvp.view.impl.View;

import com.darkside.mojave.web.mvp.chat.event.ChatInputEvent;
import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.event.FieldEvents.FocusEvent;
import com.vaadin.event.FieldEvents.FocusListener;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.event.ShortcutListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class ChatView extends View implements IChatView {
	private static final long serialVersionUID = 166125024363809128L;
	
	private Panel logPanel = new Panel();
	private VerticalLayout chatLayout  = new VerticalLayout();
	private HorizontalLayout footer = new HorizontalLayout();
	private TextField chatInput = new TextField();
	private boolean isFocused = true;
	
	public ChatView() {
		logPanel.setContent(chatLayout);
		
		this.chatLayout.setWidth("100%");
		this.chatLayout.setHeight(-1, Component.UNITS_PIXELS);
		
		logPanel.setSizeFull();
		addComponent(logPanel);
		setExpandRatio(logPanel, 1.0f);

		chatInput.focus();
		chatInput.setWidth("100%");
		chatInput.setImmediate(true);
		chatInput.addShortcutListener(new ShortcutListener("submit", KeyCode.ENTER, null) {
			private static final long serialVersionUID = 6334047052440004043L;

			@Override
			public void handleAction(Object sender, Object target) {
				dispatchChatInputEvent();
			}
		});
		
		//TODO:
		
		chatInput.addListener(new FocusListener() {
			private static final long serialVersionUID = 2910003102488237347L;

			@Override
			public void focus(FocusEvent event) {
				isFocused = true;
			}
		});
		
		chatInput.addListener(new BlurListener() {
			private static final long serialVersionUID = 26545722151249951L;

			@Override
			public void blur(BlurEvent event) {
				isFocused = false;
			}
		});
		
		footer.addComponent(chatInput);
		footer.setExpandRatio(chatInput, 1);
		footer.addComponent(new Button("Send", new ClickListener() {
			private static final long serialVersionUID = -1169480958729485386L;

			@Override
			public void buttonClick(ClickEvent event) {
				if(isFocused) dispatchChatInputEvent();
			}
		}));
		footer.setWidth("100%");
		addComponent(footer);
		
		setSizeFull();
	}
	
	private void dispatchChatInputEvent() {
		dispatch(new ChatInputEvent((String) ChatView.this.chatInput.getValue()), EventScope.PARENT);
		ChatView.this.chatInput.setValue("");
	}

	@Override
	public void addChatLine(String userName, String message, String timeStamp) {
		HorizontalLayout newChatLine = new HorizontalLayout();
		newChatLine.setWidth("100%");
		
		HorizontalLayout timeWrapper = new HorizontalLayout();
		Label timeStampLabel = new Label("[" + timeStamp + "]");
		timeStampLabel.addStyleName("chat-line-timestamp");
		timeWrapper.addComponent(timeStampLabel);
		
		HorizontalLayout userWrapper = new HorizontalLayout();
		Label userNameLabel = new Label(userName + ":");
		userNameLabel.addStyleName("chat-line-username");
		userWrapper.addComponent(userNameLabel);
		
		Label messageLabel = new Label(message);
		messageLabel.addStyleName("chat-line-message");
		
		newChatLine.addComponent(timeWrapper);
		newChatLine.addComponent(userWrapper);
		newChatLine.addComponent(messageLabel);
		
		newChatLine.setExpandRatio(messageLabel, 1.0f);
		
		this.chatLayout.addComponent(newChatLine);
	}
}
