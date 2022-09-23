package lovepink.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "Products")
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String image;
	private Double price;
	@DateTimeFormat(pattern = "yyyy-MM-dd") @Column(name = "Createdate")
	private Date createDate = new Date(new java.util.Date().getTime());
	private Boolean available;
	@ManyToOne @JoinColumn(name = "Categoryid")
	private Category category;
	@JsonIgnore @OneToMany(mappedBy = "product")
	private List<OrderDetail> orderDetails;

	public Product(Integer id) {
		this.id = id;
	}

}
