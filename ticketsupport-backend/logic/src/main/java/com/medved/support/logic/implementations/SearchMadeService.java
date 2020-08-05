package com.medved.support.logic.implementations;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medved.support.logic.interfaces.ISearchMadeService;
import com.medved.support.model.SearchMade;
import com.medved.support.repository.interfaces.ISearchMadeDAO;

@Service
public class SearchMadeService implements ISearchMadeService {

	@Autowired
	private ISearchMadeDAO searchMadeDao;

	@Override
	@Transactional
	public Iterable<SearchMade> findAll() {
		return searchMadeDao.findAll();
	}

	@Override
	@Transactional
	public void save(SearchMade searchMade) {
		searchMadeDao.save(searchMade);
		
	}

	@Override
	@Transactional
	public void update(SearchMade searchMade) {
		searchMadeDao.update(searchMade);
		
	}

	@Override
	@Transactional
	public void remove(SearchMade searchMade) {
		searchMadeDao.remove(searchMade);
		
	}

	@Override
	@Transactional
	public SearchMade findById(long id) {
		return searchMadeDao.findById(id);
	}

}
