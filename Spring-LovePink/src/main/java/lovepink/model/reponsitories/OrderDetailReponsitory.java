package lovepink.model.reponsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lovepink.entities.OrderDetail;

@Repository
public interface OrderDetailReponsitory extends JpaRepository<OrderDetail,Long> {
	
}
