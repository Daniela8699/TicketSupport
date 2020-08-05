package com.medved.support.rest.implementations;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.medved.support.logic.apiconnection.InternalTicketDTO;
import com.medved.support.logic.interfaces.IAnswerService;
import com.medved.support.logic.interfaces.IEnterpriseService;
import com.medved.support.logic.interfaces.IInternalTicketService;
import com.medved.support.logic.interfaces.IPriorityService;
import com.medved.support.logic.interfaces.IResourceService;
import com.medved.support.logic.interfaces.IUserService;
import com.medved.support.model.Answer;
import com.medved.support.model.InternalTicket;
import com.medved.support.model.Resource;
import com.medved.support.model.Ticket;
import com.medved.support.model.User;
import com.medved.support.rest.interfaces.IInternalTicketRestController;
import com.medved.support.rest.jwt.JwtRequestFilter;

@RequestMapping("/medvedapi/internalTickets")
@RestController
@CrossOrigin(origins="*")
public class InternalTicketRestController implements IInternalTicketRestController{

	private static final String TEMPORAL_USER = "dangaltor";

	@Autowired
	private IInternalTicketService service;

	@Autowired
	private IAnswerService answer;

	@Autowired
	private IEnterpriseService enterprises;

	@Autowired
	private IPriorityService priorities;

	@Autowired
	private IUserService users;
	
	@Autowired
	private IResourceService resource;
	
	@Autowired
	private JwtRequestFilter jwt;

