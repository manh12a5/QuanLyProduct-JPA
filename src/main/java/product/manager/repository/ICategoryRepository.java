package product.manager.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import product.manager.model.Category;

@Repository
public interface ICategoryRepository extends PagingAndSortingRepository<Category, Long> {
}
