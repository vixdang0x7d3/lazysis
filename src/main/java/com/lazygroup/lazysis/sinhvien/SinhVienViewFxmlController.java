package com.lazygroup.lazysis.sinhvien;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.lazygroup.lazysis.util.TwoColumnGridPane;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import net.rgielen.fxweaver.core.FxmlView;

@FxmlView("sinhvienView.fxml")
@Component
public class SinhVienViewFxmlController implements Initializable {

	@FXML
	VBox results;

	@FXML
	Button undoButton, redoButton, themButton, xoaButton, suaButton;

	private final SinhVienModel model;

	private Runnable loadHandler;
	private Consumer<SinhVienModelItem> themHandler;
	private Consumer<SinhVienModelItem> suaHandler;
	private Consumer<Runnable> xoaHandler;

	@Autowired
	SinhVienViewFxmlController(SinhVienModel model) {
		this.model = model;
	}

	public void setLoadModelHandler(Runnable loadHandler) {
		this.loadHandler = loadHandler;
	}

	public void setThemHandler(Consumer<SinhVienModelItem> themHandler) {
		this.themHandler = themHandler;
	}

	public void setSuaHandler(Consumer<SinhVienModelItem> suaHandler) {
		this.suaHandler = suaHandler;
	}

	public void xoaHandler(Consumer<Runnable> xoaHandler) {
		this.xoaHandler = xoaHandler;
	}

	private Region createTableView() {
		return new SinhVienTableViewBuilder(model).build();
	}

	private void configureThemButton() {
		themButton.setOnAction(evt -> {
			Dialog<SinhVienModelItem> dialog = new SinhVienDialog(null);
			Optional<SinhVienModelItem> modelItem = dialog.showAndWait();
			modelItem.ifPresent((i) -> {
				themHandler.accept(i);
			});
		});
	}

	private void configureSuaButton() {
		suaButton.setOnAction(evt -> {
			Dialog<SinhVienModelItem> dialog = new SinhVienDialog(model.getSelectedItem());
			Optional<SinhVienModelItem> modelItem = dialog.showAndWait();
			modelItem.ifPresent((i) -> {
				suaHandler.accept(i);
			});
		});
	}

	private void configureXoaButton() {
		xoaButton.disableProperty().unbind();
		xoaButton.setDisable(true);
		xoaButton.setOnAction(evt -> {
			xoaHandler.accept(() -> {
				xoaButton.setDisable(false);
				// restore bindings
			});
		});
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		results.getChildren().add(createTableView());
		configureThemButton();
		configureXoaButton();
		configureSuaButton();
	}
}
