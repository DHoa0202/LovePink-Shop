package lovepink.model.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lovepink.model.reponsitories.CategoryReponsitory;

@Component
public class GlobalInterCepter implements HandlerInterceptor {
	
	@Autowired
	private CategoryReponsitory categoryDAO;
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		request.setAttribute("cates", categoryDAO.findAll());
	}
	
}
