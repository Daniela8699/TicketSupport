package com.medved.support.logic.implementations;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medved.support.logic.interfaces.IEnterpriseService;
import com.medved.support.model.Enterprise;
import com.medved.support.model.EntityState;
import com.medved.support.model.Source;
import com.medved.support.model.User;
import com.medved.support.repository.implementations.EntityStateDAO;
import com.medved.support.repository.implementations.SourceDAO;
import com.medved.support.repository.implementations.UserDAO;
import com.medved.support.repository.interfaces.IEnterpriseDAO;

@Service
public class EnterpriseService implements IEnterpriseService{

	@Autowired
	IEnterpriseDAO enterpriseDao;
	
	@Autowired
	EntityStateDAO entityStateDao;

	@Autowired
	UserDAO userDao;
	
	@Autowired
	SourceDAO sourceDao;
	
	@Override
	@Transactional
	public Enterprise findById(String nit) {
		// TODO Auto-generated method stub
		return enterpriseDao.findById(nit);
	}

	@Override
	@Transactional
	public Iterable<Enterprise> findAll() {
		// TODO Auto-generated method stub
		return enterpriseDao.findAll();
	}

	@Override
	@Transactional
	public void save(Enterprise newEnterprise) {
		
		if (newEnterprise.getUsers() != null) {
			for (User user : newEnterprise.getUsers()) {
				user.setEnterprise(newEnterprise);
			}			
		}
	
		if (newEnterprise.getUsers() != null) {
			for (Source src : newEnterprise.getSources()) {
				if (src.getEnterprises() != null) {
					src.getEnterprises().add(newEnterprise);				
				} else {
					ArrayList<Enterprise> toPass = new ArrayList<>();
					toPass.add(newEnterprise);
					src.setEnterprises(toPass);	
				}
			}			
		}
		EntityState entityState = entityStateDao.findById(1);
		newEnterprise.setEntityState(entityState);
		
		enterpriseDao.save(newEnterprise);
		
	}
	
	

	@Override
	@Transactional
	public void update(Enterprise updateEnterprise) {
	    ArrayList<Source> sources  = (ArrayList<Source>) sourceDao.findAll();
		for(Source s : sources) {
			if(s.getEnterprises() != null) {
				if(updateEnterprise.getSources().contains(s)) {
					if (!s.getEnterprises().contains(updateEnterprise)) {
						s.getEnterprises().add(updateEnterprise);
					}
				}
				else {
					if (s.getEnterprises().contains(updateEnterprise)) {
						s.getEnterprises().remove(updateEnterprise);
					}
				}
			}
			else {
					ArrayList<Enterprise> toPass = new ArrayList<>();
					toPass.add(updateEnterprise);
					s.setEnterprises(toPass);	
				}
				sourceDao.update(s);
				
	
		}
		
		for (User user : updateEnterprise.getUsers()) {
			user.setEnterprise(updateEnterprise);
			userDao.update(user);
		}
		enterpriseDao.update(updateEnterprise);
	}

	@Override
	@Transactional
	public void remove(Enterprise enterpriseRemove) {
		// TODO Auto-generated method stub
		if(enterpriseRemove == null) 
			throw new RuntimeException("la instancia para eliminar una empresa no debe ser nula");
		EntityState es = entityStateDao.findById(0);
		enterpriseRemove.setEntityState(es);
		enterpriseDao.remove(enterpriseRemove);
	}

	@Override
	public void removeState(Enterprise enterprise) {
		// TODO Auto-generated method stub
		if(enterprise == null) {
			throw new RuntimeException("la instancia para eliminar una empresa no debe ser nula");
		}
		enterpriseDao.removeState(enterprise);
	}
}
