package com.lazygroup.lazysis.lop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.beans.property.StringProperty;
import javafx.concurrent.Task;
import javafx.scene.layout.Region;
import net.rgielen.fxweaver.core.FxWeaver;

@Component
public class LopController {

	private final LopModel model;
	private final LopInteractor interactor;
	private final LopViewBuilder viewBuilder;

	@Autowired
	LopController(LopModel model, LopInteractor interactor, FxWeaver fxWeaver) {
		this.model = model;
		this.interactor = interactor;
		this.viewBuilder = new LopViewBuilder(model, fxWeaver);
	}

	public void bindToParentModel(StringProperty username, StringProperty password, StringProperty site) {
		model.usernameProperty().bind(username);
		model.passwordProperty().bind(password);
		model.siteProperty().bind(site);
	}

	public void themLop(LopModelItem modelItem) {
		Task<Void> themTask = new Task<>() {
			@Override
			protected Void call() throws Exception {
				interactor.themLop(modelItem);
				return null;
			}
		};

		themTask.setOnSucceeded(evt -> {
			interactor.updateModel(modelItem);
		});

		Thread themThread = new Thread(themTask);
		themThread.start();
	}

	public void suaLop(LopModelItem modelItem) {
		Task<Void> suaTask = new Task<>() {
			@Override
			protected Void call() throws Exception {
				interactor.suaLop(modelItem);
				return null;
			}
		};

		suaTask.setOnSucceeded(evt -> {
			interactor.updateModel(model.getSelectedItem().getMaLop(), modelItem);
		});

		Thread suaThread = new Thread(suaTask);
		suaThread.start();
	}

	public void xoaLop() {
		Task<Void> xoaTask = new Task<>() {
			@Override
			protected Void call() throws Exception {
				interactor.xoaLop();
				return null;
			}
		};

		xoaTask.setOnSucceeded(evt -> {
			interactor.updateModel(model.getSelectedItem().getMaLop());
		});

		Thread xoaThread = new Thread(xoaTask);
		xoaThread.start();
	}

	public Region getView() {
		return viewBuilder.build();
	}
}
