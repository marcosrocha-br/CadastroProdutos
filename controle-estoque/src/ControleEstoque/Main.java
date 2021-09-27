package ControleEstoque;

import java.sql.SQLException;
import java.util.Scanner;
import dao.ControleDao;
import java.util.Scanner;

/**
 *
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
        ControleDao dao = new ControleDao();
       
        String R, nome, conteudo;
        int id, quantidade;
        double valor;
        
        System.out.println("\tCONTROLE DE ESTOQUE\n");
        R = Acao();
        
        while(!R.equals("s")){
            switch(R){
                case "l"://SELECT
                    System.out.printf("\n%-2s\t%-15s\t%-15S\t%S\n","ID", "NOME", "QUANTIDADE", "VALOR");
                    dao.getProdutos();
                    break;
                case "i"://INSERT
                    input.nextLine();//LIMPEZA DE BUFFER
                    
                    System.out.println("INSERIR PRODUTO");
                    
                    System.out.print("NOME: ");
                    nome = input.nextLine();

                    System.out.print("Quantidade: ");
                    quantidade = input.nextInt();

                    System.out.print("Valor: R$ ");
                    valor = input.nextDouble();
                    
                    dao.setInserir(nome, quantidade, valor);
                    
                    break;
                case "a"://UPDATE
                    System.out.print("ID do produto: ");
                    id = input.nextInt();
                    System.out.print("\nAlterar:\n\t(n)nome\t(q)quantidade\t(v)valor\nAcao: ");
                    conteudo = input.next();
                    
                    switch(conteudo){
                        case "n":
                            input.nextLine();//Limpeza de buffer
                            
                            System.out.print("Nome: ");
                            nome = input.nextLine();
                            dao.setNome(id, nome);
                            break;
                        case "q":
                            input.nextLine();//Limpeza de buffer
                            
                            System.out.print("Quantidade: ");
                            quantidade = input.nextInt();
                            dao.setQuantidade(id, quantidade);
                            break;
                        case "v":
                            input.nextLine();//Limpeza de buffer
                            
                            System.out.print("Valor: ");
                            valor = input.nextDouble();
                            dao.setValor(id, valor);
                            break;
                        default:
                            System.out.println("Digite uma letra correspondente!");
                    }//END SWITCH 
                    
                    break;
                case "d"://DELETE
                    System.out.println("DELETAR PRODUTO");
                    System.out.print("ID do produto: ");
                    id = input.nextInt();
                    dao.setDelete(id);
                    break;
                default:
                    System.out.println("Digite uma letra correspondente!");
            }//END SWITCH
            
            System.out.println("\n|l|i|a|d|s|");
            System.out.print("Acao: ");
            R = input.next();
            
        }//END WHILE
        
        System.out.println("\t\tPrograma encerrado!\n");
        
    }
}
