package com.codegyme.module3_final_exam.controllers;

import com.codegyme.module3_final_exam.models.Product;
import com.codegyme.module3_final_exam.services.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name="MainController", urlPatterns = "/product")
public class MainController extends HttpServlet {
    private ProductService productService = new ProductService();
    private List<Product> products = new ArrayList<>();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                req.getRequestDispatcher("product/create.jsp").forward(req, resp);
                break;

            default:
                products = productService.getAll();
                req.setAttribute("products", products);
                req.getRequestDispatcher("product/display.jsp").forward(req, resp);
                break;
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        Product product = null;
        String name;
        int price;
        int discount;
        int stock;
        int id;

        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                name = req.getParameter("name");
                price = Integer.parseInt(req.getParameter("price"));
                discount = Integer.parseInt(req.getParameter("discount"));
                stock = Integer.parseInt(req.getParameter("stock"));
                product = new Product(name,price,discount,stock);
                boolean isAdded = productService.addProduct(product);
                if (isAdded) {
                    req.setAttribute("message", "Product is successfully added");
                } else {
                    req.setAttribute("message", "Product is not added");
                }
                req.getRequestDispatcher("product/create.jsp").forward(req, resp);
                break;
            case "viewTop":
                int top = Integer.parseInt(req.getParameter("top"));
                products = productService.viewTop(top);
                req.setAttribute("products", products);
                req.getRequestDispatcher("product/display.jsp").forward(req, resp);
                break;
            case "listsSaleTime":
                String startDate = req.getParameter("startDate");
                String startEnd = req.getParameter("endDate");
                products = productService.viewListSaleTime(startDate,startEnd);
                req.setAttribute("products", products);
                req.getRequestDispatcher("product/display.jsp").forward(req, resp);
                break;
        }
    }
}
