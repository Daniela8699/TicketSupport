package com.medved.support.rest.implementations;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medved.support.logic.interfaces.IAnswerService;
import com.medved.support.logic.interfaces.IStatisticsService;
import com.medved.support.model.Answer;
import com.medved.support.rest.interfaces.IAnswerRestController;
import com.medved.support.rest.interfaces.IStatisticsRestController;

@RequestMapping("/medvedapi/statistics")
@RestController
@CrossOrigin(origins="*")
public class StatisticsRestController implements IStatisticsRestController{

	@Autowired
	private IStatisticsService service;

	@Override
	@GetMapping("/enterprises/number")
	public long numberOfEnterprises() {

		try {
			return service.numberOfEnterprises();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return -1;
	}

	@Override
	@GetMapping("/externalTickets/number")
	public long numberOfExternalTickets() {

		try {
			return service.numberOfExternalTickets();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return -1;
	}
	
	@Override
	@GetMapping("/internalTickets/number")
	public long numberOfInternalTickets() {

		try {
			return service.numberOfInternalTickets();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return -1;
	}

	@Override
	@GetMapping("/syncRegisters/number")
	public long numberOfSyncRegisters() {
		try {
			return service.numberOfSyncRegisters();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return -1;
	}

	@Override
	@GetMapping("/internalTickets/enterprises")
	public List numberOfInternalTicketsPerEnterprise() {
		try {
			return service.numberOfInternalTicketsPerEnterprise();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	@GetMapping("/internalTickets/created/{start}/{end}")
	public int[] numberOfCreatedInternalTicketsPerMonth(
			@PathVariable("start") 	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'") Date start,
			@PathVariable("end") 	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'") Date end) {
		try {
			return service.numberOfCreatedInternalTicketsPerMonth(start, end);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	@GetMapping("/internalTickets/closed/{start}/{end}")
	public int[] numberOfClosedInternalTicketsPerMonth(
			@PathVariable("start") 	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'") Date start,
			@PathVariable("end") 	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'") Date end) {
		try {
			return service.numberOfClosedInternalTicketsPerMonth(start, end);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
