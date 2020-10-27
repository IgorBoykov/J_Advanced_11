import domain.Product;
import domain.User;
import service.ProductService;
import service.UserService;
import serviceImpl.ProductServiceImpl;
import serviceImpl.UserServiceImpl;

public class AppTest {

	public static void main(String[] args) {
		
		ProductService pr = new ProductServiceImpl();
		pr.create(new Product("test", "test", 12));

		UserService us = new UserServiceImpl();
		us.create(new User("igor@gmail.com", "igor", "boikov", "admin"));
	}

}
