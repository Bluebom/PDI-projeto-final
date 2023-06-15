package br.com.softslab.qrcode.products.facade;

import br.com.softslab.qrcode.db.DB;
import br.com.softslab.qrcode.products.dto.ProductDTO;
import br.com.softslab.qrcode.qrcode.QrCodeGenerator;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductFacade {
    private DB databaseConnection;

    public ProductFacade() {
        databaseConnection = new DB();
    }

    public List<ProductDTO> index() {
        List<ProductDTO> productList = new ArrayList<>();
        try {
            Connection connection = databaseConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM products");

            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");

                ProductDTO productDTO = new ProductDTO(id, title, description);
                productList.add(productDTO);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productList;
    }

    public List<ProductDTO> show(String productId) {
        ProductDTO productDTO = null;
        List<ProductDTO> productList = new ArrayList<>();
        try {
            Connection connection = databaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from products as pr where pr.id = (?);");
            preparedStatement.setString(1, productId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next())
            {
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");

                productDTO = new ProductDTO(productId, title, description);
                productList.add(productDTO);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productList;
    }

    public ProductDTO store(ProductDTO productDTO) {
        try {
            productDTO.setId(UUID.randomUUID().toString());
            Connection connection = databaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into products (id, title, description) values (?, ?, ?)");
            preparedStatement.setString(1, productDTO.getId());
            preparedStatement.setString(2, productDTO.getTitle());
            preparedStatement.setString(3, productDTO.getDescription());
            preparedStatement.executeUpdate();
            new QrCodeGenerator(productDTO.getId());
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productDTO;
    }

    public ProductDTO update(String productId, ProductDTO productDTO) {
        try {
            productDTO.setId(productId);
            Connection connection = databaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE products SET title = ?, description = ? WHERE id = ?");
            preparedStatement.setString(3, productDTO.getId());
            preparedStatement.setString(1, productDTO.getTitle());
            preparedStatement.setString(2, productDTO.getDescription());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productDTO;
    }

    public String destroy(String productId) {
        try {
            Connection connection = databaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM products WHERE id = ?");
            preparedStatement.setString(1, productId);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return "ERROR";
        }

        return "DELETED";
    }

}
