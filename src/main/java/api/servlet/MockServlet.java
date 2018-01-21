package api.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import entity.Customer;
import entity.Product;
import service.CustomerService;
import service.ProductService;

@WebServlet("/mock")
public class MockServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ProductService productService = new ProductService();
		CustomerService customerService = new CustomerService();
		Customer inkCustomer = new Customer("Ink", 1500f);
		inkCustomer.setCustomerId(999);
		Product cocoProduct = new Product("Coco", 55f);
		cocoProduct.setProductId(999);
		productService.create(cocoProduct);
		customerService.create(inkCustomer);
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(resp.getOutputStream(), inkCustomer);
	}

}
