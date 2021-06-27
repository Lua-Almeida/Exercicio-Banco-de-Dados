package teste;

import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import dao.ConnectionFactory;

import model.Alunos;

public class TesteAlunos {

	public static void main(String[] args) {
		Connection conn = null;
		String nome = null, novoNome;
        int id;
        double media=0, novaMedia;
        int op;
        

        try {
            ConnectionFactory bd = new ConnectionFactory();

            conn = bd.conectar();

            conn.setAutoCommit(false);
            

            Alunos aluno = new Alunos(nome, media);
            
        	do 
    		{
    			op = Integer.parseInt(JOptionPane.showInputDialog("o que deseja fazer:"
    					+ "\n 1 - inserir"
    					+ "\n 2 - excluir"
    					+ "\n 3 - Imprimir todos alunos"
    					+ "\n 4 - Imprimir um aluno "
    					+ "\n 5 - alterar "
    					+ "\n 6 - sair "));
    		
    		if(op==1)
    		{
    			
    			
    			nome=(JOptionPane.showInputDialog("digite o nome:"));
    			media=Double.parseDouble(JOptionPane.showInputDialog("digite a media do aluno "));
    			
    			aluno = new Alunos(nome,media);
                aluno.setNome(nome);
                aluno.setMedia(media);

                aluno.incluir(conn);
                conn.commit();
    		}
    		
    		if(op==2)
    		{
    			id = Integer.parseInt(JOptionPane.showInputDialog("digite o codigo do aluno que deseja excluir"));
    			aluno.setId(id);
    			aluno.excluir(conn);
    			conn.commit();
    		}
    		
    		if(op==3)
    		{
    			 aluno.imprimirAlunos(conn);
    		}
    		
    		if(op==4)
    		{
    			id = Integer.parseInt(JOptionPane.showInputDialog("digite o codigo do aluno que deseja exibir: "));
    			aluno.setId(id);
    			aluno.imprimirAluno(conn);
    			
    			System.out.println("Id:"+id+"    nome:"+ aluno.getNome()+"     Media:" + aluno.getMedia());
    			System.out.println(" ");
    		}
    		
    		if(op==5)
    		{
    			id = Integer.parseInt(JOptionPane.showInputDialog("digite o codigo do aluno que deseja alterar: "));
    			novoNome = (JOptionPane.showInputDialog("digite o novo nome: "));
    			novaMedia =Double.parseDouble(JOptionPane.showInputDialog("digite a nova media "));
    			
    			aluno.setNome(novoNome);
                aluno.setMedia(novaMedia);
    			aluno.setId(id);
    			aluno.alterar(conn);
    			
    			conn.commit();
    			
    		}
    		
    		
    		
    		}
    		while(op!=6);

        }
        catch(Exception e) {
            e.printStackTrace();
            try {
                conn.rollback();
            }
            catch(SQLException ex) {
                System.out.print(ex.getStackTrace());
            }
        }

	}

}
