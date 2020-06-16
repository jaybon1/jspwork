package com.jaybon.product.action.product;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jaybon.product.action.Action;
import com.jaybon.product.model.Product;
import com.jaybon.product.model.ProductType;
import com.jaybon.product.repository.ProductRepository;

import jdk.nashorn.internal.ir.RuntimeNode.Request;

public class ProductInsertItemProcAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println(request.getParameter("productType"));
		
		Product product = Product.builder()
				.name(request.getParameter("productName"))
				.type(ProductType.valueOf(request.getParameter("productType")))
				.price(Integer.parseInt(request.getParameter("productPrice")))
				.count(Integer.parseInt(request.getParameter("productCount")))
				.build();
		
		ProductRepository productRepository = ProductRepository.getInstance();
		
		int result = productRepository.insertItem(product);
		
		if(result ==1 ) {
			
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
			
		}
				
	}

}
