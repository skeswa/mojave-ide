package com.darkside.mojave.web;

import java.io.File;
import java.util.HashMap;
import java.util.Set;

import org.pakhama.vaadin.mvp.event.IEventDispatcher;
import org.vaadin.aceeditor.collab.DocDiff;
import org.vaadin.aceeditor.collab.gwt.shared.Doc;
import org.vaadin.diffsync.Shared;

public class EditorSession {

	private HashMap<File, Shared<Doc, DocDiff>> sharedTextMap = new HashMap<File, Shared<Doc, DocDiff>>();

	public EditorSession(){
	}

	public Set<File> getFiles(){
		return sharedTextMap.keySet();
	}

	public Shared<Doc, DocDiff> getShared(File file){
		return sharedTextMap.get(file);
	}

	public void addFile(File file, Shared<Doc, DocDiff> shared, IEventDispatcher dispatcher){
		if(sharedTextMap.get(file) == null){
			sharedTextMap.put(file, shared);
		}
	}

	public void removeFile(File file){
		sharedTextMap.remove(file);
	}
}
