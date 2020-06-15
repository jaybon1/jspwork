package com.jaybon.product.action.product;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jaybon.product.action.Action;
import com.jaybon.product.model.Product;
import com.jaybon.product.repository.ProductRepository;

public class ProductTestAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("ProductTestAction");
		
		ProductRepository productRepository = ProductRepository.getInstance();
		
		List<Product> products = productRepository.findAll();
		
		request.setAttribute("products", products);
		
		System.out.println("홈페이지 이동합니다.");
		
		RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
		rd.forward(request, response);
		
	}
}
