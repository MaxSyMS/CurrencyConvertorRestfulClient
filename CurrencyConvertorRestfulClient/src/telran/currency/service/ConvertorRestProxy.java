package telran.currency.service;

import java.util.Set;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import telran.currency.dto.RequestDTO;
import telran.currency.interfaces.ICurrencyConverter;

public class ConvertorRestProxy implements ICurrencyConverter{
	
	RestTemplate restTemplate = new RestTemplate();
	String url = "https://currencyconvertorrestfulservic.herokuapp.com/";
	
	@Override
	public String lastDateTimePresentation() {
		ResponseEntity<String> response = restTemplate.exchange(url+"/currencies/datetime", HttpMethod.GET,null,String.class);
		return response.getBody();
	}

	@Override
	public Set<String> getCodes() {
		ResponseEntity<Set<String>> response = restTemplate.exchange(url+"/currencies", HttpMethod.GET,null,
				new ParameterizedTypeReference<Set<String>>() {	});
		return response.getBody();
	}

	@Override
	public double convert(String from, String to, double amount) {
		HttpEntity<RequestDTO> requestEntity = 
				new HttpEntity<>(RequestDTO.builder().currencyFrom(from).currencyTo(to).amount(amount).build());
		;
		ResponseEntity<Double> response = restTemplate.exchange(url+"/currencies/convert",HttpMethod.POST,requestEntity,Double.class);
		return response.getBody();
	}

}
