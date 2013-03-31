package com.darkside.mojave.web.mvp.header.view;

import org.pakhama.vaadin.mvp.event.EventScope;
import org.pakhama.vaadin.mvp.view.impl.View;

import com.darkside.mojave.web.mvp.header.event.BuildEvent;
import com.darkside.mojave.web.mvp.header.event.SaveFileEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;

public class HeaderView extends View implements IHeaderView{
	private static final long serialVersionUID = -2382322714579392754L;
	
	private HorizontalLayout layout = new HorizontalLayout();
	private Button saveButton = new Button("Save");
	private Button buildButton = new Button("Build");
	
	public HeaderView(){
		saveButton.addListener(new ClickListener() {
			private static final long serialVersionUID = -2532681713979871463L;

			@Override
			public void buttonClick(ClickEvent event) {
				dispatch(new SaveFileEvent(), EventScope.PARENT);
			}
		});
		
		buildButton.addListener(new ClickListener() {
			private static final long serialVersionUID = -2532681713979871463L;

			@Override
			public void buttonClick(ClickEvent event) {//TODO: implement build event/ presenter
				dispatch(new BuildEvent(), EventScope.PARENT);
			}
		});
		
		layout.addComponent(saveButton);
		layout.addComponent(buildButton);
		addComponent(layout);
	}
}
