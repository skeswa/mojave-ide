package com.darkside.mojave.web.mvp.editor.view;

import java.io.File;
import java.util.HashMap;

import org.pakhama.vaadin.mvp.event.EventScope;
import org.pakhama.vaadin.mvp.view.impl.View;
import org.vaadin.aceeditor.collab.DocDiff;
import org.vaadin.aceeditor.collab.gwt.shared.Doc;
import org.vaadin.artur.icepush.ICEPush;
import org.vaadin.diffsync.Shared;

import com.darkside.mojave.web.mvp.CollaborativeEditor;
import com.darkside.mojave.web.mvp.editor.event.SwitchFileEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.CloseHandler;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.TabSheet.SelectedTabChangeListener;
import com.vaadin.ui.TabSheet.Tab;

public class EditorView extends View implements IEditorView{
	private static final long serialVersionUID = 8720241933920091350L; 

	HashMap<CollaborativeEditor, File> editors = new HashMap<CollaborativeEditor, File>();
	private TabSheet tabSheet = new TabSheet();

	public EditorView(){
		tabSheet.setSizeFull();
		tabSheet.addListener(new SelectedTabChangeListener() {
			private static final long serialVersionUID = -1956438755250247642L;

			@Override
			public void selectedTabChange(SelectedTabChangeEvent event) {
				EditorView.this.dispatch(new SwitchFileEvent(editors.get(tabSheet.getSelectedTab())), EventScope.PARENT);
			}
		});
		
		tabSheet.setCloseHandler(new CloseHandler() {
			private static final long serialVersionUID = 2L;

			@Override
			public void onTabClose(TabSheet tabsheet, Component tabContent) {
				tabsheet.removeComponent(tabContent);
				editors.remove(tabContent);
			}
		});
		addComponent(tabSheet);
		setExpandRatio(tabSheet, 1.0f);
		setSizeFull();
	}

	@Override
	public void createEditor(File file, Shared<Doc, DocDiff> sharedText, ICEPush pusher) {
		CollaborativeEditor newEditor = new CollaborativeEditor(file, sharedText, pusher);
		editors.put(newEditor, file);
		Tab tab = tabSheet.addTab(newEditor);
		tab.setClosable(true);
		tab.setCaption(file.getName());
		tabSheet.setSelectedTab(newEditor);
		newEditor.setSizeFull();
	}

	@Override
	public void openToEditor(File file, Shared<Doc, DocDiff> sharedText, ICEPush pusher) {
		if(!editors.containsValue(file)){
			CollaborativeEditor newEditor = new CollaborativeEditor(file, sharedText, pusher);
			editors.put(newEditor, file);
			Tab tab = tabSheet.addTab(newEditor);
			tab.setClosable(true);
			tab.setCaption(file.getName());
			tabSheet.setSelectedTab(newEditor);
			newEditor.setSizeFull();
		}else{
			for(CollaborativeEditor ce : editors.keySet()){
				if(editors.get(ce) == file){
					tabSheet.setSelectedTab(ce);
				}
			}
		}
	}
}
