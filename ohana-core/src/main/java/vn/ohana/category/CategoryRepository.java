package vn.ohana.category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vn.ohana.entities.Category;
import vn.ohana.entities.StatusCategory;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByTitle(String title);

    Page<Category> findAllByStatus(StatusCategory status,Pageable pageable);
}
