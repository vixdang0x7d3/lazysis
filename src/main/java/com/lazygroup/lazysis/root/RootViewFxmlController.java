package com.lazygroup.lazysis.root;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.lazygroup.lazysis.home.HomeController;
import com.lazygroup.lazysis.home.HomeViewFxmlController;
import com.lazygroup.lazysis.sinhvien.SinhVienController;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;

@FxmlView("rootView.fxml")
@Component
public class RootViewFxmlController implements Initializable {

	@FXML
	private BorderPane results;
	@FXML
	private Label userNameLabel;
	@FXML
	private Button logoutButton;
	@FXML
	private ComboBox<String> siteRoutingComboBox;

	// encapsulated views
	@FXML
	Tab homeTab;
	@FXML
	Tab sinhvienTab;

	private final RootModel model;

	private final Map<String, String> idLookUp;

	private final Runnable logoutHandler;

	private final Region homeTabContent;
	private final Region sinhvienTabContent;

	@Autowired
	RootViewFxmlController(RootModel model, RootInteractor interactor,
			@Qualifier("siteIdLookUpMapGV") Map<String, String> idLookUp,
			HomeController homeController,
			SinhVienController sinhvienController) {
		this.model = model;
		this.idLookUp = idLookUp;
		this.logoutHandler = interactor::logout;
		this.homeTabContent = homeController.getView();
		this.sinhvienTabContent = sinhvienController.getView();
	}

	private void configureUserNameLabel() {
		userNameLabel.textProperty().bind(model.inAppNameProperty());
	}

	private void configureLogoutButton() {
		logoutButton.setOnAction(evt -> {
			logoutHandler.run();
		});
	}

	private void configureSiteRoutingComboBox() {

		siteRoutingComboBox.getItems().addAll(idLookUp.keySet());

		String firstItem = siteRoutingComboBox.getItems().get(0);

		if (idLookUp.get(firstItem) == model.getSite()) {
			siteRoutingComboBox.getSelectionModel().select(0);
		} else {
			siteRoutingComboBox.getSelectionModel().select(1);
		}

		model.siteProperty().bind(Bindings.createStringBinding(() -> {

			if (siteRoutingComboBox.valueProperty().get() == null)
				return "";

			return idLookUp.get(siteRoutingComboBox.valueProperty().get());

		}, siteRoutingComboBox.valueProperty()));
	}

	private void configureHomeTab() {
		homeTab.setContent(homeTabContent);
	}

	private void configureSinhVienTab() {
		sinhvienTab.setContent(sinhvienTabContent);
	}

	public Region getResults() {
		return results;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		configureUserNameLabel();
		configureLogoutButton();
		configureSiteRoutingComboBox();
		configureHomeTab();
		configureSinhVienTab();
	}

}
