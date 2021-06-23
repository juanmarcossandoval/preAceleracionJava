package alkemy.challenge.Challenge.Alkemy.service;

import alkemy.challenge.Challenge.Alkemy.model.Category;
import alkemy.challenge.Challenge.Alkemy.repository.CategoryRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    //inserta una nueva categoria
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }
        
    //encuentra una categoria por el id
    public Category findCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }
        
    //elimina una categoria recibiendo el id por parametro
    public String deleteCategoryById(Long id) {
    	categoryRepository.deleteById(id);
    	return new String("Categoria eliminada: "+id);
    }
    
    //actualiza una categoria recibida por parametro
    public Category updateCategoryById(Category category) {
    	return categoryRepository.save(category);
    }
    
    //devuelve todas las categorias de la entidad en una lista
    public List <Category> allCategories(){
    	return categoryRepository.findAll();
    }
    
}
