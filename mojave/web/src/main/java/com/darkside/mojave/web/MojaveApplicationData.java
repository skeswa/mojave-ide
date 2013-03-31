package com.darkside.mojave.web;

import java.io.Serializable;

import com.darkside.mojave.web.session.MojaveSession;
import com.vaadin.Application;
import com.vaadin.service.ApplicationContext.TransactionListener;

/** Holds data for one user session. */
public class MojaveApplicationData implements TransactionListener, Serializable {
	private static final long serialVersionUID = 7328171664485032692L;

	private MojaveSession session;

	private Application app; // For distinguishing between apps

	private static ThreadLocal<MojaveApplicationData> instance = new ThreadLocal<MojaveApplicationData>();

	public MojaveApplicationData(Application app) {
		this.app = app;

		// It's usable from now on in the current request
		instance.set(this);
	}

	@Override
	public void transactionStart(Application application, Object transactionData) {
		// Set this data instance of this application
		// as the one active in the current thread.
		if (this.app == application)
			instance.set(this);
	}

	@Override
	public void transactionEnd(Application application, Object transactionData) {
		// Clear the reference to avoid potential problems
		if (this.app == application)
			instance.set(null);
	}

	public static MojaveSession getSession() {
		if (instance == null || instance.get() == null) {
			return null;
		} else {
			return instance.get().session;
		}
	}

	public static void setSession(MojaveSession session) {
		if (instance != null && instance.get() != null) {
			instance.get().session = session;
		}
	}

}