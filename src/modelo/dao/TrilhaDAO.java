package modelo.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import modelo.pojo.Trilha;

/**
 * Essa classe faz todas as operações do banco de dados referente as Trilhas
 * */
public class TrilhaDAO {
	
	FactoryConnection factoryConnection = new FactoryConnection();
	/**
	 * Esse método resgistra uma trilha no banco de dados
	 * @param trilha objeto da classe Trilha
	 * */
	public boolean registrarTrilha(Trilha trilha) {
		
		try {
			Connection conexao = factoryConnection.getConnection();
			String sql = "insert into tbTrilha(apelido, obstaculos, distancia, idMata, dataGravacao, nicknameUsuario)" + 
					"		values(?,?,?,?,?,?);";
			
			PreparedStatement pstmt = conexao.prepareStatement(sql);
			pstmt.setString(1, trilha.getApelido());
			pstmt.setString(2, trilha.getOsbtaculos());
			pstmt.setDouble(3, trilha.getDistancia());
			pstmt.setLong(4, trilha.getIdMata());
			pstmt.setDate(5, Date.valueOf(trilha.getDataGravacao()) );
			pstmt.setString(6, trilha.getNicknameUsuario() );
			pstmt.execute();
			pstmt.close();
			conexao.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * Esse método consulta todos os atributos de uma trilha pelo id da mesma
	 * @param idTrilha id da trilha a ser consultada
	 * @return ResultSet com as informações da trilha consultada
	 * */
	public ResultSet consultarTrilhaPorID(int idTrilha) {
		ResultSet rs = null;
		
		
		try {
			Connection conexao;
			conexao = factoryConnection.getConnection();

			String sql =  "select * from tbTrilha " + 
						"where idTrilha = ?";
			PreparedStatement pstmt;
			
			pstmt = conexao.prepareStatement(sql);
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
	 * Esse método busca uma trilha conforme os possiveis atributos que o usuario possa passar, sendo que quando os atributos são
	 * nulos eles apresentam valor -1 ou "null"
	 * @param apelido O apelido se refere ao nome dado a trilha pelo usuario que cadadtrou essa trilha
	 * @param distancia Distancia da trilha
	 * @param idMata Id da mata, presente na tbDescricaoMAta
	 * @param dificuldade	dificuldade da trilha, cadastrada pelo usuario que a gravou
	 * @param nicknameUsuario nickname do usuario que gravou a trilha
	 * @param idLocomocao id do meio de locomocao, presente na tbTipoDeLocomocao
	 * @return ResultSet com todos os atributos das trilhas que condizem com a pesquisa
	 * */
	public ArrayList<Trilha> buscarTrilha(String apelido, double distancia, int idMata, int dificuldade, String nicknameUsuario,
			int idLocomocao) {
		/*
		 * Primeiramente, peço desculpas pelo código feio e as gambiarras presentes nessa função, mas não vou refatorar não,
		 * já tá dificil de fazer, e não to usando git.
		 * Vamos a algumas considerações importantes para entender a gambiarra:
		 * todos os parametros que tenham valores null, são passsados como -1.
		 * nickname nulo = "null"
		 * A primeira sequencia de IF arruma a String sql para criar o PreparedStatement
		 * A segunda sequencia de IF faz pstmt.setString();
		 * */
		ResultSet rs = null;
		ArrayList<Trilha> trilhas = new ArrayList<Trilha>();
		Connection conexao = factoryConnection.getConnection();
		String sql =  "select * from tbTrilha where 1=1 ";
		//ifs para ver se o campo é "nulo" (nulo = -1) para criar o PreparedStatement
		if( (apelido.equalsIgnoreCase("null")) == false)
		{
			sql = sql + "and apelido like ? ";
		}
		if(distancia != -1) 
		{
			sql = sql + "and distancia = ? ";
		}
		if(dificuldade != -1) 
		{
			sql = sql + "and dificuldade = ? ";
		}
		if(idMata != -1)
		{
			sql = sql + "and idMata = ? ";
		}
		if( (nicknameUsuario.equalsIgnoreCase("null")) == false)
		{
			sql = sql + "and nicknameUsuario = ? ";
		}
		if(idLocomocao != -1) 
		{
			sql = sql + " and idTrilha = (select idTrilha from tbTrilhaLocomocao where idLocomocao = ?) ";
		}
		try 
		{
			PreparedStatement pstmt = conexao.prepareStatement(sql);
			int i = 1;
			
			//ifs para ver se o campo é "nulo" (nulo = -1) para fazer setString
			if( (apelido.equalsIgnoreCase("null")) == false)
			{
				pstmt.setString(i, ("%"+apelido+"%") );
				i++; 
			}
			if(distancia != -1)
			{
				pstmt.setDouble(i, distancia);
				i++;				
			}
			if(dificuldade != -1)  
			{
				pstmt.setInt(i, dificuldade);
				i++;
			}
			if(idMata != -1)
			{
				pstmt.setInt(i, idMata);
				i++;
			}
			if( (nicknameUsuario.equalsIgnoreCase("null")) == false)
			{
				pstmt.setString(i, nicknameUsuario);
				i++;
			}
			if(idLocomocao != -1) 
			{
				pstmt.setInt(i, idLocomocao);
				i++;
			}
			rs = pstmt.executeQuery();
			
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
			System.out.println(sql);
		}
		
			try {
				while(rs.next()) 
				{
					//int resultidTrilha = rs.getInt("idTrilha");
					String resultapelido = rs.getString("apelido");
					String resultObstaculos = rs.getString("obstaculos");
					double resulTdistancia = rs.getDouble("distancia");
					int ResultIdMata = rs.getInt("idMata");
					String resultNicknameUsuario = rs.getString("nicknameUsuario");
					//LocalDate resultDataGravacao =  rs.getDate("dataGravacao").toLocalDate();
					Trilha trilha = new Trilha(resultapelido, resultObstaculos, resulTdistancia, ResultIdMata, resultNicknameUsuario);
					trilhas.add(trilha);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return trilhas;
	}

	/**
	 * Esse método consulta as trilhas que foram gravadas por um determinado usuario
	 * @param nicknameUsuario nickname do Usuario para consulta
	 * @return ResultSet com a informação de todas as trilhas gravadas pelo usuario em questão
	 * */
	public ResultSet consultarTrilhasGravadas(String nicknameUsuario) 
	{
		Connection conexao = factoryConnection.getConnection();
		ResultSet rs = null;
		String sql = "select * from tbTrilha where nicknameUsuario = ?";
		try 
		{
			PreparedStatement pstmt = conexao.prepareStatement(sql);
			pstmt.setString(1, nicknameUsuario);
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
	 * Esse método consulta a descrição de um meio de locomoção segundo o id do mesmo
	 * @param idLocomocao id do meio de locomoção a ter a descricao consultada
	 * @return retorna uma String com a descrição do critério
	 * */
	public String consultarDescricaoLocomocao(int idLocomocao)
	{
		Connection conexao = factoryConnection.getConnection();
		ResultSet rs = null;
		String descricao = null;
		String sql = "select descricao from tbTipodelocomocao where idLocomocao = ?";
		try 
		{
			PreparedStatement pstmt = conexao.prepareStatement(sql);
			pstmt.setInt(1, idLocomocao);
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				descricao = rs.getString("descricao");
			}
		} 
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return descricao;
	}

	public ResultSet consultarLocomocao()
	{
		Connection conexao = factoryConnection.getConnection();
		ResultSet rs = null;
		String sql = "select * from tbTipodelocomocao";
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
	 * Esse método consulta a descrição da mata que se refere ao id oferecido
	 * @param idMata id da mata que terá a descricao consultada
	 * @return retorna uma String com a descrição da mata
	 * */
	public String consultarDescricaoMata(int idMata)
	{
		Connection conexao = factoryConnection.getConnection();
		ResultSet rs = null;
		String descricao = null;
		String sql = "select descricao from tbDescricaoMata where idMata = ?";
		try 
		{
			PreparedStatement pstmt = conexao.prepareStatement(sql);
			pstmt.setInt(1, idMata);
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				descricao = rs.getString("descricao");
			}
		} 
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return descricao;
	}
	
	/**
	 * Esse método consulta as trilhas utilizadas/consumidas por um usuario especifico
	 * @param nicknameUsuario nickname do usuario a ter seu histórico de trilhas realizadas consultado
	 * @return retorna um ResultSet com todas as informações das trilhas presentes no histórico e a data 
	 * em que elas foram realizadas
	 * */
	public ResultSet consultarTrilhasUtilizadas(String nicknameUsuario) 
	{
		Connection conexao = factoryConnection.getConnection();
		ResultSet rs = null;
		String sql = "select tbUsuarioRealizaTrilha.dataRealizacao, tbTrilha.* " + 
				"    from tbUsuarioRealizaTrilha, tbTrilha " + 
				"    where tbUsuarioRealizaTrilha.nicknameUsuario = ? and tbUsuarioRealizaTrilha.idTrilha = tbTrilha.idTrilha";
		try 
		{
			PreparedStatement pstmt = conexao.prepareStatement(sql);
			pstmt.setString(1, nicknameUsuario);
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
	 * Esse método é chamado para gravar no banco o histórico de trilhas utilizadas/consumidas por um usuario
	 * @param nicknameUsuario nickname do usuario que estará realizou a trilha
	 * @param idTrilha id da trilha que foi realizada
	 * @param dataRealizacao data em que a trilha foi realizada
	 * */
	public void realizarTrilha(String nicknameUsuario, int idTrilha, LocalDate dataRealizacao)
	{
		String sql = "insert into usuarioRealizaTrilha (nicknameUsuario, idTrilha, dataRealizacao)" + 
					" values(?,?,?);";
		Connection conexao = factoryConnection.getConnection();
		
		try 
		{
			PreparedStatement pstmt = conexao.prepareStatement(sql);
			pstmt.setString(1, nicknameUsuario);
			pstmt.setInt(1, idTrilha);
			pstmt.setDate(3, Date.valueOf(dataRealizacao) );
			pstmt.execute();
			conexao.close();
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * Esse método é chamado quando se deseja saber a quantidade de meios de locomoção existentes
	 @return quantidade de meios de locomocao registrados  
	 * */
	public int consultarQtdMeiosLocomocao()
	{
		ResultSet rs = null;
		int quantidade = 0;
		Connection conexao = factoryConnection.getConnection();
		String sql = "select max(idLocomocao) as quantidade from tbTipodelocomocao";
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

}
