package com.darkside.mojave.web.mvp.navigation.presenter;

import org.pakhama.vaadin.mvp.annotation.event.EventListener;
import org.pakhama.vaadin.mvp.event.EventScope;
import org.pakhama.vaadin.mvp.presenter.impl.Presenter;

import com.darkside.mojave.web.mvp.navigation.event.ProjectNavigationEvent;
import com.darkside.mojave.web.mvp.navigation.view.INavigationView;

public class NavigationPresenter extends Presenter<INavigationView> {
	private static final long serialVersionUID = 7941536230125066878L;

	@EventListener(event = ProjectNavigationEvent.class)
	public void onProjectNavigation(ProjectNavigationEvent e) {
		dispatch(e, EventScope.PARENT);
	}
}
