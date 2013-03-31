package com.darkside.mojave.web.mvp.view;

import org.pakhama.vaadin.mvp.view.impl.View;

import com.vaadin.ui.Component;

public class MojaveView extends View implements IMojaveView {
	private static final long serialVersionUID = -5464383357362389149L;

	public MojaveView() {
		setSizeFull();
	}

	public void setContent(Component component) {
		removeAllComponents();
		component.setSizeFull();
		addComponent(component);
		setExpandRatio(component, 1.0f);
		
		setSizeFull();
	}
}
