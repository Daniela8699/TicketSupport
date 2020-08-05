package com.medved.support.logic.implementations;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medved.support.logic.interfaces.ISourceService;
import com.medved.support.model.Source;
import com.medved.support.repository.interfaces.ISourceDAO;

@Service
public class SourceService implements ISourceService {

	@Autowired
	private ISourceDAO sourceDao;

	@Override
	@Transactional
	public Source findById(long id) {
		return sourceDao.findById(id);
	}

	@Override
	@Transactional
	public Iterable<Source> findAll() {
		return sourceDao.findAll();
	}

	@Override
	@Transactional
	public void save(Source newSource) {
		sourceDao.save(newSource);

	}

	@Override
	@Transactional
	public void update(Source source) {
		sourceDao.update(source);

	}

	@Override
	@Transactional
	public void remove(Source source) {
		sourceDao.remove(source);

	}	
}
