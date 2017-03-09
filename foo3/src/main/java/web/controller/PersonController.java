package web.controller;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import web.entity.Person;
import web.service.IPersonService;

@RestController
public class PersonController {
	Logger logger = LoggerFactory.getLogger(PersonController.class);

	@Autowired @Lazy
	private IPersonService<Person, Serializable> personService;

	@RequestMapping(value = "/addPerson", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> addPerson(@RequestBody Person person, UriComponentsBuilder ucBuilder) {
		logger.info(" addCity  name :" + person.getPname());
		personService.save(person);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/person/{id}").buildAndExpand(person.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/person/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Person> getPerson(@PathVariable("id") String id) {
		Person person = personService.get(id);
		if (person == null) {
			return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
		}
		logger.info("person/{id} " + id);
		return new ResponseEntity<Person>(person, HttpStatus.OK);
	}

}
