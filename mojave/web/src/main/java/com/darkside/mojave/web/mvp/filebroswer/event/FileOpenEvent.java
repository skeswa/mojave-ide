package com.darkside.mojave.web.mvp.filebroswer.event;

import java.io.File;

import org.pakhama.vaadin.mvp.event.impl.Event;

public class FileOpenEvent extends Event{
	private static final long serialVersionUID = 6092947819767878875L;

	private File file;
	
	public FileOpenEvent(){
	}
	
	public FileOpenEvent(File file){
		this.setFile(file);
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
}
