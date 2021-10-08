package br.com.stecnology.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.stecnology.adapter.DozerConverter;
import br.com.stecnology.data.dto.PersonDTO;
import br.com.stecnology.data.model.Person;
import br.com.stecnology.exception.ResourceNotFoundExecption;
import br.com.stecnology.repository.PersonRepository;


@Service
public class PersonService {

	@Autowired
	PersonRepository repository;

	public PersonDTO create(PersonDTO person) {
		var entity = DozerConverter.parseObject(person, Person.class);
		var entityDTO = DozerConverter.parseObject(repository.save(entity), PersonDTO.class);
		return entityDTO;
	}

	public Page<PersonDTO> findAll(Pageable pageable) {
		var page = repository.findAll(pageable);
		return page.map(this::convertToPersonVO);
	}
	
	public Page<PersonDTO> findPersonByName(String firstName, Pageable pageable) {
		var page = repository.findPersonByName(firstName,pageable);
		return page.map(this::convertToPersonVO);
	}
	
	private PersonDTO convertToPersonVO(Person entity) {
		return DozerConverter.parseObject(entity, PersonDTO.class);
	}

	public PersonDTO findById(Long id) {

		Person entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundExecption("No record found foi this ID"));

		return DozerConverter.parseObject(entity, PersonDTO.class);
	}

	public PersonDTO update(PersonDTO person) {

		var entity = repository.findById(person.getKey())
				.orElseThrow(() -> new ResourceNotFoundExecption("No record found foi this ID"));
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setGender(person.getGender());

		return DozerConverter.parseObject(repository.save(entity), PersonDTO.class);
	}
	
	@Transactional
	public PersonDTO disablePerson(Long id) {
		repository.disablePerson(id);
		Person entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundExecption("No record found foi this ID"));

		return DozerConverter.parseObject(entity, PersonDTO.class);
	}
	

	public void delete(Long id) {
		Person entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundExecption("No record found foi this ID"));

		repository.delete(entity);
	}

}
