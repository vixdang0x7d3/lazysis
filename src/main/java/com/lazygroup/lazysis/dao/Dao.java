package com.lazygroup.lazysis.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
	public List<T> list();

	public void create(T t);

	public Optional<T> get(String id);

	public void update(T t, String id);

	public void delete(String id);
}
