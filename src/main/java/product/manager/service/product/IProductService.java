package product.manager.service.product;

import product.manager.model.Product;
import product.manager.service.IService;

import java.util.List;

public interface IProductService extends IService<Product> {

    List<Product> findByCategoryName(Long id);

    List<Product> top5ProductPriceMax();

    List<Product> top5ProductNewest();

    long sumPrice();
}
