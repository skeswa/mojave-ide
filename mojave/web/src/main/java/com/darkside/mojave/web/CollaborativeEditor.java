package com.darkside.mojave.web;

import java.io.File;

import org.vaadin.aceeditor.collab.DocDiff;
import org.vaadin.aceeditor.collab.SuggestibleCollabAceEditor;
import org.vaadin.aceeditor.collab.gwt.shared.Doc;
import org.vaadin.aceeditor.gwt.ace.AceMode;
import org.vaadin.artur.icepush.ICEPush;
import org.vaadin.diffsync.Shared;
import org.vaadin.diffsync.gwt.shared.SendPolicy;

import com.vaadin.terminal.PaintException;
import com.vaadin.terminal.PaintTarget;

@SuppressWarnings("serial")
public class CollaborativeEditor extends SuggestibleCollabAceEditor{

	private ICEPush pusher;

	public CollaborativeEditor(File file, Shared<Doc, DocDiff> sharedText, ICEPush pusher) {
		super(sharedText);
		this.pusher = pusher;
		
		setSendPolicy(SendPolicy.IMMEDIATELY);
		
		String extension = file.getName().substring(file.getName().lastIndexOf(".") + 1);
		if(extension.equalsIgnoreCase("asc")) setMode(AceMode.ascii_doc);
		else if(extension.equalsIgnoreCase("cpp") || extension.equalsIgnoreCase("c") || extension.equalsIgnoreCase("h")) setMode(AceMode.c_cpp);
		else if(extension.equalsIgnoreCase("cs")) setMode(AceMode.csharp);
		else if(extension.equalsIgnoreCase("css")) setMode(AceMode.css);
		else if(extension.equalsIgnoreCase("html")) setMode(AceMode.html);
		else if(extension.equalsIgnoreCase("java")) setMode(AceMode.java);
		else if(extension.equalsIgnoreCase("js")) setMode(AceMode.javascript);
		else if(extension.equalsIgnoreCase("json")) setMode(AceMode.json);
		else if(extension.equalsIgnoreCase("php")) setMode(AceMode.php);
		else if(extension.equalsIgnoreCase("pl")) setMode(AceMode.perl);
		else if(extension.equalsIgnoreCase("py")) setMode(AceMode.python);
		else if(extension.equalsIgnoreCase("rb") || extension.equalsIgnoreCase("rbw")) setMode(AceMode.ruby);
		else if(extension.equalsIgnoreCase("sql")) setMode(AceMode.sql);
		else if(extension.equalsIgnoreCase("scss")) setMode(AceMode.scss);
		else if(extension.equalsIgnoreCase("xml")) setMode(AceMode.xml);
		else setMode(AceMode.text);
	}

	@Override
	protected void paintDiff(DocDiff diff, PaintTarget target) throws PaintException {
		super.paintDiff(diff, target);
		pusher.push();
	}
}
