package product.manager.service.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import product.manager.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import product.manager.repository.IProductRepository;

import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    IProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findOne(id);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void remove(Long id) {
        productRepository.delete(id);
    }

    @Override
    public List<Product> findByName(String name) {
        return productRepository.findProductName(name);
    }

    @Override
    public List<Product> findByCategoryName(Long id) {
        return productRepository.findProductByCategoryName(id);
    }

    @Override
    public List<Product> top5ProductPriceMax() {
        return productRepository.findTop5ByOrderByPriceDesc(5);
    }

    @Override
    public List<Product> top5ProductNewest() {
        return productRepository.findTop5ByOrderByDateTimeDesc();
    }

    @Override
    public long sumPrice() {
        return productRepository.sumPrice();
    }


}
