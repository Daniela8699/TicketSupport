package com.medved.support.repository.interfaces;

import java.util.List;

import com.medved.support.model.SearchMade;

public interface ISearchMadeDAO {

	public void save(SearchMade searchMade);
	public void update(SearchMade searchMade);
	public void remove(SearchMade searchMade);
	public SearchMade findById (long id);
	public List<SearchMade> findAll();
}
