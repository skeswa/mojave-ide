package com.darkside.mojave.web.mvp.presenter;

import javax.persistence.EntityManager;

import org.pakhama.vaadin.mvp.annotation.event.EventListener;
import org.pakhama.vaadin.mvp.presenter.impl.Presenter;
import org.phakama.maven.model.MavenEnvironmentConfiguration;
import org.vaadin.artur.icepush.ICEPush;

import com.darkside.mojave.domain.User;
import com.darkside.mojave.web.MojaveApplicationData;
import com.darkside.mojave.web.dal.PersistenceUtil;
import com.darkside.mojave.web.dal.UserDAO;
import com.darkside.mojave.web.mvp.login.event.LoginSuccessEvent;
import com.darkside.mojave.web.mvp.login.presenter.LoginPresenter;
import com.darkside.mojave.web.mvp.view.IMojaveView;
import com.darkside.mojave.web.mvp.workspace.presenter.WorkspacePresenter;
import com.darkside.mojave.web.session.MojaveSession;

public class MojavePresenter extends Presenter<IMojaveView> {
	private static final long serialVersionUID = 1386146394630201829L;
	private static final String MAVEN_LOCAL_M2_PATH = "C:\\m2";
	private static final String MAVEN_LOCAL_M2_LOG_PATH = "C:\\m2logs";
	
	private MojaveSession session;
	private ICEPush pusher;

	public MojavePresenter() {
	}
	
	@Override
	public void onBind(IMojaveView view) {
		super.onBind(view);
		this.session = MojaveApplicationData.getSession();
		if (this.session == null) {
			doLogin();
		} else {
			doWorkspace();
		}
	}
	
	public void init(ICEPush pusher) {
		this.pusher = pusher;
	}
	
	@EventListener(event = LoginSuccessEvent.class)
	public void onLoginSuccess(LoginSuccessEvent e) {
		EntityManager em = PersistenceUtil.createEntityManager();
		MavenEnvironmentConfiguration config = new MavenEnvironmentConfiguration(MAVEN_LOCAL_M2_PATH, MAVEN_LOCAL_M2_LOG_PATH);
		User user = UserDAO.provideUser(em, e.getGitHubSession());
		MojaveSession session = new MojaveSession(e.getGitHubSession(), config, user, em);
		MojaveApplicationData.setSession(session);
		// Time to show the workspace
		doWorkspace();
	}
	
	private void doLogin() {
		LoginPresenter loginPresenter = createChild(LoginPresenter.class);
		getView().setContent(loginPresenter.getView().getComponent());
	}
	
	private void doWorkspace() {
		WorkspacePresenter workspacePresenter = createChild(WorkspacePresenter.class);
		workspacePresenter.init(this.pusher);
		getView().setContent(workspacePresenter.getView().getComponent());
	}
}
