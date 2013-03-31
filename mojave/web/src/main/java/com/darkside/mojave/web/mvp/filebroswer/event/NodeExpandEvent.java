package com.darkside.mojave.web.mvp.filebroswer.event;

import java.io.File;

import org.pakhama.vaadin.mvp.event.impl.Event;

public class NodeExpandEvent extends Event{
	
	private static final long serialVersionUID = 5899818923271391355L;
	
	private File file;
	
	public NodeExpandEvent(){
	}
	
	public NodeExpandEvent(File file) {
		this.file = file;

	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
}
