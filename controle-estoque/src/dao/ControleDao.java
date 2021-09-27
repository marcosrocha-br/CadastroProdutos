package dao;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControleDao {
    
    private Connection conexao;
    private PreparedStatement sql;
    private ResultSet lista;
            
    private String url = "jdbc:derby://localhost:1527/controle-estoque";
    private String usuario = "controle";
    private String senha = "123";
    
    public ControleDao(){
        try{
            
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            this.conexao = DriverManager.getConnection(url, usuario, senha);
            
        }catch(ClassNotFoundException | SQLException e){
           System.out.println("Erro: " + e);
        }
        
    }
   
    public void getProdutos(){
        try{
            sql = conexao.prepareStatement("SELECT * FROM PRODUTOS");
            lista = sql.executeQuery();

            while(lista.next()){
                int id = lista.getInt("id");
                String nome = lista.getString("nome");
                int quantidade = lista.getInt("quantidade");
                double valor = lista.getDouble("valor");

                System.out.printf("%-2d\t%-15s\t%-15d\tR$ %.2f\n",id, nome, quantidade, valor);
            }                

        }catch(SQLException e){
            System.out.println("Erro: " + e);
        }  
    }

    public void setInserir(String nome, int quantidade, double valor){
        try{
            sql = conexao.prepareStatement("INSERT INTO PRODUTOS (NOME, QUANTIDADE, VALOR) VALUES (?, ?, ?)");
            sql.setString(1, nome);
            sql.setInt(2, quantidade);
            sql.setDouble(3, valor);
            sql.execute();

            System.out.println("Produto cadastrado!");

        }catch(SQLException e){
            System.out.println("Erro: " + e);
        }  
    }

    public void setNome(int id, String nome){
        try{
            sql = conexao.prepareStatement("UPDATE PRODUTOS SET NOME = ? WHERE ID = ?");
            sql.setString(1, nome);
            sql.setInt(2, id);
            sql.execute();

        }catch(SQLException e){
            System.out.println("Erro: " + e);
        }
    }

    public void setQuantidade(int id, int quantidade){
        try{
            sql = conexao.prepareStatement("UPDATE PRODUTOS SET QUANTIDADE = ? WHERE ID = ?");
            sql.setInt(1, quantidade);
            sql.setInt(2, id);
            sql.execute();

        }catch(SQLException e){
            System.out.println("Erro: " + e);
        }
    }

    public void setValor(int id, double valor){
        try{
            sql = conexao.prepareStatement("UPDATE PRODUTOS SET VALOR = ? WHERE ID = ?");
            sql.setDouble(1, valor);
            sql.setInt(2, id);
            sql.execute();

        }catch(SQLException e){
            System.out.println("Erro: " + e);
        }
    }

    public void setDelete(int id){
        try{
            sql = conexao.prepareStatement("DELETE FROM PRODUTOS WHERE ID = ?");
            sql.setInt(1, id);
            sql.execute();

        }catch(SQLException e){
            System.out.println("Erro: " + e);
        }
    }
    
}
