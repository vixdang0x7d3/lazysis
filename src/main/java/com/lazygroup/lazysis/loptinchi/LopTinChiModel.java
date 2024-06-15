package com.lazygroup.lazysis.loptinchi;

import org.springframework.stereotype.Component;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

@Component
public class LopTinChiModel {

	private ListProperty<LopTinChiModelItem> allItems = new SimpleListProperty<>(
			FXCollections.observableArrayList());
	private ObjectProperty<LopTinChiModelItem> selectedItem = new SimpleObjectProperty<>();

	public ObservableList<LopTinChiModelItem> getAllItems() {
		return this.allItems.get();
	}

	private StringProperty username = new SimpleStringProperty("");
	private StringProperty password = new SimpleStringProperty("");
	private StringProperty site = new SimpleStringProperty("");

	public void setAllItems(ObservableList<LopTinChiModelItem> items) {
		this.allItems.set(items);
	}

	public ListProperty<LopTinChiModelItem> allItemsProperty() {
		return allItems;
	}

	public LopTinChiModelItem getSelectedItem() {
		return this.selectedItem.get();
	}

	public void setSelectedItem(LopTinChiModelItem selectedItem) {
		this.selectedItem.set(selectedItem);
	}

	public ObjectProperty<LopTinChiModelItem> selectedItemProperty() {
		return selectedItem;
	}

	public String getUsername() {
		return this.username.get();
	}

	public void setUsername(String username) {
		this.username.set(username);
	}

	public StringProperty usernameProperty() {
		return username;
	}

	public String getPassword() {
		return this.password.get();
	}

	public void setPassword(String password) {
		this.password.set(password);
	}

	public StringProperty passwordProperty() {
		return password;
	}

	public String getSite() {
		return this.site.get();
	}

	public void setSite(String site) {
		this.site.set(site);
	}

	public StringProperty siteProperty() {
		return site;
	}
}
