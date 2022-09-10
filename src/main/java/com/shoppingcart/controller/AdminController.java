package com.shoppingcart.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shoppingcart.dao.OrderDAO;
import com.shoppingcart.dao.ProductDAO;
import com.shoppingcart.model.OrderDetailInfo;
import com.shoppingcart.model.OrderInfo;
import com.shoppingcart.model.PaginationResult;
import com.shoppingcart.model.ProductInfo;
import com.shoppingcart.validator.ProductInfoValidator;

@Controller
public class AdminController {

	@Autowired
	private OrderDAO orderDAO;

	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private ProductInfoValidator productInfoValidator;

	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	// GET: Hiển thị trang login
	@GetMapping(value = { "/login" })
	public String login(Model model) {
		return "login";
	}
	
	@GetMapping("/403")
	public String accessDenied() {
		return "/403";
	}

	@GetMapping(value = { "/accountInfo" })
	public String accountInfo(HttpServletRequest request, Model model) {
		/* HttpServletRequest = ${pageContext.request}
		//http://localhost:8081/shoppingcart/accountInfo
		String serverName = request.getServerName();//localhost = ${pageContext.request.serverName}
		int serverPort = request.getServerPort();//8081 = ${pageContext.request.port}
		String contextPath = request.getContextPath();//shoppingcart = ${pageContext.request.contextPath}
		String servletPath = request.getServletPath();//accountInfo = ${pageContext.request.servletPath}
		String method = request.getMethod();//GET
		HttpSession session = request.getSession();
		Map<String, String[]> map = request.getParameterMap();
		String name = request.getUserPrincipal().getName();//nếu đã xác thực thì sẽ trả về username = ${pageContext.request.userPrincipal.name}
		*/

		//String name = request.getUserPrincipal().getName();
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println("username: " + userDetails.getUsername());
		System.out.println("password: " + userDetails.getPassword());
		System.out.println("enable: " + userDetails.isEnabled());

		model.addAttribute("userDetails", userDetails);
		return "accountInfo";
	}

	@GetMapping(value = { "/orderList" }) // orderList?page=2
	public String orderList(Model model, @RequestParam(value = "page", defaultValue = "1") int page) {
		final int MAX_RESULT = 5;
		PaginationResult<OrderInfo> paginationOrderInfos = orderDAO.getAllOrderInfos(page, MAX_RESULT);
		model.addAttribute("paginationOrderInfos", paginationOrderInfos);
		return "orderList";
	}

	@GetMapping(value = { "/order" })
	public String orderView(Model model, @RequestParam("orderId") String orderId) {
		OrderInfo orderInfo = null;
		if (orderId != null) {
			orderInfo = orderDAO.getOrderInfoById(orderId);
		}
		if (orderInfo == null) {
			return "redirect:/orderList";
		}

		List<OrderDetailInfo> orderDetailInfos = orderDAO.getAllOrderDetailInfos(orderId);
		orderInfo.setOrderDetailInfos(orderDetailInfos);
		model.addAttribute("orderInfo", orderInfo);
		return "order";
	}

	// GET: Hiển thị product // create -->/product  edit -->/product?code=s1
	@GetMapping(value = { "/product" })
	public String product(Model model, @RequestParam(value = "code", defaultValue = "") String code) {
		ProductInfo productInfo = null;
		if (code != null && code.length() > 0) {
			productInfo = productDAO.getProductInfoByCode(code);
		}
		if (productInfo == null) {
			productInfo = new ProductInfo();
			productInfo.setNewProduct(true);
		}

		model.addAttribute("productForm", productInfo);
		return "product";
	}

	// POST: Save product
	@PostMapping(value = { "/product" })
	public String productSave(Model model, @ModelAttribute("productForm") @Valid ProductInfo productInfo,
			BindingResult result) {
		productInfoValidator.validate(productInfo, result);
		if (result.hasErrors()) {
			return "product";
		}

		try {
			productDAO.saveProductInfo(productInfo);
		} catch (Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "product";
		}
		return "redirect:/productList";
	}

}