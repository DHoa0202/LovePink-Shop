package lovepink.control.rests;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import lovepink.entities.*;
import lovepink.model.reponsitories.*;

@CrossOrigin("*") // response header Access-EntityControl-Allow-Origin
@RestController
/**
 * <h3>For account has id is IDENTITY</h3>
 **/
public class RestAccount {
	@Autowired
	private AccountReponsitory accountDAO;
	@Autowired
	private AuthorityReponsitory authorityDAO;
	@Autowired
	private RoleReponsitory roleDAO;

	// GET -> ALL TO MAP
	@SuppressWarnings("rawtypes")
	@RequestMapping({ "/rest/accounts" })
	public ResponseEntity<Map<String, List>> getMap(Model model) {
		Map<String, List> map = new HashMap<>();
		map.put("accounts", accountDAO.findAll());
		map.put("roles", roleDAO.findAll());
		return ResponseEntity.ok(map);
	}
	
	// GET -> READ WITH ID
	@GetMapping("/rest/accounts/{id}")
	public ResponseEntity<Account> getById(@PathVariable("id") String id) {
		Optional<Account> optional = accountDAO.findById(id);
		return optional.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(optional.get());
	}
	
	// POST -> SAVE
	@PostMapping("/rest/accounts")
	public ResponseEntity<Account> save(@RequestBody Account account) {
		try {
			return ResponseEntity.ok(accountDAO.save(account));
		} catch (IllegalArgumentException e) {
			return ResponseEntity.noContent().build();
		}
	}

	// PUT -> UPDATE
	@PutMapping("/rest/accounts")
	public ResponseEntity<Account> update(@RequestBody Account account) {
		Optional<Account> optional = accountDAO.findById(account.getUsername());
		try {
			this.restartAuthorities(account);
			if (!optional.isEmpty()) return ResponseEntity.ok(accountDAO.save(account));
		} catch (Exception e) {
			ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	// DELETE -> DELETE BY ID
	@DeleteMapping("/rest/accounts/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") String id) {
		Optional<Account> optional = accountDAO.findById(id);
		if (optional.isEmpty())
			return ResponseEntity.notFound().build();
		else
			accountDAO.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	// UPDATE Authorities
	private void restartAuthorities(Account account) {
		Set<Authority> authorities = authorityDAO.findByAccount(account);
		authorities.forEach(x -> authorityDAO.delete(x)); // delete authorities before save-up
		
		account.getAuthorities().forEach(x -> { // save-up authorities
			x.setAccount(new Account(account.getUsername()));
			authorityDAO.save(x);
		});
	}
}
