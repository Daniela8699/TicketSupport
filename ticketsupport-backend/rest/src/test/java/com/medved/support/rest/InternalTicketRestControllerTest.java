//package com.medved.support.rest;
//
//import org.junit.Before;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import com.medved.support.logic.interfaces.IEnterpriseService;
//import com.medved.support.logic.interfaces.IInternalTicketService;
//import com.medved.support.logic.interfaces.IPriorityService;
//import com.medved.support.logic.interfaces.IUserService;
//import com.medved.support.rest.implementations.InternalTicketRestController;
//
//public class InternalTicketRestControllerTest {
//
//	@InjectMocks
//	private InternalTicketRestController internalTicketRestController; 
//	
//	@Mock
//	private IInternalTicketService service;
//	
//	@Mock
//	private IEnterpriseService enterprises;
//	
//	@Mock
//	private IPriorityService priorities;
//	
//	@Mock
//	private IUserService users;
//	
//	@Before
//	public void setup() {
//		internalTicketRestController = new InternalTicketRestController();
//		MockitoAnnotations.initMocks(this);
//	}
//	
//	
//}
