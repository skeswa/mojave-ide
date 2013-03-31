package com.darkside.mojave.web.mvp.login.presenter;

import org.pakhama.vaadin.mvp.annotation.event.EventListener;
import org.pakhama.vaadin.mvp.presenter.impl.Presenter;
import org.phakama.vaadin.github.session.GitHubSession;
import org.phakama.vaadin.github.session.GitHubSessionManager;

import com.darkside.mojave.web.mvp.login.event.LoginAttemptEvent;
import com.darkside.mojave.web.mvp.login.event.LoginSuccessEvent;
import com.darkside.mojave.web.mvp.login.view.ILoginView;

public class LoginPresenter extends Presenter<ILoginView> {
	private static final long serialVersionUID = 4883100037942785887L;
	private static final String AUTH_FAILURE_MESSAGE = "<br>Invalid GitHub credentials were provided.";

	@EventListener(event = LoginAttemptEvent.class)
	public void onLoginAttempt(LoginAttemptEvent event) {
		GitHubSession session = null;
		try {
			session = GitHubSessionManager.createSession(event.getUserName(), event.getPassword());
		} catch (Exception e) {
			getView().fail(AUTH_FAILURE_MESSAGE);
			return;
		}
		
		if (session == null) {
			getView().fail(AUTH_FAILURE_MESSAGE);
			return;
		} else {
			// If we got this far, we're winning
			dispatch(new LoginSuccessEvent(session));
		}
	}
}
