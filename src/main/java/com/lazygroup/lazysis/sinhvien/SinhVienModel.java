package com.lazygroup.lazysis.sinhvien;

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
public class SinhVienModel {
	private ListProperty<SinhVienModelItem> allItems = new SimpleListProperty<>(
			FXCollections.observableArrayList());
	private ObjectProperty<SinhVienModelItem> selectedItem = new SimpleObjectProperty<>();

	private StringProperty username = new SimpleStringProperty("");
	private StringProperty password = new SimpleStringProperty("");
	private StringProperty site = new SimpleStringProperty("");

	public ObservableList<SinhVienModelItem> getAllItems() {
		return this.allItems.get();
	}

	public void setAllItems(ObservableList<SinhVienModelItem> items) {
		this.allItems.set(items);
	}

	public ListProperty<SinhVienModelItem> allItemsProperty() {
		return allItems;
	}

	public void setSelectedItem(SinhVienModelItem selectedItem) {
		this.selectedItem.set(selectedItem);
	}

	public SinhVienModelItem getSelectedItem() {
		return this.selectedItem.get();
	}

	public ObjectProperty<SinhVienModelItem> selectedItemProperty() {
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
