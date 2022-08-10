package com.jpmc.notificationservice.controller;

import com.jpmc.notificationservice.exception.SalesProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.jpmc.notificationservice.model.Sale;
import com.jpmc.notificationservice.service.ProcessingService;

@RestController
public class MessageController {

	final Logger LOG = LoggerFactory.getLogger(MessageController.class);
	
	@Autowired
	private ProcessingService processingService;

	/* Request can be validated using spring-boot-starter-validation dependency and using @Valid*/
	@PostMapping("/sale")
	public ResponseEntity<String> addSale(@RequestHeader(value = "X-b3-traceId", required = false)String traceId, @RequestBody Sale sale){
		MDC.put("traceId" , traceId);

		try {
			processingService.add(sale);
			return new ResponseEntity<>("Success", HttpStatus.OK);
		}
		catch(SalesProcessingException ex){
			LOG.error ("We are processing adjustments , please try after 1 min ");
			return new ResponseEntity<>("Error", HttpStatus.INSUFFICIENT_STORAGE);
		}
		catch (Exception ex){
			LOG.error ("Failed while processing the message ");
			return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
