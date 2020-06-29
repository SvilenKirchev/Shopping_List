package shopping.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import shopping.repository.ProductRepository;
import shopping.service.ProductService;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    private final ProductService productService;

    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public ModelAndView index(HttpSession httpSession, ModelAndView modelAndView){
        if (httpSession.getAttribute("user") == null){
            modelAndView.setViewName("index");
        }else {
            modelAndView.addObject("foods", this.productService.findAllByCategory("FOOD"));
            modelAndView.addObject("drinks", this.productService.findAllByCategory("DRINK"));
            modelAndView.addObject("households", this.productService.findAllByCategory("HOUSEHOLD"));
            modelAndView.addObject("other", this.productService.findAllByCategory("OTHER"));
            modelAndView.setViewName("home");
        }
        return modelAndView;
    }
}