	@GetMapping("/{id}")
	public InternalTicket findById(@PathVariable("id") Long ticketId) {

		try {
			return service.findById(ticketId);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	@GetMapping("/meta/{keyword}")
	public Iterable<InternalTicket> findByKeyWord(@PathVariable("keyword") String keyword) {

		try {
			return service.findByKeyWord(keyword);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@GetMapping("/")
	public Iterable<InternalTicket> findAll() {
		return service.findAll();
	}

	@PostMapping("")
	public void save(@RequestBody InternalTicketDTO serv, @RequestHeader("Authorization") String token) {
		InternalTicket newInternal = mapSave(serv, true,token);
		service.save(newInternal);

	}

	@PutMapping("")
	public void updateEntity(@RequestBody InternalTicketDTO ticket) {
		service.update(map(ticket, false));
	}
	
	

	@DeleteMapping("/{id}")
	public void remove(Long ticketId) {
		InternalTicket a = service.findById(ticketId);
		if (a != null)
			service.remove(a);
	}

	@DeleteMapping(value = "/state/{id}")
	public void removeState(Long ticketId) {
		InternalTicket a = service.findById(ticketId);
		if (a != null)
			service.removeState(a);
	}

	private InternalTicket map(InternalTicketDTO dto, boolean newTicket) {
		InternalTicket newInternal;
		Ticket ticket;
		Date actDate = new Date();
		if (newTicket) {
			newInternal = new InternalTicket();
			ticket = new Ticket();
			ticket.setOpenDate(actDate);
			ticket.setId(System.nanoTime());
		} else {
			newInternal = service.findById(dto.getId());
			ticket = newInternal.getTicket();
		}
		
		newInternal.setPrioridad(priorities.findById(dto.getPriority()));
		newInternal.setUserCreator(users.getUser(TEMPORAL_USER));
		newInternal.setUserAssigned(users.getUser(dto.getAgent()));
		
		ticket.setDescription(dto.getDescription());
		ticket.setInternalTicket(newInternal);
		ticket.setTitle(dto.getTitle());

		ticket.setLastActivityDate(actDate);

		newInternal.setTicket(ticket);

		return newInternal;
	}
	
	private InternalTicket mapSave(InternalTicketDTO dto, boolean newTicket, String token) {
		InternalTicket newInternal;
		Ticket ticket;
		Date actDate = new Date();
		if (newTicket) {
			newInternal = new InternalTicket();
			ticket = new Ticket();
			ticket.setOpenDate(actDate);
			ticket.setId(System.nanoTime());
		} else {
			newInternal = service.findById(dto.getId());
			ticket = newInternal.getTicket();
		}
		newInternal.setEnterprise(users.getUser(jwt.extractUsername(token)).getEnterprises());
		newInternal.setPrioridad(priorities.findById(dto.getPriority()));
		newInternal.setUserCreator(users.getUser(jwt.extractUsername(token)));
		newInternal.setUserAssigned(users.getUser(dto.getAgent()));
		
		ticket.setDescription(dto.getDescription());
		ticket.setInternalTicket(newInternal);
		ticket.setTitle(dto.getTitle());

		ticket.setLastActivityDate(actDate);

		newInternal.setTicket(ticket);

		return newInternal;
	}
	
	@GetMapping("resolved/{perPage}/{currentPage}/")
	@Override
	public Iterable<InternalTicket> findAllResolvedPageable(@PathVariable("currentPage") int currPage, @PathVariable("perPage") int pageSize,@RequestHeader("Authorization") String token){
		try {
			String nit = users.getUsersCompanyNIT(jwt.extractUsername(token));
			return service.findAllResolvedPageable(pageSize, currPage-1,nit);
		}catch(Exception e) {
			
		}
		return null;
	}
	
	@GetMapping("assigned/{perPage}/{currentPage}/")
	@Override
	public Iterable<InternalTicket> findAllAssignedPageable(@PathVariable("currentPage") int currPage, @PathVariable("perPage") int pageSize,@RequestHeader("Authorization") String token){
		try {
			return service.findAllAssignedPageable(pageSize, currPage-1,jwt.extractUsername(token));
		}catch(Exception e) {
			
		}
		return null;
	}
	
	@GetMapping("assigned/meta/{keyword}/{perPage}/{currentPage}/")
	@Override
	public Iterable<InternalTicket> findByKeyWordAssignedPageable(@PathVariable("keyword") String keyword,@PathVariable("currentPage") int currPage, @PathVariable("perPage") int pageSize, @RequestHeader("Authorization") String token) {
		try {
			return service.findByKeywordAssignedPageable(keyword, pageSize, currPage-1, jwt.extractUsername(token));
		}catch(Exception e) {
			
		}
		return null;
	}
	@GetMapping("resolved/meta/{keyword}/{perPage}/{currentPage}/")
	@Override
	public Iterable<InternalTicket> findByKeyWordResolvedPageable(@PathVariable("keyword") String keyword,@PathVariable("currentPage") int currPage, @PathVariable("perPage") int pageSize, @RequestHeader("Authorization") String token){
		try {
			String nit = users.getUsersCompanyNIT(jwt.extractUsername(token));
			return service.findByKeywordResolvedPageable(keyword, pageSize, currPage-1, nit);
		}catch(Exception e) {
			
		}
		return null;
	}

	@PostMapping("/answer")
	public long answerTicket(@RequestBody InternalTicketDTO ticket) {

		if (ticket != null && ticket.getAnswer()!=null) {
			InternalTicket internal = service.findById(ticket.getId());
			Answer newAnswer = new Answer();
			newAnswer.setBody(ticket.getAnswer());
			newAnswer.setCreationDateAnswer(new Date());
			newAnswer.setId(System.nanoTime());
			internal.getTicket().addAnswer(newAnswer);
			this.answer.save(newAnswer);
			this.service.updateAnswer(internal, 2);
			return newAnswer.getId();
			
		}
		return -1;
	}
	
	@PostMapping("/image/{id}")
	public void saveImage(@RequestParam(value="file")MultipartFile file,@PathVariable("id")Long idAnswer) throws Exception {
		if(file!=null) {
			Resource r = new Resource();
			r.setAttachedFile(file.getBytes());
			Answer a = answer.findById(idAnswer);
			r.setAnswer(a);
			r.setTicket(a.getTicket());
			r.setEntityState(a.getTicket().getInternalTicket().getEntityState());
			resource.save(r);
		}
	}

}
