package com.darkside.mojave.web.mvp.filebroswer.presenter;

import java.io.File;

import org.pakhama.vaadin.mvp.annotation.event.EventListener;
import org.pakhama.vaadin.mvp.event.EventScope;
import org.pakhama.vaadin.mvp.presenter.impl.Presenter;

import com.darkside.mojave.web.mvp.filebroswer.event.FileOpenEvent;
import com.darkside.mojave.web.mvp.filebroswer.event.NodeExpandEvent;
import com.darkside.mojave.web.mvp.filebroswer.view.IFileBrowserView;

public class FileBrowserPresenter extends Presenter<IFileBrowserView>{
	private static final long serialVersionUID = -3637268767473184414L;
	
	public void init(File directory){
		if(directory != null) getView().populateNodeOrOpen(directory);
	}
	
	@EventListener(event = NodeExpandEvent.class)
	public void onNodeExpanded(NodeExpandEvent event){
		getView().populateNodeOrOpen(event.getFile());
	}
	
	@EventListener(event = FileOpenEvent.class)
	public void onFileOpened(FileOpenEvent event){
		if(event.getSource() != this) dispatch(event, EventScope.SIBLINGS);
	}
}
