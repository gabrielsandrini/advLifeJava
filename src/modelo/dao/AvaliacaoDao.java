package modelo.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.pojo.Avaliacao;

	/**
	 * Essa classe faz todas as operações do banco de dados referente a Avaliações das trilhas
	 * */
public class AvaliacaoDao {
	
	FactoryConnection factoryConnection = new FactoryConnection();
	
	/**
	 * Esse método insere uma avaliação no banco
	 * @param idTrilha id da trilha que está sendo avaliada
	 * @param login nickname do usuario que está avaliando a trilha, ou seja, logado no sistema
	 * @param avaliacao veotr de objetos do tipo Avaliaçao (para cada critério de avaliação cadastrados previamente na tabela 
	 * tbCriterio avaliação irá ser passado um objeto no vetor)
	 * */
	public void inserirAvaliacao(int idTrilha, String login, Avaliacao avaliacao[])
	{
		
			try {
				Connection conexao = factoryConnection.getConnection();
				
				//inicia uma transação
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
		       
		        //Fecha a conexão 
		        conexao.close(); 			 
			}
			catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	
	/**
	 * Essa classe consulta os critérios cadastrados na tbCriterioDeAvaliacao;
	 * @return rs esse método retorna um ResultSet com o id do criterio e a descrição do mesmo
	 * */
	public ResultSet consultarCriterios()
	{
		ResultSet rs = null;
		Connection conexao = factoryConnection.getConnection();
		String sql = "select * from tbCriterioDeAvaliacao";
		try 
		{
			PreparedStatement pstmt = conexao.prepareStatement(sql);
			rs = pstmt.executeQuery();			
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	/**
	 * Esse método consulta a quantidade de critérios de avaliação presentes na tabela tbCriterioDeAvaliacao
	 * @return quantidade quantidade de criterios cadastrados na tabela tbCriterioDeAvaliacao
	 * */
	public int consultarQuantidadeDeCriterios()
	{
		ResultSet rs = null;
		int quantidade = 0;
		Connection conexao = factoryConnection.getConnection();
		String sql = "select max(idCriterio) as quantidade from tbCriterioDeAvaliacao";
		try 
		{
			PreparedStatement pstmt = conexao.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				quantidade = rs.getInt("quantidade");
			}
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return quantidade;
	}
	
	/**
	 * Esse método consulta a descricao do criterio, segundo o id do mesmo
	 * @param idCriterio id do critério a ser consultado
	 * @return descricaocriterio 
	 * */
	public String consultarDescricaoCriterio(int idCriterio)
	{
		ResultSet rs = null;
		Connection conexao = factoryConnection.getConnection();
		String descricaoCriterio = "null";
		String sql = "select descricao from tbCriterioDeAvaliacao where idCriterio = ?";
		try 
		{
			PreparedStatement pstmt = conexao.prepareStatement(sql);
			pstmt.setInt(1, idCriterio);
			rs = pstmt.executeQuery();	
			while(rs.next())
			{
				descricaoCriterio = rs.getString("descricao");
			}
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return descricaoCriterio;
	}

	/**
	 * Esse método retrorna as avaliacoes que foram relizadas(idAvaliacao, nicknameUsuario, dataRealizacao), mas sem
	 *  seus valores(criterios, notas e comentários)
	 * @param idTrilha id da trilha a ter as avaliações consultadas
	 * @return rs ResultSet com o id da avaliacao, nickname do usuario que realizou a avaliacao, data de realização da avaliacao
	 * */
	public ResultSet consultarAvaliacoesRealizadas(int idTrilha) 
	{
		ResultSet rs = null;
		try {
				Connection conexao;
				conexao = factoryConnection.getConnection();

				//(idtrilha, nickname_usuario,idcriterio, data_realizacao)
				
				String sql =  "select * from tbAvaliacoesRealizadas " + 
						"where idTrilha = ? order by dataRealizacao";
				
				PreparedStatement pstmt = conexao.prepareStatement(sql);
				
				pstmt.setInt(1, idTrilha);
				
				rs = pstmt.executeQuery();
				
			} 
			
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return rs;
			
	}
	
	/**
	 * Esse método retorna os valores registrados em uma determinada avaliação (idCriterio, nota e comentário)
	 * @param idAvaliacao id da avaliação a ter os valores pesquisados
	 * @return rs ResultSet com o (idCriterio, nota e comentário)
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
