package lovepink.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;

@SuppressWarnings("serial")
@Data
@Entity 
@Table(name = "Orderdetails")
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail  implements Serializable{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Double price;
	private Integer quantity;
	@ManyToOne @JoinColumn(name = "Productid")
	private Product product;
	@ManyToOne @JoinColumn(name = "Orderid")
	private Order order;
}
