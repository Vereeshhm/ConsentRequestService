package com.saswatconsent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saswatconsent.service.ConsentService;
import com.saswatconsent.utils.ConsentRequest;

@RestController
@RequestMapping("/saswat")
public class ConsentController {

	@Autowired
	ConsentService consentService;
	
	@PostMapping(value = "/consentrequest", produces = MediaType.APPLICATION_JSON_VALUE)
	public String getConsent(@RequestBody ConsentRequest consentRequest) {
		return consentService.fetchConsent(consentRequest);

	}

}
