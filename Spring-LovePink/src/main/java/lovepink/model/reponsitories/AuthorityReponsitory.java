package lovepink.model.reponsitories;

import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lovepink.entities.Account;
import lovepink.entities.Authority;

@Repository
public interface AuthorityReponsitory extends JpaRepository<Authority,Integer> {
	public Set<Authority> findByAccount(Account account);
}
