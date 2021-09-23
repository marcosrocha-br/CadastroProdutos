package ControleEstoque;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

/**
 * @author Marcos Rocha
 */

public class Main {
    
    public static String Acao(){
        Scanner input = new Scanner(System.in);
        String resposta;
        
        System.out.println("\tSELECIONE UMA ACAO");
        System.out.println("(l) Listar\t(i) Inserir\n(a) Alterar\t(d) Deletar\n(s) Sair");
        System.out.printf("Acao: ");
        resposta = input.next();
        
        return resposta;
    }
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException{
    
        Scanner input = new Scanner(System.in);
        String R, nome;
        int quantidade, id;
        double valor;
        
        //CONEXÃO COM O BANCO DE DADOS DERBY
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        
        String url = "jdbc:derby://localhost:1527/controle-estoque";
        //String usuario;
        //String senha;
        
        //Connection conexao = DriverManager.getConnection(url, usuario, senha);
        PreparedStatement sql;
        ResultSet resultado;
        
        System.out.println("\tCONTROLE DE ESTOQUE\n");
        R = Acao();
        
        System.out.println("");
        while(!R.equals("s")){
            
            switch(R){
                case "l"://SELECT *
                    System.out.printf("\n%-2s\t%-15s\t%-15S\t%S\n","ID", "NOME", "QUANTIDADE", "VALOR");

                    sql = conexao.prepareStatement("SELECT * FROM PRODUTOS");
                    resultado = sql.executeQuery();

                    while(resultado.next()){
                        System.out.printf("%-2d\t%-15s\t%-15d\tR$ %.2f\n", resultado.getInt("id"),resultado.getString("nome"), resultado.getInt("quantidade"), resultado.getDouble("valor"));
                    }
                    break;

                case "i"://INSERT
                    input.nextLine();
                    sql = conexao.prepareStatement("INSERT INTO PRODUTOS (nome, quantidade, valor) values (?,?,?)");
                    System.out.println("INSERIR PRODUTO");
                    
                    System.out.print("NOME: ");
                    nome = input.nextLine();

                    System.out.print("Quantidade: ");
                    quantidade = input.nextInt();

                    System.out.print("Valor: R$ ");
                    valor = input.nextDouble();

                    sql.setString(1, nome);
                    sql.setInt(2, quantidade);
                    sql.setDouble(3, valor);
                    sql.execute();

                    break;

                case "a"://UPDATE
                    System.out.println("ALTERAR PRODUTO");
                    System.out.printf("\n%-2s\t%-15s\t%-15S\t%S\n","ID", "NOME", "QUANTIDADE", "VALOR");
                    sql = conexao.prepareStatement("SELECT * FROM PRODUTOS");
                    resultado = sql.executeQuery();

                    while(resultado.next()){
                        System.out.printf("%-2d\t%-15s\t%-15d\tR$ %.2f\n", resultado.getInt("id"),resultado.getString("nome"), resultado.getInt("quantidade"), resultado.getDouble("valor"));
                    }
                    
                    System.out.print("\nDIGITE A 'ID' DO PRODUTO: ");
                    id = input.nextInt();
                    
                    System.out.print("\nAlterar:\n\t(n)nome\t(q)quantidade\t(v)valor\nAcao: ");
                    R = input.next();
                    
                    input.nextLine();//Limpeza de buffer
                    switch(R){
                        case "n":
                            System.out.print("Nome: ");
                            nome = input.nextLine();
                            sql = conexao.prepareStatement("UPDATE PRODUTOS SET NOME = ? WHERE ID = "+id);
                            sql.setString(1, nome);
                            sql.execute();
                            break;
                        case "q":
                            System.out.print("Quantidade: ");
                            quantidade = input.nextInt();
                            sql = conexao.prepareStatement("UPDATE PRODUTOS SET QUANTIDADE = ? WHERE ID = "+id);
                            sql.setInt(1, quantidade);
                            sql.execute();
                            break;
                        case "v":
                            System.out.print("Valor: ");
                            valor = input.nextDouble();
                            sql = conexao.prepareStatement("UPDATE PRODUTOS SET VALOR = ? WHERE ID = "+id);
                            sql.setDouble(1, valor);
                            sql.execute();
                            break;
                        default:
                            System.out.println("Digite uma letra correspondente!");
                    }//END SWITCH  
                                        
                    break;

                case "d"://DELETE
                    System.out.println("DELETAR PRODUTO");
                    sql = conexao.prepareStatement("SELECT * FROM PRODUTOS");
                    resultado = sql.executeQuery();

                    while(resultado.next()){
                        System.out.printf("%d\t%s\t\t%d\t\tR$ %.2f\n", resultado.getInt("id"),resultado.getString("nome"), resultado.getInt("quantidade"), resultado.getDouble("valor"));
                    }
                    
                    System.out.print("\nDIGITE A 'ID' DO PRODUTO: ");
                    id = input.nextInt();
                    
                    sql = conexao.prepareStatement("DELETE FROM PRODUTOS WHERE ID = "+id);
                    sql.execute();
                    System.out.println("PRODUTO DELETADO!");
                    break;

                default:
                    System.out.println("Digite uma letra correspondente!");
                    
            }//END SWICTH
            
            System.out.println("\n|l|i|a|d|s|");
            System.out.print("Acao: ");
            R = input.next();
            
        }//END WHILE
        System.out.println("\t\tPrograma encerrado!\n");
    }
}
