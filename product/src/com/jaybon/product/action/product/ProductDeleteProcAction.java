package com.jaybon.product.action.product;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.jaybon.product.action.Action;
import com.jaybon.product.model.Product;
import com.jaybon.product.repository.ProductRepository;

public class ProductDeleteProcAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int productId = Integer.parseInt(request.getParameter("productId"));
		
		ProductRepository productRepository = ProductRepository.getInstance();
		
		int result = productRepository.deleteById(productId);
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.println(result+"");

		
	}

}
