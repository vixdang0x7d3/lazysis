package com.lazygroup.lazysis.monhoc;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.lazygroup.lazysis.dao.Dao;

@Component
public class DaoMonHocImpl implements Dao<MonHoc, String> {

	@Override
	public List<MonHoc> list() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'list'");
	}

	@Override
	public void create(MonHoc t) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'create'");
	}

	@Override
	public Optional<MonHoc> get(String id) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'get'");
	}

	@Override
	public void update(MonHoc t, String id) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'update'");
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'delete'");
	}

}
