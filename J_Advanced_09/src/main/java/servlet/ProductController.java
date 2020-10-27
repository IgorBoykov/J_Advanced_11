package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Product;
import service.ProductService;
import serviceImpl.ProductServiceImpl;

@WebServlet("/product")
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ProductService productService = ProductServiceImpl.getProductService();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String price = request.getParameter("price");

		Product product = new Product(name, description, getValidatedPrice(price));
		productService.create(product);
		response.setContentType("text");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write("Success");
	}

	private double getValidatedPrice(String price) {
		if (price == null || price.isEmpty()) {
			return 0;
		}
		return Double.parseDouble(price);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String productId = request.getParameter("id");

		Product product = productService.read(Integer.parseInt(productId));

		request.setAttribute("product", product);
		request.getRequestDispatcher("singleProduct.jsp").forward(request, response);
	}

}
