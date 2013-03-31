package com.darkside.mojave.web.mvp.presenter;

import javax.persistence.EntityManager;

import org.pakhama.vaadin.mvp.annotation.event.EventListener;
import org.pakhama.vaadin.mvp.presenter.impl.Presenter;

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
	
	private MojaveSession session;

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
	
	@EventListener(event = LoginSuccessEvent.class)
	public void onLoginSuccess(LoginSuccessEvent e) {
		EntityManager em = PersistenceUtil.createEntityManager();
		User user = UserDAO.provideUser(em, e.getGitHubSession());
		MojaveSession session = new MojaveSession(e.getGitHubSession(), user, em);
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
		getView().setContent(workspacePresenter.getView().getComponent());
	}
}
