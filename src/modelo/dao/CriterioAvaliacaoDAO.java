package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.pojo.CriterioAvaliacao;

public class CriterioAvaliacaoDAO {
	
	FactoryConnection factoryConnection = new FactoryConnection();
	/**
	 * Essa funcao consulta os critérios cadastrados na tbCriterioDeAvaliacao;
	 * @return rs esse método retorna um ResultSet com o id do criterio e a descrição do mesmo
	 * */
	public ArrayList<CriterioAvaliacao> consultarCriterios()
	{
		ArrayList<CriterioAvaliacao> criterios = new ArrayList<CriterioAvaliacao>();
		Connection conexao = factoryConnection.getConnection();
		String sql = "select * from tbCriterioDeAvaliacao";
		try 
		{
			PreparedStatement pstmt = conexao.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();	
			
			while(rs.next())
			{
				int idCriterio = rs.getInt("idCriterio");
				String descricao = rs.getString("descricao");
				
				CriterioAvaliacao criterio = new CriterioAvaliacao(idCriterio, descricao);
				criterios.add(criterio);
			}
			
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return criterios;
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

}
