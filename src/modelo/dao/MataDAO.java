package modelo.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import modelo.pojo.Mata;

public class MataDAO {
	
	FactoryConnection factoryConnection = new FactoryConnection();
	
	public ArrayList<Mata> consultarMatas()
	{
		ArrayList<Mata> matas = new ArrayList<Mata>();
		Connection con = (Connection) factoryConnection.getConnection();
		String sql = "select * from tbDescricaoMata";
		Mata mata;
		int id;
		String descricao;
		try {
			PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				id = rs.getInt("idMata");
				descricao = rs.getString("descricao");
				 mata = new Mata(id, descricao);
				 
				 matas.add(mata);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
		return matas;
	}
}
