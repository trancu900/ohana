package vn.ohana.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.ohana.category.dto.CategoryCreationParam;
import vn.ohana.category.dto.CategoryResult;
import vn.ohana.category.dto.CategoryUpdateParam;
import vn.ohana.entities.Category;
import vn.rananu.shared.exceptions.NotFoundException;
import vn.rananu.shared.exceptions.ValidationException;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    @Transactional(readOnly = true)
    public List<CategoryResult> findAll() {
        List<Category> entities = categoryRepository.findAll();
        return categoryMapper.toDTOList(entities);
//
//        List<CategoryResult> dtoList = new ArrayList<>();
//
//        for (Category category : entities) {
//            CategoryResult dto = categoryMapper.toDTO(category);
//////            dtoList.add(dto);
//        }
//       return dtoList;
//      return   entities
//              .stream()
//              .map( categoryMapper::toDTO)
//              .collect(Collectors.toList());


    }

    @Transactional(readOnly = true)
    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("category.exception.notFound"));
    }

    @Override
    public CategoryResult getById(Long id) {
        return categoryMapper.toDTO(findById(id));
    }

    private void validationByTitle(String title) {
        if (categoryRepository.existsByTitle(title)) {
            throw new ValidationException("title", "category.validation.title.existed");
        }
    }

    @Override
    @Transactional
    public CategoryResult create(CategoryCreationParam param) {
        validationByTitle(param.getTitle());
        Category category = categoryMapper.toEntity(param);
        category = categoryRepository.save(category);
        return categoryMapper.toDTO(category);
    }

    @Override
    @Transactional
    public CategoryResult update(CategoryUpdateParam param) {
        Category category = findById(param.getId());
        String title = param.getTitle();

        if (!category.getTitle().equalsIgnoreCase(title)) {
            validationByTitle(title);
        }

        categoryMapper.transferFields(param, category);
        return categoryMapper.toDTO(category);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

}
