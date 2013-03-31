package com.darkside.mojave.web.mvp.workspace.presenter;

import org.pakhama.vaadin.mvp.presenter.impl.Presenter;

import com.darkside.mojave.web.mvp.navigation.presenter.NavigationPresenter;
import com.darkside.mojave.web.mvp.workspace.actionbar.presenter.ActionBarPresenter;
import com.darkside.mojave.web.mvp.workspace.view.IWorkspaceView;

public class WorkspacePresenter extends Presenter<IWorkspaceView> {
	private static final long serialVersionUID = -7088584207923447991L;

	@Override
	public void onBind(IWorkspaceView view) {
		super.onBind(view);
		
		ActionBarPresenter actionBarPresenter = createChild(ActionBarPresenter.class);
		NavigationPresenter navigationPresenter = createChild(NavigationPresenter.class);
		
		getView().setTop(actionBarPresenter.getView().getComponent());
		getView().setLeft(navigationPresenter.getView().getComponent());
	}
}
