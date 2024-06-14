package com.lazygroup.lazysis.sinhvien;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lazygroup.lazysis.root.RootModel;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Task;
import javafx.scene.control.Tab;
import javafx.scene.layout.Region;
import net.rgielen.fxweaver.core.FxWeaver;

@Component
public class SinhVienController {

	private SinhVienModel model;
	private final SinhVienInteractor interactor;
	private final SinhVienViewBuilder viewBuilder;

	@Autowired
	SinhVienController(SinhVienModel model, SinhVienInteractor interactor,
			FxWeaver fxWeaver) {
		this.model = model;
		this.interactor = interactor;
		viewBuilder = new SinhVienViewBuilder(fxWeaver, this::load);
	}

	// init method
	public void bindToParentModel(StringProperty username, StringProperty password, StringProperty site,
			BooleanProperty loggedIn) {
		model.usernameProperty().bind(username);
		model.passwordProperty().bind(password);
		model.siteProperty().bind(site);

		loggedIn.addListener((ob) -> {
			if (loggedIn.get()) {
				load();
			}
		});
	}

	public void load() {
		Task<List<SinhVienModelItem>> loadTask = new Task<>() {

			@Override
			protected List<SinhVienModelItem> call() throws Exception {
				return interactor.fetchData();
			}

		};

		loadTask.setOnSucceeded(evt -> {
			List<SinhVienModelItem> data = loadTask.getValue();
			interactor.updateModel(data);
		});
		loadTask.setOnFailed(evt -> {
			loadTask.getException().printStackTrace();
		});

		Thread loadThread = new Thread(loadTask);
		loadThread.start();
	}

	public void them(SinhVienModelItem modelItem) {
		Task<Void> themTask = new Task<>() {
			@Override
			protected Void call() throws Exception {
				interactor.themSinhVien(modelItem);
				return null;
			}
		};

		themTask.setOnSucceeded(evt -> {
			interactor.updateModel(modelItem);
		});

		themTask.setOnFailed(evt -> {
			themTask.getException().printStackTrace();
		});
	}

	public void sua(SinhVienModelItem modelItem) {
		Task<Void> suaTask = new Task<>() {

			@Override
			protected Void call() throws Exception {
				interactor.suaSinhVien(modelItem);
				return null;
			}
		};

		suaTask.setOnSucceeded(evt -> {
			interactor.updateModel(model.getSelectedItem().getMaSv(), modelItem);
		});
	}

	public void xoa(Runnable postActionGuiStuffs) {
		Task<Void> xoaTask = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				String maSv = model.getSelectedItem().getMaSv();
				interactor.xoaSinhVien(maSv);
				return null;
			}
		};
		xoaTask.setOnSucceeded(evt -> interactor.updateModel(model.getSelectedItem().getMaSv()));
	}

	public Region getView() {
		return viewBuilder.build();
	}
}
