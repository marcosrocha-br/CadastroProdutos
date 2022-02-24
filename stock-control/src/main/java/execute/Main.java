package execute;

import java.util.List;

import org.junit.Test;

import dao.ControlDao;
import model.Product;

public class Main {

	@Test
	public void listAllProducts() {

		ControlDao dao = new ControlDao();

		List<Product> list = dao.selectAll();

		for (Product product : list) {
			System.out.println(product);
		}
	}

	@Test
	public void insertProduct() {
		ControlDao dao = new ControlDao();

		Product product = new Product();
		product.setName("Feijão");
		product.setPrice(7.85);
		product.setQuantity(52);

		dao.insert(product);
	}

	@Test
	public void updateProductName() {
		ControlDao dao = new ControlDao();

		Product product = new Product();
		product.setName("Açúcar");
		product.setId(2);

		dao.updateName(product);
	}

	@Test
	public void updateProductPrice() {
		ControlDao dao = new ControlDao();

		Product product = new Product();
		product.setId(2);
		product.setPrice(2.98);

		dao.updatePrice(product);
	}

	@Test
	public void updateProductQuantity() {
		ControlDao dao = new ControlDao();

		Product product = new Product();
		product.setId(2);
		product.setQuantity(18);

		dao.updateQuantity(product);
	}

	@Test
	public void deleteProduct() {
		ControlDao dao = new ControlDao();

		Product product = new Product();
		product.setId(2);

		dao.delete(product);
	}

}
