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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.stecnology.data.dto.BookDTO;
import br.com.stecnology.services.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = {"BookEndpoint"})
@RestController
@RequestMapping("api/book/v1")
public class BookController {
	
	@Autowired
	private BookService service;
	
	@Autowired
	PagedResourcesAssembler<BookDTO> assembler;
	
	@ApiOperation(value = "Find all book recorded")
	@GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
	public ResponseEntity<?> findAll(
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "10") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction){
		
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "author"));
		
		Page<BookDTO> books = service.findAll(pageable);
		books.stream()
			.forEach(b -> b.add(linkTo(methodOn(BookController.class).findById(b.getKey())).withSelfRel()));
		
		PagedResources<?> resources= assembler.toResource(books);
		
		return new ResponseEntity<>(resources, HttpStatus.OK);
		
	}
	
	@ApiOperation(value = "Find book by id recorded")
	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public BookDTO findById(@PathVariable("id") Long id) {
		var bookDTO = service.findById(id);
		bookDTO.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
		return bookDTO;

		
	}

	@ApiOperation(value = "Create book recorded")
	@PostMapping(produces = { "application/json", "application/xml" }, consumes = { "application/json",
			"application/xml", "application/x-yaml" })
	public BookDTO create(@RequestBody BookDTO book) throws Exception {
		var bookDTO = service.create(book);
		bookDTO.add(linkTo(methodOn(BookController.class).findById(bookDTO.getKey())).withSelfRel());
		return bookDTO;

		
	}

	@ApiOperation(value = "Update book recorded")
	@PutMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public BookDTO update(@RequestBody BookDTO book) {
		var bookDTO = service.update(book);
		book.add(linkTo(methodOn(BookController.class).findById(bookDTO.getKey())).withSelfRel());
		return bookDTO;

		
	}

	@ApiOperation(value = "Delet book by id recorded")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") long id) {

		service.delete(id);
		return ResponseEntity.ok().build();
	}


}
