package lovepink.model.reponsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lovepink.entities.Account;

@Repository
public interface AccountReponsitory extends JpaRepository<Account, String> {
	
}
