package com.medved.support.repository.interfaces;

import java.util.List;

import com.medved.support.model.Answer;

public interface IAnswerDAO {

	public void save(Answer answer);
	public void update(Answer answer);
	public void remove(Answer answer);
	public Answer findById (long id);
	public List<Answer> findAll();
	public void removeState (Answer answer);
	
}
