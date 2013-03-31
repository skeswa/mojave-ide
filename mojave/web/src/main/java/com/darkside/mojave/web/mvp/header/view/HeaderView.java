package com.darkside.mojave.web.mvp.header.view;

import org.pakhama.vaadin.mvp.event.EventScope;
import org.pakhama.vaadin.mvp.view.impl.View;

import com.darkside.mojave.web.mvp.header.event.BuildEvent;
import com.darkside.mojave.web.mvp.header.event.CommitEvent;
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
	private Button commitButton = new Button("Commit");
	
	public HeaderView(){
		saveButton.setWidth("100px");
		saveButton.addListener(new ClickListener() {
			private static final long serialVersionUID = -2532681713979871463L;

			@Override
			public void buttonClick(ClickEvent event) {
				dispatch(new SaveFileEvent(), EventScope.PARENT);
			}
		});
		
		buildButton.setWidth("100px");
		buildButton.addListener(new ClickListener() {
			private static final long serialVersionUID = -2532681713979871463L;

			@Override
			public void buttonClick(ClickEvent event) {
				dispatch(new BuildEvent(), EventScope.PARENT);
			}
		});
		
		commitButton.setWidth("100px");
		commitButton.addListener(new ClickListener() {
			private static final long serialVersionUID = -2532681713979871463L;

			@Override
			public void buttonClick(ClickEvent event) {
				dispatch(new CommitEvent(), EventScope.PARENT);
			}
		});
		
		layout.addComponent(saveButton);
		layout.addComponent(buildButton);
		layout.addComponent(commitButton);
		addComponent(layout);
	}
}
