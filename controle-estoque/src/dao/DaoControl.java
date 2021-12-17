package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoControl {

	private Connection connection;

	private String url = "jdbc:postgresql://localhost:5432/controle_estoque";
	private String user = "controle";
	private String password = "123";

	public DaoControl() {
		try {

			Class.forName("org.postgresql.Driver");
			this.connection = DriverManager.getConnection(url, user, password);

		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Erro: " + e);
		}

	}

	public void getProdutos() {
		try {
			PreparedStatement sql = connection.prepareStatement("SELECT * FROM PRODUTOS ORDER BY ID ASC");
			ResultSet list = sql.executeQuery();

			while (list.next()) {
				int id = list.getInt("id");
				String name = list.getString("nome");
				int amount = list.getInt("quantidade");
				float uniPrice = list.getFloat("valor");
				float fullPrice = list.getFloat("valorTotal");

				System.out.printf("%-2d\t%-15s\t%-15d\tR$ %.2f\t\tR$ %.2f\n", id, name, amount, uniPrice, fullPrice);
			}

		} catch (SQLException e) {
			System.out.println("Erro: " + e);
		}
	}

	public void setInserir(String name, int amount, float price) {
		try {
			PreparedStatement sql = connection
					.prepareStatement("INSERT INTO PRODUTOS (NOME, QUANTIDADE, VALOR) VALUES (?, ?, ?)");
			PreparedStatement sqlupdate = connection
					.prepareStatement("update produtos set valorTotal = valor*quantidade");
			sql.setString(1, name);
			sql.setInt(2, amount);
			sql.setFloat(3, price);
			sql.execute();
			sqlupdate.execute();

			System.out.println("Registered product!");

		} catch (SQLException e) {
			System.out.println("Erro: " + e);
		}
	}

	public void setNome(int id, String name) {
		try {
			PreparedStatement sql = connection.prepareStatement("UPDATE PRODUTOS SET NOME = ? WHERE ID = ?");
			sql.setString(1, name);
			sql.setInt(2, id);
			sql.execute();

		} catch (SQLException e) {
			System.out.println("Erro: " + e);
		}
	}

	public void setQuantidade(int id, int amount) {
		try {
			PreparedStatement sql = connection.prepareStatement("UPDATE PRODUTOS SET QUANTIDADE = ? WHERE ID = ?");
			sql.setInt(1, amount);
			sql.setInt(2, id);
			sql.execute();

		} catch (SQLException e) {
			System.out.println("Erro: " + e);
		}
	}

	public void setValor(int id, float price) {
		try {
			PreparedStatement sql = connection.prepareStatement("UPDATE PRODUTOS SET VALOR = ? WHERE ID = ?");
			sql.setFloat(1, price);
			sql.setInt(2, id);
			sql.execute();

		} catch (SQLException e) {
			System.out.println("Erro: " + e);
		}
	}

	public void setDelete(int id) {
		try {
			PreparedStatement sql = connection.prepareStatement("DELETE FROM PRODUTOS WHERE ID = ?");
			sql.setInt(1, id);
			sql.execute();

		} catch (SQLException e) {
			System.out.println("Erro: " + e);
		}
	}

}
