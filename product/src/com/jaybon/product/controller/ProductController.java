package com.jaybon.product.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jaybon.product.action.Action;
import com.jaybon.product.action.product.ProductCountDescProcAction;
import com.jaybon.product.action.product.ProductDeleteProcAction;
import com.jaybon.product.action.product.ProductGoFirstProcAction;
import com.jaybon.product.action.product.ProductPriceAscProcAction;
import com.jaybon.product.action.product.ProductPriceDescProcAction;
import com.jaybon.product.action.product.ProductTestAction;
import com.jaybon.product.action.product.ProductInsertItemProcAction;


// http://localhost:8000/blog/user
@WebServlet("/test")
public class ProductController extends HttpServlet {
	private final static String TAG = "ProductController : ";
	private static final long serialVersionUID = 1L;

	public ProductController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// http://localhost:8000/product/user?cmd=test
		String cmd = request.getParameter("cmd");
		System.out.println(TAG + "router : " + cmd);
		Action action = router(cmd);
		action.execute(request, response);
	}

	public Action router(String cmd) {
		if (cmd.equals("home")) {
			return new ProductTestAction();
		} else if (cmd.equals("goFirstProc")) {
			return new ProductGoFirstProcAction();
		} else if (cmd.equals("priceAscProc")) {
			return new ProductPriceAscProcAction();
		} else if (cmd.equals("priceDescProc")) {
			return new ProductPriceDescProcAction();
		} else if (cmd.equals("countDescProc")) {
			return new ProductCountDescProcAction();
		} else if (cmd.equals("deleteProc")) {
			return new ProductDeleteProcAction();
		} else if (cmd.equals("insertItem")) {
			return new ProductInsertItemProcAction();
		}
		return null;
	}
}