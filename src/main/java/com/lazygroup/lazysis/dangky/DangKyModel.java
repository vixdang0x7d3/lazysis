package com.lazygroup.lazysis.dangky;

import org.springframework.stereotype.Component;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

@Component
public class DangKyModel {

	private ListProperty<DangKyModelItem> allItems = new SimpleListProperty<>(FXCollections.observableArrayList());
	private ObjectProperty<DangKyModelItem> selectedItem = new SimpleObjectProperty<>();

	public ObservableList<DangKyModelItem> getAllItems() {
		return this.allItems.get();
	}

	public void setAllItems(ObservableList<DangKyModelItem> items) {
		this.allItems.set(items);
	}

	public ListProperty<DangKyModelItem> allItemsProperty() {
		return allItems;
	}

	public DangKyModelItem getSelectedItem() {
		return this.selectedItem.get();
	}

	public void setSelectedItem(DangKyModelItem selectedItem) {
		this.selectedItem.set(selectedItem);
	}

	public ObjectProperty<DangKyModelItem> selectedItemProperty() {
		return selectedItem;
	}
}
