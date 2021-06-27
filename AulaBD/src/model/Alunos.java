package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Alunos {

	private String nome;
	private double media;
	private int id;
	
	public Alunos(String nome, double media) {
		this.nome=nome;
		this.media=media;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getMedia() {
		return media;
	}
	public void setMedia(double media) {
		this.media = media;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	
	public void incluir(Connection conn) {
        String sqlInsert = "INSERT INTO alunos(nome, media) values(?, ?)";

        try(PreparedStatement stm = conn.prepareStatement(sqlInsert) ){
            stm.setString(1, getNome());
            stm.setDouble(2, getMedia());
            stm.execute();
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
	
	public void excluir(Connection conn)
	{
		String sqlDelete = "DELETE FROM alunos WHERE codigo = ?";
		try(PreparedStatement stm = conn.prepareStatement(sqlDelete)){
			  stm.setInt(1, getId());
	            
	            stm.execute();
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
    
	public ArrayList <Alunos> imprimirAlunos(Connection conn) {
		
		String sqlSelect = "SELECT * FROM alunos";
		ArrayList <Alunos> lista= new ArrayList<>();
		try(PreparedStatement stm = conn.prepareStatement(sqlSelect); 
				ResultSet rs = stm.executeQuery();){
			
			while(rs.next())
			{
				Alunos alunos = new Alunos(nome, media);
				alunos.setId(rs.getInt("codigo"));
				alunos.setNome(rs.getString("nome"));
				alunos.setMedia(rs.getDouble("media"));
				lista.add(alunos);
			}
			System.out.println("lista de alunos: ");
			for(Alunos alunos : lista)
			 {
				 
				 System.out.println(" id:" + alunos.getId()+"    nome:"+ alunos.getNome()+"     media:" + alunos.getMedia());
			 }
			 System.out.println("");
	            }
		
		 catch(Exception e) {
	            e.printStackTrace();
		 }
	       return lista;
	}

	public void imprimirAluno(Connection conn) {
		String sqlSelect = "SELECT * FROM alunos WHERE codigo = ?";
		try(PreparedStatement stm=conn.prepareStatement(sqlSelect);){
			stm.setInt(1,getId());
			try(ResultSet rs = stm.executeQuery();){
				if(rs.next()) {
					this.setNome(rs.getString(2));
					this.setMedia(rs.getDouble(3));
				}
			}
			
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		catch (SQLException e1) {
			System.out.print(e1.getStackTrace());;
		}
	}

	public void alterar(Connection conn) {
		
		String sqlAterar = "UPDATE alunos SET nome =?, media= ? WHERE codigo = ?";
		try(PreparedStatement stm = conn.prepareStatement(sqlAterar)){
			stm.setString(1, getNome() );
			stm.setDouble(2, getMedia() );
			stm.setInt(3, getId() );
	            
	            stm.execute();
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
