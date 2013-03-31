package com.darkside.mojave.web.mvp.navigation.view;

import org.pakhama.vaadin.mvp.event.EventScope;
import org.pakhama.vaadin.mvp.view.impl.View;

import com.darkside.mojave.web.mvp.navigation.event.ProjectNavigationEvent;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.NativeButton;

public class NavigationView extends View implements INavigationView {
	private static final long serialVersionUID = 5033618986959170984L;

	private static final String NAVIGATION_VIEW_STYLE = "mojave-navigation";
	private static final String NAVIGATION_VIEW_WIDTH = "45px";
	private static final String NAVIGATION_VIEW_HEIGHT = "100%";
	
	public NavigationView() {
		addStyleName(NAVIGATION_VIEW_STYLE);
		setWidth(NAVIGATION_VIEW_WIDTH);
		setHeight(NAVIGATION_VIEW_HEIGHT);
		
		NativeButton button = new NativeButton("IDE");
		button.setSizeFull();
		button.addListener(new ClickListener() {
			private static final long serialVersionUID = 3032067869431215215L;

			@Override
			public void buttonClick(ClickEvent event) {
				NavigationView.this.dispatch(new ProjectNavigationEvent(), EventScope.PARENT);
			}
		});
		
		addComponent(button);
	}
}
