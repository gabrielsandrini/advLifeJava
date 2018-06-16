package modelo.pojo;

import java.time.LocalDate;
/**
 * Essa classe se remete as trilhas presente no sistema
 * */
public class Trilha {
	private String apelido;
	private String osbtaculos;
	private LocalDate dataGravacao;
	private int idTrilha;
	private double distancia;
	private int idMata;
	private String nicknameUsuario;
	
	/**
	 * Construtor para inserir os dados de uma trilha
	 * @param apelido nome inserido pelo usuario para a trilha
	 * @param osbtaculos String contendo os obstaculos da trilha, texto inserido pelo usuario
	 * @param distancia distancia percorrida durante a realização da trilha
	 * @param idMata id referente ao tipo de mata
	 * @param nicknameUsuario nickname do usuario que gravou essa trilha
	 * */
	public Trilha(String apelido, String osbtaculos, double distancia, int idMata,
			String nicknameUsuario) {
		this.apelido = apelido;
		this.osbtaculos = osbtaculos;
		this.distancia = distancia;
		this.idMata = idMata;
		this.nicknameUsuario = nicknameUsuario;
		dataGravacao = LocalDate.now();
		
	}

	//Getters and Setters
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


}
