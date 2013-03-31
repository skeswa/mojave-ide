package com.darkside.mojave.web;

import java.io.File;
import java.io.IOException;

import com.vaadin.data.Item;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Tree;
import com.vaadin.ui.Tree.ExpandEvent;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class FileBrowser extends VerticalLayout{

	private Panel explorerPanel = new Panel("Filesystem explorer");
	private Tree tree = new Tree();
	
	public FileBrowser(File directory){
		addComponent(explorerPanel);
		explorerPanel.addComponent(tree);
		explorerPanel.setHeight("100%");
		explorerPanel.setScrollable(true);
		setExpandRatio(explorerPanel, 1.0f);
		explorerPanel.setWidth("100%");
		tree.addListener(new TreeListener());
		if(directory != null) populateNode(directory.getAbsolutePath(), null);
	}
	
	/**
	 * Populates files to tree as items. In this example items are of String
	 * type that consist of file path. New items are added to tree and item's
	 * parent and children properties are updated.
	 * 
	 * @param file
	 *            path which contents are added to tree
	 * @param parent
	 *            for added nodes, if null then new nodes are added to root node
	 */
	private void populateNode(String file, Object parent) {
		final File subdir = new File(file);
		final File[] files = subdir.listFiles();
		for (int x = 0; x < files.length; x++) {
			String path = "";
			try {
				path = files[x].getCanonicalPath().toString();
			} catch (IOException e) {
				e.printStackTrace();
			}
			tree.addItem(path);
			// set parent if this item has one
			if (parent != null) tree.setParent(path, parent);
			// check if item is a directory and read access exists
			if (files[x].isDirectory() && files[x].canRead()) tree.setChildrenAllowed(path, true);
			else tree.setChildrenAllowed(path, false);
		}
	}
	
	private class TreeListener implements Tree.ExpandListener{

		/**
		 * Handle tree expand event, populate expanded node's children with new files
		 * and directories.
		 */
		@Override
		public void nodeExpand(ExpandEvent event) {
			final Item i = tree.getItem(event.getItemId());
			if (!tree.hasChildren(i)) {
				// populate tree's node which was expanded
				populateNode(event.getItemId().toString(), event.getItemId());
			}
		}
	}
}
