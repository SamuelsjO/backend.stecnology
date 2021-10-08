package br.com.stecnology.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.stecnology.adapter.DozerConverter;
import br.com.stecnology.data.dto.BookDTO;
import br.com.stecnology.data.model.Book;
import br.com.stecnology.exception.ResourceNotFoundExecption;
import br.com.stecnology.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	BookRepository repository;
	
	public BookDTO create(BookDTO book) {
		var entity = DozerConverter.parseObject(book, Book.class);
		var entityDTO = DozerConverter.parseObject(repository.save(entity), BookDTO.class);
		return entityDTO;
	}
	
	public Page<BookDTO> findAll(Pageable pageable){
		var page = repository.findAll(pageable);
		return page.map(this::convertToBookDTO);
	}
	
	private BookDTO convertToBookDTO(Book entity) {
		return DozerConverter.parseObject(entity, BookDTO.class);
	}
	
	public BookDTO findById(Long id) {

		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundExecption("No record found foi this ID"));

		return DozerConverter.parseObject(entity, BookDTO.class);
	}
	
	public BookDTO update(BookDTO book) {

		var entity = repository.findById(book.getKey())
				.orElseThrow(() -> new ResourceNotFoundExecption("No record found foi this ID"));
		entity.setAuthor(book.getAuthor());
		entity.setLaunch_date(book.getLaunch_date());
		entity.setPrice(book.getPrice());
		entity.setTitle(book.getTitle());

		return DozerConverter.parseObject(repository.save(entity), BookDTO.class);
	}
	
	public void delete(Long id) {
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundExecption("No record found foi this ID"));

		repository.delete(entity);
	}
}
