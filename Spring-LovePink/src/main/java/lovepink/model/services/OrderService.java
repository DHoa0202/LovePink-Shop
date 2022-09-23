package lovepink.model.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lovepink.entities.Order;
import lovepink.entities.OrderDetail;
import lovepink.model.DAOes.OrderDAO;
import lovepink.model.reponsitories.OrderDetailReponsitory;
import lovepink.model.reponsitories.OrderReponsitory;

@Service
public class OrderService implements OrderDAO {
	@Autowired
	private OrderReponsitory orderDAO;
	@Autowired
	private OrderDetailReponsitory orderDetailDAO;

	@Override
	public Order create(JsonNode orderData) {
		ObjectMapper mapper = new ObjectMapper();
		Order order = mapper.convertValue(orderData, Order.class);
		orderDAO.save(order);
		TypeReference<List<OrderDetail>> type = new TypeReference<List<OrderDetail>>(){};
		
		List<OrderDetail> details = mapper.convertValue(orderData.get("orderDetails"), type).stream()
				.peek(d -> d.setOrder(order)).collect(Collectors.toList());
		orderDetailDAO.saveAll(details);
		return order;
	}
	
	@Override
	public Order findById(Long id) {
		return orderDAO.findById(id).get();
	}
	
	@Override
	public List<Order> findByUsername(String username) {
		return orderDAO.findByUsername(username);
	}

}
