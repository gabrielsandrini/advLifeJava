package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import modelo.pojo.Usuario;

/**
 * Essa classe faz todas as operações do banco de dados referente a tbUsuario
 * */
public class UsuarioDAO {
	FactoryConnection factoryConnection = new FactoryConnection();
	
	/**
	 * Esse método faz a insercao de um novo usuario no banco, ou seja, faz o cadastro de um novo usuario
	 * @param usuario Objeto da classe Usuario
	 * */
	public boolean cadastrarUsuario(Usuario usuario) 
	{
			Connection conexao = factoryConnection.getConnection();
			boolean cadastroBemSucedido = false;
	       String sql = "insert into tbUsuario" +
	               " (nicknameUsuario, senha, nome, email)" +
	               " values (?,?,?,?)";

	       try 
	       {
	    	PreparedStatement pstmt = conexao.prepareStatement(sql);
			pstmt.setString(1, usuario.getNicknameUsuario());
			 pstmt.setString(2, usuario.getSenha());
		     pstmt.setString(3, usuario.getNome());
		     pstmt.setString(4, usuario.getEmail());

		     cadastroBemSucedido = pstmt.execute();
		     pstmt.close();
		     cadastroBemSucedido = true;
	       } 
	       catch (SQLException e) 
	       {
			// TODO Auto-generated catch block
			e.printStackTrace();
			cadastroBemSucedido = false;
	       }
	       return cadastroBemSucedido;
	      
	}
	
	
	/**
	 * Esse método deleta um usuario do Banco de dados
	 * @param nicknameUsuario nickname do usuario a ser deletado
	 * */
	public void deletarUsuario(String nicknameUsuario)  
	{
		try
		{
	       Connection conexao = factoryConnection.getConnection();
	       String sql = "DELETE FROM tbUsuario WHERE nicknameUsuario= ?";  
	       PreparedStatement pstmt = conexao.prepareStatement(sql);  
	       pstmt.setString(1, nicknameUsuario);  	       
	       pstmt.executeUpdate();
	       conexao.close();
		}
		catch(SQLException e)
		{
			 e.printStackTrace();
		}
	}
	
	/**
	 * Esse método deleta um usuario do Banco de dados
	 * @param nicknameUsuario nickname do usuario que está fazendo login
	 * @param senha senha inserida pelo usuario
	 * @return senhaELoginCorretos retorna true caso o nickname e a senha do usuario estiverem corretos,
	 *  caso contrário, retorna false
	 * */
	public boolean colsultaSenha(String nicknameUsuario, String senha)
	{
		ResultSet rs = null;
		String senhaELoginCorretos;
		boolean senhaECorreta = false;
		try
		{
	       Connection conexao = factoryConnection.getConnection();
	       String sql = "select senha from tbUsuario where nicknameUsuario = ?";  
	       PreparedStatement pstmt = conexao.prepareStatement(sql);  
	       pstmt.setString(1, nicknameUsuario);  	       
	       rs = (ResultSet) pstmt.executeQuery();
	       
	       while(rs.next())
	       {
	    	   senhaELoginCorretos = rs.getString("senha");
	    	   
	    	   if(senha.equals(""))
	    		  senhaECorreta = false;
	    	   else 
	    	   {
	    	   if(senhaELoginCorretos.equals(senha) )
	    		   senhaECorreta = true;
	    	   }
	       }
		}
		catch(SQLException e)
		{
			 e.printStackTrace();
		}
		
		return senhaECorreta;
	}
}
