package com.medved.support.logic.implementations;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medved.support.logic.interfaces.IAnswerService;
import com.medved.support.model.Answer;
import com.medved.support.repository.interfaces.IAnswerDAO;

@Service
public class AnswerService implements IAnswerService {

	@Autowired
	private IAnswerDAO answerDao;

	@Override
	@Transactional
	public Answer findById(long id) {
		return answerDao.findById(id);
	}

	@Override
	@Transactional
	public Iterable<Answer> findAll() {
		return answerDao.findAll();
	}

	@Override
	@Transactional
	public void save(Answer answer) {
		if (answer == null)
			throw new RuntimeException("La entidad no puede ser nula");
		if (answer.getCreationDate().compareTo(answer.getTicket().getOpenDate()) < 0)
			throw new RuntimeException("La fecha de creación de la respuesta no puede ser anterior a la fecha de creación del post");
		
		answerDao.save(answer);

	}

	@Override
	@Transactional
	public void update(Answer answer) {
		if (answer == null)
			throw new RuntimeException("La entidad no puede ser nula");
		if (answer.getCreationDate().compareTo(answer.getTicket().getOpenDate()) < 0)
			throw new RuntimeException("La fecha de creación de la respuesta no puede ser anterior a la fecha de creación del post");
		answerDao.update(answer);

	}

	@Override
	@Transactional
	public void remove(Answer answer) {
		answerDao.remove(answer);

	}

	@Override
	public void removeState(Answer answer) {
		answerDao.removeState(answer);
	}

}
