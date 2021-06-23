package alkemy.challenge.Challenge.Alkemy.controller;

import alkemy.challenge.Challenge.Alkemy.model.Book;
import alkemy.challenge.Challenge.Alkemy.service.BookService;
import alkemy.challenge.Challenge.Alkemy.util.Message;
import java.util.List;
import javax.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;
    
    //genera una lista de libros
    @GetMapping
    public ResponseEntity<?>getBooks(){
    	//generamos la lista
    	List<Book> listBooks = bookService.allBooks();
    	//comprobamos que en la lista haya datos
    	if (listBooks.isEmpty()) {
    		return new ResponseEntity<>(listBooks,HttpStatus.NO_CONTENT);
    	}else {
    		return new ResponseEntity<>(listBooks,HttpStatus.OK);
    	}
    }

    //busca un libro por el id
    @RequestMapping(value="{id}")
    public ResponseEntity<?>findBook(@PathVariable Long id){
    	Book auxbook = new Book();
    	auxbook = bookService.findBookById(id);
    	if (auxbook==null) {
    		return new ResponseEntity<>(auxbook,HttpStatus.NO_CONTENT);
    	}else {
    		return new ResponseEntity<>(auxbook,HttpStatus.OK);
    	}
    }

    //agrega un libro a la BBDD siempre y cuando tenga titulo
    @PostMapping
    public ResponseEntity<?> addBook(@RequestBody @Valid Book book){
    	
    	Message msg = new Message("");

        if (StringUtils.isBlank(book.getTitle())) {
        	msg.setMessage("campo titulo no puede estar vacio.");
            return new ResponseEntity<>(msg.getMessage(),HttpStatus.BAD_REQUEST);
        } else {
            Book newBook=bookService.createBook(book);
            msg.setMessage("Libro creado.");
            return new ResponseEntity<>(newBook,HttpStatus.CREATED);
        }
    }
    
    //elimina un libro de la BBDD
    @DeleteMapping
    public ResponseEntity<?> deleteBook(@RequestBody @Valid Book book){
    	Book auxbook = new Book();
    	auxbook = bookService.findBookById(book.getId());
    	if (auxbook==null) {
    		Message msg= new Message("El libro no existe");
    		return new ResponseEntity<>(msg.getMessage(),HttpStatus.BAD_REQUEST);
    	}else {
    		//delete book retorna un string que vamos a utilizar para enviar el mensaje del responseEntity
    		Message msg= new Message(bookService.deleteBookById(book.getId()));
    		return new ResponseEntity<>(msg.getMessage(),HttpStatus.OK);
    	}  
    }
    
    //actualiza un libro
    @PutMapping
    public ResponseEntity<?> updateBook(@RequestBody Book book){
    	Book auxbook = new Book();
    	auxbook = bookService.findBookById(book.getId());
    	if (auxbook==null) {
    		Message msg = new Message("No existe el libro: "+ book.getId());
    		return new ResponseEntity<>(msg.getMessage(),HttpStatus.BAD_REQUEST);
    	}else {
    		Message msg = new Message("Se actualizo el libro: "+ book.getId());
    		bookService.updateBookById(book);
    		return new ResponseEntity<>(msg.getMessage(),HttpStatus.OK);
    	}
    }

    @GetMapping("/colletctorPrice/book/{id}")
    public ResponseEntity<?> getCollectorPrice (@PathVariable long id){

        Book book = bookService.findBookById(id);
        if (book==null) {
        	return new ResponseEntity<>(book,HttpStatus.BAD_REQUEST);
        }else {
        	if(book.getEdition() < 3 || book.getEdition() > 5 ){
            	book.setPrice(book.getPrice()* 1.5);
            }
            return new ResponseEntity<>(book.getPrice(), HttpStatus.OK);
        }
        
    }


}
