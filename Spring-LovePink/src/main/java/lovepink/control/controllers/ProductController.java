package lovepink.control.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lovepink.entities.Product;
import lovepink.model.reponsitories.ProductReponsitory;


@Controller
public class ProductController {
	@Autowired
	private ProductReponsitory productDAO;

	@RequestMapping("/product/list")
	public String listProduct( Model model, Optional<String> cid) throws Exception {
		if(cid.isPresent()){
			model.addAttribute("items",productDAO.findByCategory(cid.get()));
		}
		else {
			List<Product> list= productDAO.findAll();
			model.addAttribute("items",list);
		}
		return "product/list";
	}
	
	@RequestMapping("/product/detail/{id}")
	public String detailProduct(Model model,@PathVariable("id") Integer id ) {
		Product item = productDAO.findById(id).get();
		model.addAttribute("item",item);
		return "product/detail";
	}
	
	@RequestMapping("/product/list/filter")
	public String filter(Model model,
			  @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
		      @RequestParam(name = "size", required = false, defaultValue = "8") Integer size,
		      @RequestParam(name = "field", required = false, defaultValue = "id") String field,
		      @RequestParam(name = "desc", required = false, defaultValue = "false") Boolean desc){	
		Sort sortable = desc ? Sort.by(field).descending() : Sort.by(field).ascending();
		PageRequest pageable = PageRequest.of(page, size==0?1:size, sortable);
		model.addAttribute("items",productDAO.findAll(pageable));
		return "product/list";
	}
}
