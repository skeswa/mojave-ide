package com.darkside.mojave.web.mvp.editor.view;

import java.io.File;

import org.pakhama.vaadin.mvp.view.IView;
import org.vaadin.aceeditor.collab.DocDiff;
import org.vaadin.aceeditor.collab.gwt.shared.Doc;
import org.vaadin.artur.icepush.ICEPush;
import org.vaadin.diffsync.Shared;

public interface IEditorView extends IView {
	public void createEditor(File file, Shared<Doc, DocDiff> sharedText, ICEPush pusher);
	public void openToEditor(File file, Shared<Doc, DocDiff> sharedText, ICEPush pusher);
}
