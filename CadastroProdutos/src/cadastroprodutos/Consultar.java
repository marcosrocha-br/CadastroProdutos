package cadastroprodutos;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Marcos Rocha
 */
public class Consultar {

    public static void main(String[] args) throws SQLException, ClassNotFoundException{
        
        Scanner input = new Scanner(System.in);
        
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        
        String url = "jdbc:derby://localhost:1527/cadastroprodutos";
        String usuario = "root";
        String senha = "123";
        
        Connection conexao = DriverManager.getConnection(url, usuario, senha);
        
        PreparedStatement sql = conexao.prepareStatement("SELECT * FROM PRODUTOS");
        ResultSet resultado = sql.executeQuery();
        
        System.out.println("==========================================");
        System.out.println("NOME\t\tQUANTIDADE\tVALOR");
        System.out.println("==========================================");
        
        while(resultado.next()){
            String nome = resultado.getString("nome");
            int quantidade = resultado.getInt("quantidade");
            double valor = resultado.getDouble("valor");
            
            System.out.printf("%-10s\t%-5d\t\tR$ %-5.2f\n", nome, quantidade, valor);
        }
        
        System.out.println("");
        
    }
    
}
