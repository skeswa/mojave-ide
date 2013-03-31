package com.darkside.mojave.web.mvp.ide.presenter;

import java.util.Arrays;

import org.pakhama.vaadin.mvp.annotation.event.EventListener;
import org.pakhama.vaadin.mvp.event.EventScope;
import org.pakhama.vaadin.mvp.presenter.impl.Presenter;
import org.phakama.maven.MavenBuilder;
import org.phakama.maven.execution.MavenBuildRecord;
import org.phakama.maven.execution.logging.MavenBuildRecordCallback;
import org.phakama.maven.model.MavenBuildProject;
import org.phakama.maven.model.MavenEnvironmentConfiguration;
import org.vaadin.artur.icepush.ICEPush;

import com.darkside.mojave.web.MojaveApplicationData;
import com.darkside.mojave.web.mvp.chat.presenter.ChatPresenter;
import com.darkside.mojave.web.mvp.collaborators.presenter.CollaboratorsPresenter;
import com.darkside.mojave.web.mvp.console.event.ConsoleUpdateEvent;
import com.darkside.mojave.web.mvp.console.presenter.ConsolePresenter;
import com.darkside.mojave.web.mvp.editor.presenter.EditorPresenter;
import com.darkside.mojave.web.mvp.filebroswer.presenter.FileBrowserPresenter;
import com.darkside.mojave.web.mvp.header.event.BuildEvent;
import com.darkside.mojave.web.mvp.header.presenter.HeaderPresenter;
import com.darkside.mojave.web.mvp.ide.ProjectSession;
import com.darkside.mojave.web.mvp.ide.event.NotificationEvent;
import com.darkside.mojave.web.mvp.ide.view.IIDEView;
import com.darkside.mojave.web.session.MojaveSession;

public class IDEPresenter extends Presenter<IIDEView>{
	private static final long serialVersionUID = -9168881220884649657L;
	
	private ProjectSession projectSession;
	private MojaveSession session;
	private ICEPush pusher;
	public void init(ProjectSession ps, ICEPush pusher){
		this.projectSession = ps;
		this.pusher = pusher;
		this.session = MojaveApplicationData.getSession();
		
		HeaderPresenter headerPresenter = createChild(HeaderPresenter.class);
		headerPresenter.init(ps.getSessionId());
		ChatPresenter chatPresenter = createChild(ChatPresenter.class);
		chatPresenter.init(ps.getSessionId(), this.session.getUser().getUserName(), pusher);
		FileBrowserPresenter fileBrowserPresenter = createChild(FileBrowserPresenter.class);
		fileBrowserPresenter.init(this.projectSession.getBuildProject().getProjectDir()); //TODO: integrate with Sandizzle
		EditorPresenter editorPresenter = createChild(EditorPresenter.class);
		editorPresenter.init(ps.getSessionId(), ps.getEs(), pusher);
		CollaboratorsPresenter collaboratorsPresenter = createChild(CollaboratorsPresenter.class);
		collaboratorsPresenter.init(ps.getSessionId(), ps, pusher);
		ConsolePresenter consolePresenter = createChild(ConsolePresenter.class);
		consolePresenter.init(projectSession.getSessionId(), pusher);
		
		projectSession.joinSession(this);
		
		getView().setTop(headerPresenter.getView().getComponent());
		getView().setLeft(fileBrowserPresenter.getView().getComponent());
		getView().setMid(editorPresenter.getView().getComponent());
		getView().setRight(chatPresenter.getView().getComponent());
		getView().setBottom(consolePresenter.getView().getComponent());
		getView().setProjectName(ps.getProject().getName());
		getView().setCollaboratorsView(collaboratorsPresenter.getView().getComponent());
	}
	
	@EventListener(event = BuildEvent.class)
	public void onBuild(BuildEvent e) {
		MavenBuildProject buildProject = this.projectSession.getBuildProject();
		MavenEnvironmentConfiguration buildConfig = this.session.getMavenEnvironmentConfiguration();
		
		MavenBuilder.build(buildConfig, buildProject, Arrays.asList("clean", "install"), new MavenBuildRecordCallback(buildConfig, buildProject) {
			
			@Override
			public void onLog(String log) {
				dispatch(new ConsoleUpdateEvent(projectSession.getSessionId(), log), EventScope.UNIVERSAL);
			}
			
			@Override
			public void onFinish(MavenBuildRecord record, boolean success) {
				dispatch(new NotificationEvent(projectSession.getSessionId(), "Build Completed"), EventScope.UNIVERSAL);
			}
		});
	}
	
	@EventListener(event = NotificationEvent.class)
	public void onNotificationEvent(NotificationEvent e){
		if(e.getSessionId() == projectSession.getSessionId()){
			getView().printToast(e.getMessage());
			pusher.push();
		}
	}
}
