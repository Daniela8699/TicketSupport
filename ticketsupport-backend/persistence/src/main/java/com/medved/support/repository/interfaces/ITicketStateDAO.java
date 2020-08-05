package com.medved.support.repository.interfaces;

import com.medved.support.model.TicketState;

public interface ITicketStateDAO {

	public TicketState findById(long id);
}
