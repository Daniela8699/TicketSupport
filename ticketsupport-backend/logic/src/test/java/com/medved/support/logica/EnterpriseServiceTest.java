package com.medved.support.logica;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.medved.support.logic.implementations.EnterpriseService;
import com.medved.support.model.Enterprise;
import com.medved.support.model.User;
import com.medved.support.repository.implementations.EnterpriseDAO;
import com.medved.support.repository.implementations.EntityStateDAO;

public class EnterpriseServiceTest {

	@InjectMocks
	private EnterpriseService enterpriseService;

	@Mock
	private EnterpriseDAO enterpriseDAO;

	@Mock
	private EntityStateDAO entityStateDao;

	@Before
	public void setup() {
		enterpriseService = new EnterpriseService();
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * @Name: testA
	 * @Method: findById
	 * @Desciption: testA verifies that the findById method of the EnterpriseService
	 *              class calls the findById method of the EnterpriseDAO class. Also
	 *              simulates one return of object Enterprise
	 */
	@Test
	public void testA() {
		Enterprise enterprise = new Enterprise();
		enterprise.setNit("4");
		when(enterpriseDAO.findById("4")).thenReturn(enterprise);
		Assert.assertEquals(enterpriseService.findById("4"), enterprise);
		verify(enterpriseDAO).findById("4");
	}

	/**
	 * @Name: testB
	 * @Method: findAll
	 * @Desciption: testB verifies that the findAll method of the Enterprise Service
	 *              class calls the find_All method of the EnterpriseDAO class
	 */
	@Test
	public void testB() {
		when(enterpriseDAO.findAll()).thenReturn(new ArrayList<Enterprise>());
		enterpriseService.findAll();
		verify(enterpriseDAO).findAll();
	}

	/**
	 * @Name: testC
	 * @Method: save
	 * @Desciption:
	 */

	public void testC() {
		Enterprise enterprise = new Enterprise();
		ArrayList<User> userList = new ArrayList<User>();
		User userA = new User();
		userList.add(userA);
		enterprise.setUsers(userList);
		enterpriseService.save(enterprise);
		verify(enterpriseDAO).save(enterprise);
	}

	public void testD() {

	}

	/**
	 * @Name: testE
	 * @Method: save
	 * @Desciption: testE verifies that the remove method of the Enterprise Service
	 *              class calls the remove method of the EnterpriseDAO class
	 */
	@Test
	public void testE() {
		Enterprise enterprise = new Enterprise();
		enterpriseService.remove(enterprise);
		verify(enterpriseDAO).remove(enterprise);
	}

	/**
	 * @Name: testF
	 * @Method: removeState
	 * @Desciption: testE verifies that the removeState method of the Enterprise
	 *              Service class calls the removeState method of the EnterpriseDAO
	 *              class
	 */
	@Test
	public void testF() {
		Enterprise enterprise = new Enterprise();
		enterpriseService.removeState(enterprise);
		verify(enterpriseDAO).removeState(enterprise);
	}
}
