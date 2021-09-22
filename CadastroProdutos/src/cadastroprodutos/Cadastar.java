package cadastroprodutos;

import java.util.Scanner;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Cadastar {
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException{
    
        String nome;
        int quantidade;
        double valor;
        
        Scanner input = new Scanner(System.in);
        
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        
        String url = "jdbc:derby://localhost:1527/cadastroprodutos";
        String usuario = "root";
        String senha = "123";
        
        Connection conexao = DriverManager.getConnection(url, usuario, senha);
        
        System.out.println("\tCADASTRO DE PRODUTOS");
        
        System.out.printf("NOME: ");
        nome = input.nextLine();
        
        System.out.printf("QUANTIDADE: ");
        quantidade = input.nextInt();
        
        System.out.printf("VALOR TOTAL: R$ ");
        valor = input.nextDouble();       
        
        PreparedStatement sql = conexao.prepareStatement("INSERT INTO PRODUTOS VALUES(?, ?, ?)");
        sql.setString(1, nome);
        sql.setInt(2, quantidade);
        sql.setDouble(3, valor);
        sql.execute();
        
        System.out.printf("\nProduto cadastrado!\n");
        
    }
    
}
