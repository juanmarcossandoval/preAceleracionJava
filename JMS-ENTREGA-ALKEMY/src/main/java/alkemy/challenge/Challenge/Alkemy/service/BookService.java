package alkemy.challenge.Challenge.Alkemy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import alkemy.challenge.Challenge.Alkemy.model.Book;
import alkemy.challenge.Challenge.Alkemy.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	//inserta un libro
	public Book createBook(Book book) {
		return bookRepository.save(book);
	}
	
	//encuentra un libro por el id
	public Book findBookById(Long id) {
		return bookRepository.findById(id).orElse(null);
	}
	
	//elimina un libro por el id
	public String deleteBookById(Long id) {
		bookRepository.deleteById(id);
		return new String("Libro eliminado: "+id);
	}
	
	//actualiza un libro recibido por parametro
	public Book updateBookById(Book book) {
		return bookRepository.save(book);
	}
	
	//devuelve todos los libros de la entidad en  una lista
	public List <Book> allBooks(){
		return bookRepository.findAll();
	}
}
