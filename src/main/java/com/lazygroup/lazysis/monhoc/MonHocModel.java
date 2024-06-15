package com.lazygroup.lazysis.monhoc;

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
public class MonHocModel {
	private ListProperty<MonHocModelItem> allItems = new SimpleListProperty<>(FXCollections.observableArrayList());
	private ObjectProperty<MonHocModelItem> selectedItem = new SimpleObjectProperty<>();

	private StringProperty username = new SimpleStringProperty("");
	private StringProperty password = new SimpleStringProperty("");
	private StringProperty site = new SimpleStringProperty("");

	public ObservableList<MonHocModelItem> getAllItems() {
		return this.allItems.get();
	}

	public void setAllItems(ObservableList<MonHocModelItem> items) {
		this.allItems.set(items);
	}

	public ListProperty<MonHocModelItem> allItemsProperty() {
		return allItems;
	}

	public MonHocModelItem getSelectedItem() {
		return this.selectedItem.get();
	}

	public void setSelectedItem(MonHocModelItem selectedItem) {
		this.selectedItem.set(selectedItem);
	}

	public ObjectProperty<MonHocModelItem> selectedItemProperty() {
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
