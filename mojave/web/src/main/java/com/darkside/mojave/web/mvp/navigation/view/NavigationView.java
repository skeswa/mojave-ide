package com.darkside.mojave.web.mvp.navigation.view;

import org.pakhama.vaadin.mvp.view.impl.View;

public class NavigationView extends View implements INavigationView {
	private static final long serialVersionUID = 5033618986959170984L;

	private static final String NAVIGATION_VIEW_STYLE = "mojave-navigation";
	private static final String NAVIGATION_VIEW_WIDTH = "45px";
	private static final String NAVIGATION_VIEW_HEIGHT = "100%";
	
	public NavigationView() {
		addStyleName(NAVIGATION_VIEW_STYLE);
		setWidth(NAVIGATION_VIEW_WIDTH);
		setHeight(NAVIGATION_VIEW_HEIGHT);
	}
}
