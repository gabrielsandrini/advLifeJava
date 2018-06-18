package controle;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;

import modelo.dao.MataDAO;
import modelo.dao.TrilhaDAO;
import modelo.pojo.Mata;
import modelo.pojo.Trilha;

@ManagedBean
public class TrilhaBean {
		
		private String apelido;
		private String osbtaculos;
		private LocalDate dataGravacao;
		private int idTrilha;
		private double distancia;
		private int idMata;
		private String nicknameUsuario;
		private TrilhaDAO trilhaDao;
		private MataDAO mataDao;
		private ArrayList<Mata> matas;
		
	public TrilhaBean() 
	{
		trilhaDao = new TrilhaDAO();
		mataDao = new MataDAO();
		setMatas(mataDao.consultarMatas());
	}
	
	public String cadastrar()
	{
		Trilha trilha = new Trilha(apelido, osbtaculos, distancia, idMata, nicknameUsuario);
		System.out.println("Cadastrando...");
		boolean sucefull = trilhaDao.registrarTrilha(trilha);
		System.out.println(sucefull);
		if(sucefull)
			return "RespostaCadastroTrilha.xhtml";
		return "";
	}
	
	public String consultar()
	{
		ArrayList<Trilha> trilhas = null;
		System.out.println("Consultando...");
		System.out.println(apelido + " " + distancia + " " + idMata + " " + nicknameUsuario);
		double distConsulta;
		int consultaIdMata;
		
		if(distancia == 0.0)
			distConsulta = -1;
		else
			distConsulta = distancia;
		
		if(idMata == 0)
			consultaIdMata = -1;
		else
			consultaIdMata = idMata;
		
		System.out.println(apelido + " " + distConsulta + " " + consultaIdMata + " " + nicknameUsuario);
		trilhas= trilhaDao.buscarTrilha(apelido, distancia, consultaIdMata, -1, nicknameUsuario, -1);
		System.out.println(trilhas);
		return "";
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public String getOsbtaculos() {
		return osbtaculos;
	}

	public void setOsbtaculos(String osbtaculos) {
		this.osbtaculos = osbtaculos;
	}

	public LocalDate getDataGravacao() {
		return dataGravacao;
	}

	public void setDataGravacao(LocalDate dataGravacao) {
		this.dataGravacao = dataGravacao;
	}

	public int getIdTrilha() {
		return idTrilha;
	}

	public void setIdTrilha(int idTrilha) {
		this.idTrilha = idTrilha;
	}

	public double getDistancia() {
		return distancia;
	}

	public void setDistancia(double distancia) {
		this.distancia = distancia;
	}

	public int getIdMata() {
		return idMata;
	}

	public void setIdMata(int idMata) {
		this.idMata = idMata;
	}

	public String getNicknameUsuario() {
		return nicknameUsuario;
	}

	public void setNicknameUsuario(String nicknameUsuario) {
		this.nicknameUsuario = nicknameUsuario;
	}

	public ArrayList<Mata> getMatas() {
		return matas;
	}

	public void setMatas(ArrayList<Mata> matas) {
		this.matas = matas;
	}
	
}
