package com.medved.support.logic.interfaces;

import com.medved.support.model.SearchMade;

public interface ISearchMadeService {

	public Iterable<SearchMade> findAll();
	public void save(SearchMade searchMade);
	public void update(SearchMade searchMade);
	public void remove(SearchMade searchMade);
	public SearchMade findById(long id);
}
