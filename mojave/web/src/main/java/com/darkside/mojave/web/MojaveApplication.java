package com.darkside.mojave.web;

import org.pakhama.vaadin.mvp.ui.MVPApplication;

import com.darkside.mojave.web.mvp.presenter.MojavePresenter;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class MojaveApplication extends MVPApplication {
	private static final long serialVersionUID = -1622405491837687433L;
	private static final String THEME_NAME = "mojave";

	private Window mainWindow = new Window();

	@Override
	public void init() {
		setTheme(THEME_NAME);

		VerticalLayout windowLayout = new VerticalLayout();
		windowLayout.setSizeFull();
		windowLayout.setMargin(false);

		this.mainWindow.setContent(windowLayout);
		this.mainWindow.setSizeFull();
		setMainWindow(this.mainWindow);

		// Initialize session data
		if (MojaveApplicationData.getSession() == null) {
			MojaveApplicationData sessionData = new MojaveApplicationData(this);
			getContext().addTransactionListener(sessionData);
		}

		MojavePresenter mojavePresenter = createPresenter(MojavePresenter.class);
		this.mainWindow.addComponent(mojavePresenter.getView().getComponent());
	}

}
