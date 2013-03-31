package com.darkside.mojave.web.mvp.login.view;

import org.pakhama.vaadin.mvp.event.EventScope;
import org.pakhama.vaadin.mvp.view.impl.View;

import com.darkside.mojave.web.mvp.login.event.LoginAttemptEvent;
import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.event.FieldEvents.FocusEvent;
import com.vaadin.event.FieldEvents.FocusListener;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.event.ShortcutListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window.Notification;

public class LoginView extends View implements ILoginView {
	private static final long serialVersionUID = 2940842919027363039L;
	private static final String LOGIN_VIEW_STYLE = "mojave-login";
	private static final String LOGIN_LOGO_STYLE = "mojave-login-logo";
	private static final String LOGIN_TEXT_STYLE = "mojave-login-text";
	private static final String LOGIN_BUTTON_STYLE = "mojave-login-button";

	private static final String LOGIN_LOGO_WIDTH = "516px";
	private static final String LOGIN_LOGO_HEIGHT = "59px";
	private static final String LOGIN_VIEW_HEIGHT = "235px";
	private static final String LOGIN_TEXT_WIDTH = "100%";

	private static final String LOGIN_USERNAME_PROMPT = "username";
	private static final String LOGIN_PASSWORD_PROMPT = "password";

	private TextField userNameField;
	private PasswordField passwordField;

	public LoginView() {
		VerticalLayout logoLayout = new VerticalLayout();
		logoLayout.addStyleName(LOGIN_LOGO_STYLE);
		logoLayout.setWidth(LOGIN_LOGO_WIDTH);
		logoLayout.setHeight(LOGIN_LOGO_HEIGHT);

		this.userNameField = new TextField();
		this.userNameField.addStyleName(LOGIN_TEXT_STYLE);
		this.userNameField.setInputPrompt(LOGIN_USERNAME_PROMPT);
		this.userNameField.setWidth(LOGIN_TEXT_WIDTH);
		this.userNameField.addListener(new FocusListener() {
			private static final long serialVersionUID = -560579898118188917L;

			public void focus(FocusEvent event) {
				LoginView.this.passwordField.setInputPrompt("");
			}
		});
		this.userNameField.addListener(new BlurListener() {
			private static final long serialVersionUID = 2071948621599302532L;

			public void blur(BlurEvent event) {
				LoginView.this.passwordField.setInputPrompt(LOGIN_PASSWORD_PROMPT);
			}
		});

		this.passwordField = new PasswordField();
		this.passwordField.setInputPrompt(LOGIN_PASSWORD_PROMPT);
		this.passwordField.addStyleName(LOGIN_TEXT_STYLE);
		this.passwordField.setWidth(LOGIN_TEXT_WIDTH);
		this.passwordField.addShortcutListener(new ShortcutListener("Login", KeyCode.ENTER, null) {
			private static final long serialVersionUID = 3890846691337678545L;

			@Override
			public void handleAction(Object sender, Object target) {
				dispatchLoginAttemptEvent();
			}
		});

		HorizontalLayout button = new HorizontalLayout();
		button.addStyleName(LOGIN_BUTTON_STYLE);
		Label buttonLabel = new Label("Continue");
		buttonLabel.setWidth(-1, Component.UNITS_PIXELS);
		button.addComponent(buttonLabel);
		button.setComponentAlignment(buttonLabel, Alignment.MIDDLE_CENTER);
		button.setWidth(LOGIN_TEXT_WIDTH);
		button.addListener(new LayoutClickListener() {
			private static final long serialVersionUID = 1L;

			public void layoutClick(LayoutClickEvent event) {
				dispatchLoginAttemptEvent();
			}
		});

		VerticalLayout loginLayout = new VerticalLayout();
		loginLayout.setHeight(LOGIN_VIEW_HEIGHT);
		loginLayout.setWidth(LOGIN_LOGO_WIDTH);
		loginLayout.setSpacing(true);
		loginLayout.addComponent(logoLayout);
		loginLayout.addComponent(userNameField);
		loginLayout.addComponent(passwordField);
		loginLayout.addComponent(button);

		addComponent(loginLayout);
		setComponentAlignment(loginLayout, Alignment.MIDDLE_CENTER);

		addStyleName(LOGIN_VIEW_STYLE);
		setSizeFull();
	}

	private void dispatchLoginAttemptEvent() {
		dispatch(new LoginAttemptEvent((String) this.userNameField.getValue(),
				(String) this.passwordField.getValue()), EventScope.PARENT);
	}

	public void fail(String message) {
		getWindow().showNotification("Authentication Failure", message, Notification.TYPE_ERROR_MESSAGE);
		this.passwordField.setValue("");
		this.userNameField.setValue("");
		this.userNameField.focus();
	}
}
