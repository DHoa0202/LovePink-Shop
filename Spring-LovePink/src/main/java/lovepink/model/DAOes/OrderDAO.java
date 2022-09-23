package lovepink.model.DAOes;

import java.util.List;
import com.fasterxml.jackson.databind.JsonNode;
import lovepink.entities.Order;

public interface OrderDAO {
	public Order create(JsonNode orderData);
	public Order findById(Long id);
	public List<Order> findByUsername(String username);
}
