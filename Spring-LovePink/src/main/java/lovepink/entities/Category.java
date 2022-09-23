package lovepink.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
@Data
@Entity 
@Table(name = "Categories")
@NoArgsConstructor
@AllArgsConstructor
public class Category implements Serializable{
	@Id private String id;
	private String name;
	@JsonIgnore @OneToMany(mappedBy = "category")
	private List<Product> products;
	
}
