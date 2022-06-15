package com.example.demo.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class TemplateController {

	@Autowired
	private RestTemplate restTemplate;

//	HttpEntity<User> request = new HttpEntity<User>(new User("James", "Bond", "jamesbond007@gmail.com"));

	@GetMapping("/new")
	public ResponseEntity<String> insertUser(HttpEntity<User> request) {
		request = new HttpEntity<User>(new User("James", "Bond", "jamesbond007@gmail.com"));
		return restTemplate.postForEntity("http://localhost:8081/api/customers", request, String.class);
	}

	// Creating for exchange method
	@GetMapping("/newUser")
	public ResponseEntity<String> newUser(HttpEntity<User> request) {
		request = new HttpEntity<User>(new User("Ravi", "Patel", "ravipatel233@gmail.com"));
		return restTemplate.exchange("http://localhost:8081/api/customers", HttpMethod.POST, request, String.class);
	}

	@GetMapping("/edit")
	public ResponseEntity<String> editUser(HttpEntity<User> request) {
		request = new HttpEntity<User>(new User());
		return restTemplate.exchange("http://localhost:8081/api/customers/8", HttpMethod.DELETE, request, String.class);
	}

	@GetMapping("/download")
	public void downloadFiles() {
		final HttpEntity<User> requests = new HttpEntity<User>(new User("Ravi", "Patel", "ravipatel233@gmail.com"));
		// Set HTTP headers in the request callback
		RequestCallback requestCallback = request -> {
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(request.getBody(), requests);

			request.getHeaders().setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM, MediaType.ALL));
		};

		// Processing the response. Here we are extracting the
		// response and copying the file to a folder in the server.
		ResponseExtractor<Void> responseExtractor = response -> {
			Path path = Paths.get("/home/ravi/path/list");
			Files.copy(response.getBody(), path);
			return null;
		};

		restTemplate.execute("http://localhost:8081/api/customers", HttpMethod.GET, requestCallback, responseExtractor);
	}

	@GetMapping("/customerList")
	public List<User> getCustomerList() {
		return restTemplate.getForObject("http://localhost:8081/api/customers", List.class);
	}
}
