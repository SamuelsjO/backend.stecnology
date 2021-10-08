package br.com.stecnology.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.stecnology.data.dto.PersonDTO;
import br.com.stecnology.services.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = { "PersonEndpoint" })
@RestController
@RequestMapping("api/person/v1")
public class PersonController {

	@Autowired
	private PersonService service;
	
	@Autowired
	PagedResourcesAssembler<PersonDTO> assembler;

	@ApiOperation(value = "Find all people recorded")
	@GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
	public ResponseEntity<?> findAll(
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "10") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction
			){

		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		
		
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "firstName"));
		Page<PersonDTO> persons = service.findAll(pageable);
		persons.stream()
				.forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
		
		PagedResources<?> resources = assembler.toResource(persons);
		
		return new ResponseEntity<>(resources, HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "Find people by name")
	@GetMapping(value = "/people/{firstName}",
	produces = { "application/json", "application/xml", "application/x-yaml" })
	public ResponseEntity<?> findPersonByName(
			@PathVariable("firstName") String firstName, 
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "10") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction){

		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		
		
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "firstName"));
		Page<PersonDTO> persons = service.findPersonByName(firstName,pageable);
		persons.stream()
				.forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
		
		PagedResources<?> resources = assembler.toResource(persons);
		
		return new ResponseEntity<>(resources, HttpStatus.OK);
	}

	@ApiOperation(value = "Find people by id recorded")
	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public PersonDTO findById(@PathVariable("id") Long id) {

		var personDTO = service.findById(id);
		personDTO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());

		return personDTO;
	}

	@ApiOperation(value = "Disable people by id recorded")
	@PatchMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public PersonDTO disablePerson(@PathVariable("id") Long id) {

		var personDTO = service.disablePerson(id);
		personDTO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());

		return personDTO;
	}

	@ApiOperation(value = "Create people recorded")
	@PostMapping(produces = { "application/json", "application/xml" }, consumes = { "application/json",
			"application/xml", "application/x-yaml" })
	public PersonDTO create(@RequestBody PersonDTO person) throws Exception {

		PersonDTO personDTO = service.create(person);
		personDTO.add(linkTo(methodOn(PersonController.class).findById(personDTO.getKey())).withSelfRel());
		return personDTO;
	}

	@ApiOperation(value = "Update people recorded")
	@PutMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public PersonDTO update(@RequestBody PersonDTO person) {

		var personDTO = service.update(person);
		personDTO.add(linkTo(methodOn(PersonController.class).findById(personDTO.getKey())).withSelfRel());

		return personDTO;
	}

	@ApiOperation(value = "Delete people by id recorded")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") long id) {

		service.delete(id);
		return ResponseEntity.ok().build();
	}

}
