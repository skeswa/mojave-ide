package com.darkside.mojave.web.mvp.workspace.presenter;

import java.util.concurrent.ConcurrentHashMap;

import org.pakhama.vaadin.mvp.annotation.event.EventListener;
import org.pakhama.vaadin.mvp.presenter.impl.Presenter;
import org.vaadin.artur.icepush.ICEPush;

import com.darkside.mojave.domain.Project;
import com.darkside.mojave.web.mvp.ide.ProjectSession;
import com.darkside.mojave.web.mvp.ide.presenter.IDEPresenter;
import com.darkside.mojave.web.mvp.navigation.event.ProjectNavigationEvent;
import com.darkside.mojave.web.mvp.navigation.presenter.NavigationPresenter;
import com.darkside.mojave.web.mvp.workspace.actionbar.presenter.ActionBarPresenter;
import com.darkside.mojave.web.mvp.workspace.view.IWorkspaceView;

public class WorkspacePresenter extends Presenter<IWorkspaceView> {
	private static final long serialVersionUID = -7088584207923447991L;

	private static final ConcurrentHashMap<Long, ProjectSession> projectSessions = new ConcurrentHashMap<Long, ProjectSession>();

	private ICEPush pusher;
	
	@Override
	public void onBind(IWorkspaceView view) {
		super.onBind(view);
		
		ActionBarPresenter actionBarPresenter = createChild(ActionBarPresenter.class);
		NavigationPresenter navigationPresenter = createChild(NavigationPresenter.class);

		getView().setTop(actionBarPresenter.getView().getComponent());
		getView().setLeft(navigationPresenter.getView().getComponent());

		// FIXME
		if (projectSessions.get(1L) == null) {
			Project testProject = new Project();
			testProject.setPath("E:\\dev\\repos\\test");
			testProject.setName("PROJECT");

			ProjectSession testProjectSession = new ProjectSession(43287496L, testProject);
			projectSessions.put(1L, testProjectSession);
		}
	}
	
	public void init(ICEPush pusher) {
		this.pusher = pusher;
	}

	@EventListener(event = ProjectNavigationEvent.class)
	public void onProjectNavigation(ProjectNavigationEvent e) {
		IDEPresenter idePresenter = createChild(IDEPresenter.class);
		idePresenter.init(projectSessions.get(1L), this.pusher);
		getView().setContent(idePresenter.getView().getComponent());
	}
}
