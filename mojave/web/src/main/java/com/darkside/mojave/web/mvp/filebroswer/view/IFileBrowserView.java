package com.darkside.mojave.web.mvp.filebroswer.view;

import java.io.File;

import org.pakhama.vaadin.mvp.view.IView;

public interface IFileBrowserView extends IView{
	public void populateNodeOrOpen(File subdir);
}
