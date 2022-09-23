package lovepink.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "Accounts")
@NoArgsConstructor
@AllArgsConstructor
public class Account  implements Serializable{
	@Id private String username;
	private String password;
	private String fullname;
	private String email;
	private String photo;
	@JsonIgnore @OneToMany(mappedBy = "account")
	private List<Order> orders;
	@OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
	private List<Authority> authorities;
	
	public Account(String username) {
		this.username = username;
	}
	
}
