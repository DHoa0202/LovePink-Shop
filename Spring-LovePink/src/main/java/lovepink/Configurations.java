package lovepink;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lovepink.entities.*;

@Configuration
public class Configurations {
	@Bean
	protected BCryptPasswordEncoder getBCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	
	//__________________________________________________________ Entity beans
	@Bean
	protected Account getAccount() {
		return new Account();
	}

	@Bean
	protected Authority getAuthority() {
		return new Authority();
	}

	@Bean
	protected Category getCategory() {
		return new Category();
	}

	@Bean
	protected Order getOrder() {
		return new Order();
	}

	@Bean
	protected OrderDetail getOrderDetail() {
		return new OrderDetail();
	}

	@Bean
	protected Product getProduct() {
		return new Product();
	}

	@Bean
	protected Role getRole() {
		return new Role();
	}
}
