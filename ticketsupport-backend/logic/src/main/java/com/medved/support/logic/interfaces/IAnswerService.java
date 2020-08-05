package com.medved.support.logic.interfaces;

import com.medved.support.model.Answer;

public interface IAnswerService {

	public Answer findById(long id);
	public Iterable<Answer> findAll();
	public void save(Answer internalTicket);
	public void update(Answer internalTicket);
	public void remove(Answer internalTicket);
	public void removeState (Answer internalTicket);
}
