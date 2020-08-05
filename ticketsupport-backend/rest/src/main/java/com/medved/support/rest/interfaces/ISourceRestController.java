package com.medved.support.rest.interfaces;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.medved.support.model.Source;

public interface ISourceRestController {

	public Source findById(@PathVariable("id") Long sourceId);

	public Iterable<Source> findAll();

	public void save(@RequestBody Source source);
	
	public void updateEntity(@RequestBody Source source);

	public void remove(Long sourceId);
}
