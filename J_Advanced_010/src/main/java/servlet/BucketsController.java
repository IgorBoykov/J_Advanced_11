package servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import domain.Bucket;
import domain.Product;
import dto.BucketDto;
import service.BucketService;
import service.ProductService;
import serviceImpl.BucketServiceImpl;
import serviceImpl.ProductServiceImpl;

@WebServlet("/buckets")
public class BucketsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BucketService bucketService = BucketServiceImpl.getBucketService();
	private ProductService productService = ProductServiceImpl.getProductService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Bucket> buckets = bucketService.readAll();
		Map<Integer, Product> idToProduct = productService.readAllMap();
		List<BucketDto> listOfBucketDtos = map(buckets, idToProduct);

		String json = new Gson().toJson(listOfBucketDtos);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

	public List<BucketDto> map(List<Bucket> buckets, Map<Integer, Product> idToProduct) {

		return buckets.stream().map(bucket -> {
			BucketDto bucketDto = new BucketDto();
			bucketDto.bucketId = bucket.getId();
			bucketDto.purchaseDate = bucket.getPuchase_date();

			Product product = idToProduct.get(bucket.getProduct_id());
			bucketDto.name = product.getName();
			bucketDto.description = product.getDescription();
			bucketDto.price = product.getPrice();

			return bucketDto;
		}).collect(Collectors.toList());

	}

}
