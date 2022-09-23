package lovepink.control.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeControl {
	
	@PreAuthorize("hasAnyRole('DIRE')") // access by "DIRECTOR"
	@GetMapping("/manager/{page}") public String getForm(@PathVariable(name = "page", required = false) String page) {
		return String.format("/manager/%s", page == null ? "product" : page);
	}
	
	@RequestMapping({"/", "/lovepink" ,"/home/index"})
	public String home() {
		return "redirect:/product/list/filter";
	}
	
	@RequestMapping({"/admin","/admin/home/index"})
	public String admin() {
		return "redirect:/assets/admin/index.html";
	}
	
	@RequestMapping("/cart/view")
	public String listProduct() {
		return "cart/view";
	}
	
}
