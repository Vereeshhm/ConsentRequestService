package com.saswatconsent.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.saswatconsent.service.ConsentService;
import com.saswatconsent.utils.ConsentRequest;
import com.saswatconsent.utils.PropertiesConfig;

@Service
public class ConsentServiceImpl implements ConsentService{
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	PropertiesConfig propertiesConfig;

	@Override
	public String fetchConsent(ConsentRequest consentRequest) {
		consentRequest.getMobileNumber();
		consentRequest.getConsentArtifactName();
		consentRequest.getConsentDescription();
		consentRequest.getCallbackUrl();
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", propertiesConfig.getTocken());
		headers.set("Content-Type", "application/json");
		HttpEntity<String> requestEntity = new HttpEntity(consentRequest, headers);
		String Response = restTemplate.postForObject(propertiesConfig.getUrl(), requestEntity, String.class);
		return Response;
	}

}
