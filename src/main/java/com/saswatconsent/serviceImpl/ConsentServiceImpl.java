package com.saswatconsent.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.saswatconsent.service.ConsentService;
import com.saswatconsent.utils.ConsentRequest;
import com.saswatconsent.utils.PropertiesConfig;

@Service
public class ConsentServiceImpl implements ConsentService{
	
	
	private final RestTemplate restTemplate;
	
	private final String tocken;
	
	
	@Autowired
	PropertiesConfig propertiesConfig;
	

	public ConsentServiceImpl(RestTemplate restTemplate, @Value("${tocken}") String tocken) {
		this.restTemplate = restTemplate;
		this.tocken = tocken;
	}

	@Override
	public String fetchConsent(ConsentRequest consentRequest) {
		consentRequest.getMobileNumber();
		consentRequest.getConsentArtifactName();
		consentRequest.getConsentDescription();
		consentRequest.getCallbackUrl();
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", tocken);
		headers.set("Content-Type", "application/json");
		HttpEntity<String> requestEntity = new HttpEntity(consentRequest, headers);
		String Response = restTemplate.postForObject(propertiesConfig.getUrl(), requestEntity, String.class);
		return Response;
	}

}
