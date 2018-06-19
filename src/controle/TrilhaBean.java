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
		private ArrayList<Trilha> trilhas;
		
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
		/*
		 * Essa sequencia de ifs presentes nessa função verifica se os parametros foram passados na tela, caso não foram
		 * ele seta -1 para os valores numericos e a string "null" para as strings e consulta no banco.
		 * 
		 * Sorry pela gambiarra
		 * */
		String apelidoConsulta = "null";
		double distanciaConsulta;
		int idMataConsulta;
		String nicknameUsuarioConsulta;
		trilhas = null;
		System.out.println("Consultando...");
		
		if(apelido == "" || apelido == null || apelido == " ")
			apelidoConsulta = "null";
		else
			apelidoConsulta = apelido;
		
		if(distancia == 0.0)
			distanciaConsulta = -1;
		else
			distanciaConsulta = distancia;
		
		if(idMata == 0)
			idMataConsulta = -1;
		else
			idMataConsulta = idMata;
		
		if(nicknameUsuario == "" || nicknameUsuario == null || nicknameUsuario == " ")
			nicknameUsuarioConsulta = "null";
		else 
			nicknameUsuarioConsulta = nicknameUsuario;
		
		int dificuldadeConsulta = -1;
		int idLocomocaoConsulta = -1;
		
		System.out.println("Dados brutos: " + apelido + " " + distancia + " " + idMata + " " + nicknameUsuario);
		System.out.println("Dados Tratados: " +apelidoConsulta + " " + distanciaConsulta + " " + idMataConsulta + " "
		+ nicknameUsuarioConsulta);
		trilhas= trilhaDao.buscarTrilha(apelidoConsulta, distanciaConsulta, idMataConsulta, dificuldadeConsulta,
				nicknameUsuarioConsulta, idLocomocaoConsulta);
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

	public ArrayList<Trilha> getTrilhas() {
		return trilhas;
	}

	public void setTrilhas(ArrayList<Trilha> trilhas) {
		this.trilhas = trilhas;
	}

	public void setMatas(ArrayList<Mata> matas) {
		this.matas = matas;
	}
	
}
