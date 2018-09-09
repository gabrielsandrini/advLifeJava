package modelo.pojo;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Essa classe se remete aos usuarios do sistema
 * */
public class Usuario {
	private String nicknameUsuario;
	private String senha;
	private String nome;
	private String foto;
	private String email;
	
	/**
	 * construtor para a criação de um objeto usuario
	 * @param nicknameUsuario nickname que o usuario inseriu para ser cadastrado
	 * @param senha senha inserida pelo usuario
	 * @param nome nome inserido pelo usuario
	 * @param email email inserido pelo usuario
	 * */
	public Usuario(String nicknameUsuario, String senha, String nome, String email) {
		this.nicknameUsuario = nicknameUsuario;
		this.senha = stringToMd5(senha);
		this.nome = nome;
		this.email = email;
	}
	
	
	public String stringToMd5(String texto) 
	{
		String hash = texto;
		try {
			MessageDigest m;
			m = MessageDigest.getInstance("MD5");
			m.update(texto.getBytes(),0,texto.length());
     	    hash = new BigInteger(1,m.digest()).toString(16);

		} 
		catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}
		
		return hash;
	 }
	
	//Getters and Setters:
	public String getNicknameUsuario() {
		return nicknameUsuario;
	}
	public void setNicknameUsuario(String nicknameUsuario) {
		this.nicknameUsuario = nicknameUsuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = stringToMd5(senha);
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
}