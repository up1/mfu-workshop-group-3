package api.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import entity.Product;
import java.io.IOException;
import service.ProductService;
import entity.Customer;
import service.CustomerService;

@WebServlet("/calculate")
public class CalculateServlet extends HttpServlet {


    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response)
            throws ServletException, IOException {

        int customerId = 0;
        int productId = 0;
        int quantity = 1;
        ObjectMapper mapper = new ObjectMapper();
        Order order = mapper.readValue(req.getInputStream(), Order.class);
        customerId = order.getCustomerId();
        productId = order.getProductId();
        quantity =  order.getQuantity();




        ProductService productService = new ProductService();
        Product productToPurchase = productService.findById(productId);
        float totalPrice = productToPurchase.getPrice() * quantity;
            
        CustomerService customerService = new CustomerService();
        Customer customerToPurchase = customerService.findById(customerId);

        float totalNet = customerToPurchase.getBalance() - totalPrice;
        




      


    }
    
}

class Order{
    private int productId;
    private int customerId;
    private int quantity; 

    public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}

