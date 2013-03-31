package com.darkside.mojave.web.mvp.filebroswer.view;

import java.io.File;

import org.pakhama.vaadin.mvp.event.EventScope;
import org.pakhama.vaadin.mvp.view.impl.View;

import com.darkside.mojave.web.mvp.filebroswer.event.FileOpenEvent;
import com.darkside.mojave.web.mvp.filebroswer.event.NodeExpandEvent;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Tree;
import com.vaadin.ui.Tree.ExpandEvent;
import com.vaadin.ui.Tree.ExpandListener;

public class FileBrowserView extends View implements IFileBrowserView{
	private static final long serialVersionUID = -912854854936184981L;

	private Panel explorerPanel = new Panel();
	private Tree tree = new Tree();

	public FileBrowserView(){
		addComponent(explorerPanel);
		explorerPanel.addComponent(tree);
		explorerPanel.setHeight("100%");
		explorerPanel.setScrollable(true);
		setExpandRatio(explorerPanel, 1.0f);
		explorerPanel.setWidth("100%");
		tree.addListener(new ExpandListener() {
			private static final long serialVersionUID = 2009509321192976933L;
			@Override
			public void nodeExpand(ExpandEvent event) {
				FileBrowserView.this.dispatch(new NodeExpandEvent((File)event.getItemId()), EventScope.PARENT);
			}
		});

		tree.addListener(new ItemClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void itemClick(ItemClickEvent event) {
				if(event.isDoubleClick()){
					FileBrowserView.this.dispatch(new NodeExpandEvent((File)event.getItemId()), EventScope.PARENT);
				}
			}
		});
		setSizeFull();
	}

	@Override
	public void populateNodeOrOpen(File subdir) {
		final File[] files = subdir.listFiles();

		if(subdir.isFile()){
			dispatch(new FileOpenEvent(subdir), EventScope.PARENT);
		}else{
			for (int x = 0; x < files.length; x++) {
				tree.addItem(files[x]);
				tree.setItemCaption(files[x], files[x].getName());
				// set parent if this item has one
				tree.setParent(files[x], subdir);
				// check if item is a directory and read access exists
				if (files[x].isDirectory() && files[x].canRead()) tree.setChildrenAllowed(files[x], true);
				else tree.setChildrenAllowed(files[x], false);
			}
		}
	}
}
