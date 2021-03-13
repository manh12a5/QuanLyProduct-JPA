package product.manager.controller;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import product.manager.model.Category;
import product.manager.model.Product;
import product.manager.service.category.ICategoryService;
import product.manager.service.product.IProductService;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductService productService;

    @Autowired
    private ICategoryService categoryService;

    @ModelAttribute("categories")
    public List<Category> allCategory(){
        return categoryService.findAll();
    }

    @ExceptionHandler(NotFoundException.class)
    public ModelAndView showNotFound() {
        return new ModelAndView("error404");
    }

    //Show All
    @GetMapping("")
    public ModelAndView showAll(@PageableDefault(size = 5) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("list");
        Page<Product> productPage = productService.findAll(pageable);
        modelAndView.addObject("products", productPage);
        return modelAndView;
    }

    //Create
    @GetMapping("/create")
    public ModelAndView showCreate() {
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView create(@ModelAttribute("product") Product product) {
        ModelAndView modelAndView = new ModelAndView("create");
        productService.save(product);
        modelAndView.addObject("product", product);
        modelAndView.addObject("message", "Tao moi thanh cong");
        return modelAndView;
    }

    //Edit
    @GetMapping("/edit/{id}")
    public ModelAndView showEdit(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("edit");
        Product product = productService.findById(id);
        modelAndView.addObject("product", product);
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView edit(@ModelAttribute("product") Product product) {
        ModelAndView modelAndView = new ModelAndView("edit");
        productService.save(product);
        modelAndView.addObject("product", product);
        return modelAndView;
    }

    //Delete
    @GetMapping ("/delete/{id}")
    public ModelAndView showDelete(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/products");
        productService.remove(id);
        return modelAndView;
    }

    //SearchNameProduct
    @PostMapping("/search")
    public ModelAndView showSearchNameProduct(@RequestParam String name) {
        ModelAndView modelAndView = new ModelAndView("list");
        String nameProduct = "%" + name + "%";
        List<Product> productList = productService.findByName(nameProduct);
        modelAndView.addObject("products", productList);
        return modelAndView;
    }

    //SearchCategory
    @PostMapping("/searchCategory")
    public ModelAndView showSearchCategory(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("list");
        List<Product> productList = productService.findByCategoryName(id);
        modelAndView.addObject("products", productList);
        return modelAndView;
    }

    //Top 5 gia cao nhat
    @GetMapping("/top5PriceMax")
    public ModelAndView find5PriceMax() {
        ModelAndView modelAndView = new ModelAndView("list");
        modelAndView.addObject("products", productService.top5ProductPriceMax());
        return modelAndView;
    }

    //Top 5 san pham moi nhat
    @GetMapping("/top5ProductNewest")
    public ModelAndView find5ProductNew() {
        ModelAndView modelAndView = new ModelAndView("list");
        modelAndView.addObject("products", productService.top5ProductNewest());
        return modelAndView;
    }

    //Tong tien
    @GetMapping("/sumPrice")
    public ModelAndView sumPrice() {
        ModelAndView modelAndView = new ModelAndView("list");
        modelAndView.addObject("sumPrice", productService.sumPrice());
        modelAndView.addObject("products", productService.findAll());
        return modelAndView;
    }

}
