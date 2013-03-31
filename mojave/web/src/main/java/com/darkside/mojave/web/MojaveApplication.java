package com.darkside.mojave.web;

import org.pakhama.vaadin.mvp.ui.MVPApplication;
import org.vaadin.artur.icepush.ICEPush;

import com.darkside.mojave.web.mvp.presenter.MojavePresenter;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class MojaveApplication extends MVPApplication {
	private static final long serialVersionUID = -1622405491837687433L;
	private static final String THEME_NAME = "mojave";

	private Window mainWindow = new Window();
	private ICEPush pusher = new ICEPush();

	@Override
	public void init() {
		setTheme(THEME_NAME);

		VerticalLayout windowLayout = new VerticalLayout();
		windowLayout.setSizeFull();
		windowLayout.setMargin(false);
		
		this.mainWindow.setContent(windowLayout);
		this.mainWindow.setSizeFull();
		setMainWindow(this.mainWindow);
		
		this.pusher = new ICEPush();
		windowLayout.addComponent(this.pusher);
		windowLayout.setExpandRatio(this.pusher, 0.0f);

		// Initialize session data
		if (MojaveApplicationData.getSession() == null) {
			MojaveApplicationData sessionData = new MojaveApplicationData(this);
			getContext().addTransactionListener(sessionData);
		}

		MojavePresenter mojavePresenter = createPresenter(MojavePresenter.class);
		mojavePresenter.init(this.pusher);
		// Add the mojave presenter's view to the window
		windowLayout.addComponent(mojavePresenter.getView().getComponent());
		windowLayout.setExpandRatio(mojavePresenter.getView().getComponent(), 1.0f);
	}

}
