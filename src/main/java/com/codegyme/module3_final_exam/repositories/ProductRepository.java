package com.codegyme.module3_final_exam.repositories;

import com.codegyme.module3_final_exam.models.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    public boolean add(Product product) {
        boolean isAdded;
        try {
            Connection connection = BaseRepository.getConnection();
            PreparedStatement preparedStatement = null;
            preparedStatement = connection.prepareStatement("INSERT INTO codegyme.products (name, price, discount, stock) values (?,?,?,?)");
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.setInt(3, product.getDiscount());
            preparedStatement.setInt(4, product.getStock());
            isAdded = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isAdded;
    }

    public Product findProductById(int productId) {
        try {
            Connection connection = BaseRepository.getConnection();
            PreparedStatement preparedStatement = null;
            preparedStatement = connection.prepareStatement("SELECT * FROM codegyme.products WHERE id = ?");
            preparedStatement.setInt(1, productId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int price = resultSet.getInt("price");
                int discount = resultSet.getInt("discount");
                int stock = resultSet.getInt("stock");
                return new Product(id, name, price,discount, stock);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void remove(Product product) {
        try {
            Connection connection = BaseRepository.getConnection();
            PreparedStatement preparedStatement = null;
            preparedStatement = connection.prepareStatement("DELETE FROM codegyme.products WHERE id = ?");
            preparedStatement.setInt(1, product.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean editProduct(int id, Product product) {
        boolean isEdited;
        try {
            Connection connection = BaseRepository.getConnection();
            PreparedStatement preparedStatement = null;
            preparedStatement = connection.prepareStatement("UPDATE codegyme.products SET name=?, price=?, discount=?, stock=? WHERE id = ?");
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.setInt(3, product.getDiscount());
            preparedStatement.setInt(4, product.getStock());
            preparedStatement.setInt(5, id);
            isEdited = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isEdited;
    }

    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        try {
            Connection connection = BaseRepository.getConnection();
            PreparedStatement preparedStatement = null;
            preparedStatement = connection.prepareStatement("SELECT * FROM codegyme.products");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int price = resultSet.getInt("price");
                int discount = resultSet.getInt("discount");
                int stock = resultSet.getInt("stock");
                products.add(new Product(id, name, price, discount, stock));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return products;
    }



    public List<Product> viewTop(int top) {
        List<Product> products = new ArrayList<>();
        try {
            Connection connection = BaseRepository.getConnection();
            PreparedStatement preparedStatement = null;
            preparedStatement = connection.prepareStatement("SELECT products.id,products.name,products.price,products.discount,products.stock, SUM(quantity) AS quantity FROM codegyme.products JOIN codegyme.order_details ON products.id = order_details.product_id GROUP BY products.id  ORDER BY quantity desc LIMIT ?");
            preparedStatement.setInt(1, top);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int price = resultSet.getInt("price");
                int discount = resultSet.getInt("discount");
                int stock = resultSet.getInt("stock");
                products.add(new Product(id, name, price, discount, stock));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return products;
    }

    public List<Product> viewListSaleTime(String startDate, String endDate) {
        List<Product> products = new ArrayList<>();
        try {
            Connection connection = BaseRepository.getConnection();
            PreparedStatement preparedStatement = null;
            preparedStatement = connection.prepareStatement("SELECT products.id,products.name,products.price,products.discount,products.stock, SUM(quantity) AS quantity FROM codegyme.products JOIN codegyme.order_details ON products.id = order_details.product_id JOIN codegyme.orders ON order_details.order_id = orders.id WHERE booking_date BETWEEN ? AND ? GROUP BY products.id HAVING quantity>0  ORDER BY quantity");
            preparedStatement.setString(1, startDate);
            preparedStatement.setString(2, endDate);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int price = resultSet.getInt("price");
                int discount = resultSet.getInt("discount");
                int stock = resultSet.getInt("stock");
                products.add(new Product(id, name, price, discount, stock));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return products;
    }
}
