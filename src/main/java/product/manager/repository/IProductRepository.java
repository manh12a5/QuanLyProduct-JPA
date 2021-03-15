package product.manager.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import product.manager.model.Product;

import java.util.List;

public interface IProductRepository extends PagingAndSortingRepository<Product, Long> {

    //Tìm kiếm sản phẩm theo tên
    @Query(value = "select  * from product where product.name like ?", nativeQuery = true)
    List<Product> findProductName(String name);

    //Tìm kiếm sản phẩm theo category
    @Query(value = "select * from product where product.category_id = ?", nativeQuery = true)
    List<Product> findProductByCategoryName(Long id);

    //Top5 Product Newest
    List<Product> findTop5ByOrderByDateTimeDesc();

//    @Query(value = "select * from product order by date desc limit ?", nativeQuery = true)
//    List<Product> findTop5ByOrderByDatetimeDesc(int number);

    //Top5 price max
    List<Product> findTop5ByOrderByPriceDesc();

//    @Query(value = "select * from product order by price desc limit ?", nativeQuery = true)
//    List<Product> findTop5ByOrderByPriceDesc(int number);

    // Tinh tổng tiền
    @Query(value = "select sum(price * quantity) from Product ")
    long sumPrice();

}
