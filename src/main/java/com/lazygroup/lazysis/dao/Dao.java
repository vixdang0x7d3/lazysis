package com.lazygroup.lazysis.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T, U> {
	public List<T> list();

	public void create(T t);

	public Optional<T> get(U id);

	public void update(T t, U id);

	public void delete(U id);
}
