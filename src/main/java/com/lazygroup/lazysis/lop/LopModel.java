package com.lazygroup.lazysis.lop;

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
public class LopModel {
	private final ListProperty<LopModelItem> allItems = new SimpleListProperty<>(
			FXCollections.observableArrayList());
	private final ObjectProperty<LopModelItem> selectedItem = new SimpleObjectProperty<>();

	private final StringProperty username = new SimpleStringProperty("");
	private final StringProperty password = new SimpleStringProperty("");
	private final StringProperty site = new SimpleStringProperty("");

	public ObservableList<LopModelItem> getAllItems() {
		return allItems;
	}

	public void setAllItems(ObservableList<LopModelItem> allItems) {
		this.allItems.set(allItems);
	}

	public ListProperty<LopModelItem> allItemsProperty() {
		return allItems;
	}

	public LopModelItem getSelectedItem() {
		return this.selectedItem.get();
	}

	public void setSelectedItem(LopModelItem selectedItem) {
		this.selectedItem.set(selectedItem);
	}

	public ObjectProperty<LopModelItem> selectedItemProperty() {
		return selectedItem;
	}

	public String getUsername() {
		return username.get();
	}

	public void setUsername(String username) {
		this.username.set(username);
	}

	public StringProperty usernameProperty() {
		return username;
	}

	public String getPassword() {
		return password.get();
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
