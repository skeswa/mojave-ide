package com.darkside.mojave.web.mvp.workspace.actionbar.view;

import org.pakhama.vaadin.mvp.view.impl.View;

public class ActionBarView extends View implements IActionBarView {
	private static final long serialVersionUID = 6072619260070819343L;
	
	private static final String ACTION_BAR_VIEW_STYLE = "mojave-action-bar";
	private static final String ACTION_BAR_VIEW_WIDTH = "100%";
	private static final String ACTION_BAR_VIEW_HEIGHT = "100%";
	
	public ActionBarView() {
		addStyleName(ACTION_BAR_VIEW_STYLE);
		setWidth(ACTION_BAR_VIEW_WIDTH);
		setHeight(ACTION_BAR_VIEW_HEIGHT);
	}

}
