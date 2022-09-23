package lovepink.control.controllers;

import java.security.Principal;
import java.util.Date;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import lovepink.entities.Account;
import lovepink.model.reponsitories.AccountReponsitory;

@Controller
public class AccountControl {
	
	@Autowired
	private AccountReponsitory accountDAO;
	
	@GetMapping("/secutity/myInfo") public String getInfo() {
		return "/security/info";
	}
	
	@GetMapping("/security/register") public String getSignUp(Model model, Account account) {
		model.addAttribute("message", "Đăng ký thông tin"+new Date(System.currentTimeMillis()).toString());
		return "/security/register";
	}
	
	@PostMapping("/security/register") public String accountRegister(Model model, Account account) {
		Optional<Account> optional = accountDAO.findById(account.getUsername());
		if(optional.isEmpty() && accountDAO.save(account)!=null) {
			System.out.println(String.format("register acacount %s successfully.", account.getUsername()));
			return "redirect:/lovepink";
		}
		model.addAttribute("account",account);
		model.addAttribute("message", String.format("<b class='text-danger'>%s</b> already exists!", account.getUsername()));
		return "/security/register";
	}
	
	//_______________________________________________ SECURITY - LOGIN
	@RequestMapping("/security/login/form") public String form() {
		return "security/login";
	}
	
	@RequestMapping("/login/success")
	public String success(HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		request.setAttribute("message", principal.getName() + " đăng nhập thành công!");
		return "redirect:/product/list";
	}

	@RequestMapping("/login/failed")
	public String failed(HttpServletRequest request) {
		request.setAttribute("message", "Sai thông tin tài khoản!");
		return "/security/login";
	}

	@RequestMapping("/login/error")
	public String error(HttpServletRequest request) {
		request.setAttribute("message", "Lỗi đăng nhập!");
		return "/security/login";
	}
	
	@RequestMapping("/login/error/role")
	public String notAccess(HttpServletRequest request) {
		String message = String.format("<a href='/secutity/myInfo'><b>%s</b></a> không có quyền truy cập trang này", request.getUserPrincipal().getName());
		request.setAttribute("message", message);
		return "/security/login";
	}
	
	//_______________________________________________ SECURITY - LOGOUT
	@RequestMapping("/logout/success")
	public String logout(HttpServletRequest request) {
		request.setAttribute("message", "đăng xuất thành công!");
		return "refirect:/product/list";
	}
}
