package com.darkside.mojave.web.mvp.editor.presenter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.pakhama.vaadin.mvp.annotation.event.EventListener;
import org.pakhama.vaadin.mvp.event.EventScope;
import org.pakhama.vaadin.mvp.presenter.impl.Presenter;
import org.vaadin.aceeditor.collab.DocDiff;
import org.vaadin.aceeditor.collab.gwt.shared.Doc;
import org.vaadin.artur.icepush.ICEPush;
import org.vaadin.diffsync.Shared;

import com.darkside.mojave.web.mvp.EditorSession;
import com.darkside.mojave.web.mvp.editor.event.SwitchFileEvent;
import com.darkside.mojave.web.mvp.editor.view.IEditorView;
import com.darkside.mojave.web.mvp.filebroswer.event.FileOpenEvent;
import com.darkside.mojave.web.mvp.header.event.SaveFileEvent;
import com.darkside.mojave.web.mvp.ide.event.NotificationEvent;

public class EditorPresenter extends Presenter<IEditorView>{
	private static final long serialVersionUID = 2361280631532615525L;

	private long sessionId;
	private EditorSession es;
	private File currentFile;
	private ICEPush pusher;

	public void init(long sessionId, EditorSession es, ICEPush pusher){
		this.sessionId = sessionId;
		this.es = es;
		this.pusher = pusher;
		this.currentFile = null;
	}

	@EventListener(event = SaveFileEvent.class)
	public void saveFile(SaveFileEvent event){
		if(currentFile == null) {
			dispatch(new NotificationEvent(sessionId, "Nothing to save..."), EventScope.PARENT);
		}else{
			dispatch(new NotificationEvent(sessionId, "File Saved"), EventScope.UNIVERSAL);
			try{
				FileWriter fstream = new FileWriter(currentFile);
				BufferedWriter out = new BufferedWriter(fstream);
				out.write(es.getShared(currentFile).getValue().getText());
				out.close();
			}catch (Exception e){
				e.printStackTrace();
			}
		}
	}

	@EventListener(event = SwitchFileEvent.class)
	public void onSwitchFile(SwitchFileEvent event){
		this.currentFile = event.getFile();
	}

	@EventListener(event = FileOpenEvent.class)
	public void openFile(FileOpenEvent event) throws IOException{
		currentFile = event.getFile();
		if(es.getFiles().contains(currentFile)){
			getView().openToEditor(currentFile, es.getShared(currentFile), pusher);
		}else{
			Shared<Doc, DocDiff> sharedText = new Shared<Doc, DocDiff>(new Doc(readFileToString(currentFile)));
			getView().createEditor(currentFile, sharedText, pusher);
			es.addFile(currentFile, sharedText, this);
		}
	}

	private String readFileToString(File file) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(file));;
		String currentLine;
		StringBuilder builder = new StringBuilder();
		while ((currentLine = br.readLine()) != null) {
			builder.append(currentLine + "\n");
		}
		br.close();
		return builder.toString();
	}
}
