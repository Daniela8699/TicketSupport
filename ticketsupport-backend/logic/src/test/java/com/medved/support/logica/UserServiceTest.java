//package com.medved.support.logica;
//
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import com.medved.support.logic.implementations.UserService;
//import com.medved.support.model.User;
//import com.medved.support.repository.interfaces.IUserDAO;
//
//public class UserServiceTest {
//	
//	@InjectMocks
//	private UserService userService;
//	
//	@Mock
//	private IUserDAO user;
//	
//	@Before
//	public void setup() {
//		userService = new UserService();
//		MockitoAnnotations.initMocks(this);
//	}
//	
//	@Test
//	public void  testA() {
//		User userAny = new User();
//		String name = "Cualquiera";
//		when(user.getUser(name)).thenReturn(userAny);
//		Assert.assertEquals(userService.getUser(name), userAny);
//		verify(user).getUser(name);
//	}
//}
