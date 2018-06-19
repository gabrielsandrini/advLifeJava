package modelo.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.pojo.Avaliacao;

	/**
	 * Essa classe faz todas as opera��es do banco de dados referente a Avalia��es das trilhas
	 * */
public class AvaliacaoDao {
	
	FactoryConnection factoryConnection = new FactoryConnection();
	
	/**
	 * Esse m�todo insere uma avalia��o no banco
	 * @param idTrilha id da trilha que est� sendo avaliada
	 * @param login nickname do usuario que est� avaliando a trilha, ou seja, logado no sistema
	 * @param avaliacao veotr de objetos do tipo Avalia�ao (para cada crit�rio de avalia��o cadastrados previamente na tabela 
	 * tbCriterio avalia��o ir� ser passado um objeto no vetor)
	 * */
	public void inserirAvaliacao(int idTrilha, String login, Avaliacao avaliacao[])
	{
		
			try {
				Connection conexao = factoryConnection.getConnection();
				
				//inicia uma transa��o
				conexao.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
				
				String sql = "insert into tbAvaliacoesRealizadas (idTrilha, nicknameUsuario, dataRealizacao) " + 
						"values(?,?,?)";
				
		        // Adicionei Statement.RETURN_GENERATED_KEYS ao prepareStatement assim ele retorna a id (se ela for auto_increment)
		        PreparedStatement pstmt = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

		        pstmt.setInt(1,idTrilha); 
		        pstmt.setString(2, login);
		        pstmt.setDate(3, Date.valueOf(avaliacao[0].getDataDeRealizacao()) );

		        pstmt.execute();

		        // Recupera a id
		        ResultSet rs = pstmt.getGeneratedKeys();
		        int id = 0;
		        if(rs.next()){
		            id = rs.getInt(1);
		        }
		        
		        sql = "insert into tbAvaliacaovalores (idAvaliacao, idCriterio, nota, comentario) " + 
		        		"values(?,?,?,?)";
		        for (int i = 0; i < avaliacao.length; i++)
				{
		        	pstmt = conexao.prepareStatement(sql);
		        	pstmt.setInt(1, id);
		        	pstmt.setInt(2, avaliacao[i].getIdCriterio());
		        	pstmt.setInt(3, avaliacao[i].getNota());
		        	pstmt.setString(4, avaliacao[i].getComentario());
		        	pstmt.execute();
				}
		       
		        //Fecha a conex�o 
		        conexao.close(); 			 
			}
			catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	
	/**
	 * Esse m�todo retorna os valores registrados em uma determinada avalia��o (idCriterio, nota e coment�rio)
	 * @param idAvaliacao id da avalia��o a ter os valores pesquisados
	 * @return rs ResultSet com o (idCriterio, nota e coment�rio)
	 * */
	public ResultSet consultarValoresDasAvaliacoes(int idAvaliacao)
	{
		ResultSet rs = null;
		Connection conexao = factoryConnection.getConnection();
		String sql = "select * from tbAvaliacaoValores where idAvaliacao = ? order by idCriterio asc"; 
		try
		{
			PreparedStatement pstmt = conexao.prepareStatement(sql);
			pstmt.setInt(1, idAvaliacao);
			rs = pstmt.executeQuery();
		} 
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	    
		return rs;
	}
}
