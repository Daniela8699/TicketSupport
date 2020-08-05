package com.medved.support.rest.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medved.support.logic.interfaces.IPriorityService;
import com.medved.support.model.Priority;
import com.medved.support.rest.interfaces.IPriorityRestController;

@RequestMapping("/medvedapi/priorities")
@RestController
@CrossOrigin(origins="*")
public class PriorityRestController implements IPriorityRestController {

	@Autowired
	IPriorityService priorityService;

	@GetMapping("")
	@Override
	public Iterable<Priority> findAll() {
		// TODO Auto-generated method stub
		return priorityService.findAll();
	}
}
