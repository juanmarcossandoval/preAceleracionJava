package alkemy.challenge.Challenge.Alkemy.controller;

import alkemy.challenge.Challenge.Alkemy.model.Category;
import alkemy.challenge.Challenge.Alkemy.service.CategoryService;
import alkemy.challenge.Challenge.Alkemy.util.Message;
import javax.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    
    //genera una lista de categorias 
    @GetMapping
    public ResponseEntity<?> getCategories(){
    	//generamos la lista
        List<Category> listCategories = categoryService.allCategories();
        //comprobamos que haya categorias en la base de datos
        if (listCategories.isEmpty()) {
        	return new ResponseEntity<>(listCategories,HttpStatus.NO_CONTENT);        	
        }else {
        	return new ResponseEntity<>(listCategories, HttpStatus.OK);
        }
        
    }
    
    //busca una categoria por id
    @RequestMapping(value="{id}")
    public ResponseEntity<?> findCategory(@PathVariable Long id ){
    	Category auxcategory = new Category();
    	auxcategory = categoryService.findCategoryById(id);
    	if (auxcategory==null) {
    		return new ResponseEntity<>(auxcategory,HttpStatus.NO_CONTENT);
    	}else {
    		return new ResponseEntity<>(auxcategory, HttpStatus.OK);
    	}    	
    }
    
    //agrega una categoria contemplando que el nombre cumpla ciertas caracteristicas
    @PostMapping
    public ResponseEntity<?> addCategory(@RequestBody @Valid Category category) {
    	
    	Message msg = new Message("");

        if (StringUtils.isBlank(category.getName())) {
        	msg.setMessage("campo nombre no puede estar vacio.");
            return new ResponseEntity<>(msg.getMessage(),HttpStatus.BAD_REQUEST);
        }
        if (!StringUtils.isAlpha(category.getName())) {
        	msg.setMessage("Debe contener solo letras.");
            return new ResponseEntity<>(msg.getMessage(),HttpStatus.BAD_REQUEST);
        } else {
            Category newCategory=categoryService.createCategory(category);
            msg.setMessage("categoria creada.");
            return new ResponseEntity<>(newCategory,HttpStatus.CREATED);
        }
    }
    
    
    
    //elimina una categoria de la base de datos
    @DeleteMapping
    public ResponseEntity<?> deleteCategory(@RequestBody @Valid Category category){
    	Category auxcategory = new Category();
    	auxcategory = categoryService.findCategoryById(category.getId());
    	if (auxcategory==null) {
    		Message msg= new Message("La Categoria no existe");
    		return new ResponseEntity<>(msg.getMessage(),HttpStatus.BAD_REQUEST);
    	}else {
    		//delete category retorna un string que vamos a utilizar para enviar el mensaje del responseEntity
    		Message msg= new Message(categoryService.deleteCategoryById(category.getId()));
    		return new ResponseEntity<>(msg.getMessage(),HttpStatus.OK);
    	}  	
    }
    
    //actualiza una categoria
    @PutMapping
    public ResponseEntity<?> updateCategory(@RequestBody Category category){
    	Category auxcategory = new Category();
    	auxcategory = categoryService.findCategoryById(category.getId());
    	if (auxcategory==null) {
    		Message msg = new Message("No existe la categoria: "+ category.getId());
    		return new ResponseEntity<>(msg.getMessage(),HttpStatus.BAD_REQUEST);
    	}else {
    		Message msg = new Message("Se actualizo la categoria: "+ category.getId());
    		categoryService.updateCategoryById(category);
    		return new ResponseEntity<>(msg.getMessage(),HttpStatus.OK);
    	}
    }

    
}
