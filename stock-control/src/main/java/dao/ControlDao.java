package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbcConection.SingleConnection;
import model.Product;

public class ControlDao {

	private Connection connection;

	public ControlDao() {
		connection = SingleConnection.getConnection();
	}

	public List<Product> selectAll() {

		List<Product> list = new ArrayList<>();
		String sql = "select * from produto";

		try {

			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet r = statement.executeQuery();

			while (r.next()) {

				Product product = new Product();
				product.setName(r.getString("nome"));
				product.setPrice(r.getDouble("preco"));
				product.setQuantity(r.getInt("quantidade"));
				list.add(product);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public void insert(Product product) {

		String sql = "insert into produto (nome, preco, quantidade, total) values (?,?,?,?)";

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, product.getName());
			statement.setDouble(2, product.getPrice());
			statement.setInt(3, product.getQuantity());
			statement.setDouble(4, product.getTotal());

			statement.execute();
			connection.commit();

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

	}

	public void updateName(Product product) {

		String sql = "update produto set nome = '" + product.getName();
		sql += "' where id = " + product.getId();

		try {
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.execute();
			connection.commit();

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

	}

	public void updatePrice(Product product) {

		String sql = "update produto set preco = " + product.getPrice();
		sql += " where id = " + product.getId();

		try {
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.execute();
			connection.commit();

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	public void updateQuantity(Product product) {

		String sql = "update produto set quantidade = " + product.getQuantity();
		sql += " where id = " + product.getId();

		try {
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.execute();
			connection.commit();

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	public void delete(Product product) {

		String sql = "delete from produto where id = " + product.getId();

		try {
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.execute();
			connection.commit();

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

	}

}
