package com.darkside.mojave.web;

import org.pakhama.vaadin.mvp.ui.MVPApplication;
import org.vaadin.artur.icepush.ICEPush;

import com.darkside.mojave.web.mvp.ide.presenter.IDEPresenter;
import com.darkside.mojave.web.mvp.model.Project;
import com.darkside.mojave.web.mvp.model.ProjectSession;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class Funderpable extends MVPApplication {
	
	//TODO: Short-term: fix layout, add save button and attach to saveFile method in EditorSession
	//TODO: Long-term: add support for multiple files per sessions and multiple sessions for the application
	//TODO: Hackathon: add figure out how input params will work
	
	private static final ProjectSession projectSession = new ProjectSession(4L, null);
	private ICEPush pusher =  new ICEPush();
	
	private Window window = new Window("Mojave Collaborative IDE");
	private VerticalLayout layout = new VerticalLayout();
	
    @Override
    public void init() {
    	Project testProject = new Project();
    	testProject.setPath("C:\\Users\\Tom\\Documents\\Personal\\Java\\test");
    	testProject.setName("PROJECT");
    	projectSession.setProject(testProject);
    	
    	IDEPresenter idePresenter = createPresenter(IDEPresenter.class);
    	idePresenter.init(projectSession, "caputit1", pusher);
    	setMainWindow(window);
    	window.setSizeFull();
    	window.getContent().setSizeFull();
    	window.addComponent(layout);
    	Component ide = idePresenter.getView().getComponent();
    	layout.addComponent(pusher);
    	layout.setSizeFull();
    	layout.setExpandRatio(pusher, 0.0f);
    	layout.addComponent(ide);
    	layout.setExpandRatio(ide, 1.0f);
    	ide.setSizeFull();
    }
}

