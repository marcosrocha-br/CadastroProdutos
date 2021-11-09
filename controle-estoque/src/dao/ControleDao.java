package dao;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControleDao {
    
    private Connection conexao;
            
    private String url = "jdbc:postgresql://localhost:5432/controle_estoque";
    private String usuario = "controle";
    private String senha = "123";
    
    public ControleDao(){
        try{
            
            Class.forName("org.postgresql.Driver");
            this.conexao = DriverManager.getConnection(url, usuario, senha);
            
        }catch(ClassNotFoundException | SQLException e){
           System.out.println("Erro: " + e);
        }
        
    }
   
    public void getProdutos(){
        try{
            PreparedStatement sql = conexao.prepareStatement("SELECT * FROM PRODUTOS");
            ResultSet lista = sql.executeQuery();
            

            while(lista.next()){
                int id = lista.getInt("id");
                String nome = lista.getString("nome");
                int quantidade = lista.getInt("quantidade");
                float valorUnitario = lista.getFloat("valor");
                float valorTotal = lista.getFloat("valorTotal");

                System.out.printf("%-2d\t%-15s\t%-15d\tR$ %.2f\t\tR$ %.2f\n",id, nome, quantidade, valorUnitario, valorTotal);
            }                

        }catch(SQLException e){
            System.out.println("Erro: " + e);
        }  
    }

    public void setInserir(String nome, int quantidade, float valor){
        try{
            PreparedStatement sql = conexao.prepareStatement("INSERT INTO PRODUTOS (NOME, QUANTIDADE, VALOR) VALUES (?, ?, ?)");
            PreparedStatement sqlupdate = conexao.prepareStatement("update produtos set valorTotal = valor*quantidade");
            sql.setString(1, nome);
            sql.setInt(2, quantidade);
            sql.setFloat(3, valor);
            sql.execute();
            sqlupdate.execute();

            System.out.println("Produto cadastrado!");

        }catch(SQLException e){
            System.out.println("Erro: " + e);
        }  
    }

    public void setNome(int id, String nome){
        try{
            PreparedStatement sql = conexao.prepareStatement("UPDATE PRODUTOS SET NOME = ? WHERE ID = ?");
            sql.setString(1, nome);
            sql.setInt(2, id);
            sql.execute();

        }catch(SQLException e){
            System.out.println("Erro: " + e);
        }
    }

    public void setQuantidade(int id, int quantidade){
        try{
            PreparedStatement sql = conexao.prepareStatement("UPDATE PRODUTOS SET QUANTIDADE = ? WHERE ID = ?");
            sql.setInt(1, quantidade);
            sql.setInt(2, id);
            sql.execute();

        }catch(SQLException e){
            System.out.println("Erro: " + e);
        }
    }

    public void setValor(int id, float valor){
        try{
            PreparedStatement sql = conexao.prepareStatement("UPDATE PRODUTOS SET VALOR = ? WHERE ID = ?");
            sql.setFloat(1, valor);
            sql.setInt(2, id);
            sql.execute();

        }catch(SQLException e){
            System.out.println("Erro: " + e);
        }
    }

    public void setDelete(int id){
        try{
            PreparedStatement sql = conexao.prepareStatement("DELETE FROM PRODUTOS WHERE ID = ?");
            sql.setInt(1, id);
            sql.execute();

        }catch(SQLException e){
            System.out.println("Erro: " + e);
        }
    }
    
}
