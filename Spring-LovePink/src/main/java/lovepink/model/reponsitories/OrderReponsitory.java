package lovepink.model.reponsitories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import lovepink.entities.Order;

@Repository
public interface OrderReponsitory extends JpaRepository<Order,Long> {

	@Query("SELECT o FROM Order o WHERE o.account.username=?1")
	public List<Order>findByUsername(String username);
}
