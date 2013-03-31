package com.darkside.mojave.web.mvp.editor.event;

import java.io.File;

import org.pakhama.vaadin.mvp.event.impl.Event;

public class SwitchFileEvent extends Event{
	private static final long serialVersionUID = -7916732103061321562L;

	private File file;
	
	public SwitchFileEvent(){
	}
	
	public SwitchFileEvent(File file){
		this.setFile(file);
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
}
