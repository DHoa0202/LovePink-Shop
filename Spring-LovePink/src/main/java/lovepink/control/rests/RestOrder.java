package lovepink.control.rests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.JsonNode;
import lovepink.entities.Order;
import lovepink.model.services.OrderService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/orders")
public class RestOrder {
	@Autowired
	private OrderService orderService;

	@PostMapping
	public ResponseEntity<Order> create(@RequestBody JsonNode orderData) {
		return ResponseEntity.ok(orderService.create(orderData));
	}
}
