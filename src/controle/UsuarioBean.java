package controle;

import javax.faces.bean.ManagedBean;

import modelo.dao.UsuarioDAO;
import modelo.pojo.Usuario;

@ManagedBean
public class UsuarioBean {
	
	private String nickname;
	private String senha;
	private String nome;
	private String email;
	private UsuarioDAO usuDao;
	
	public UsuarioBean()
	{
		usuDao = new UsuarioDAO();
	}
	
	public String logar()
	{
		System.out.println("Buscando...");
		boolean sucefull = usuDao.colsultaSenha(nickname, senha);
		System.out.println(sucefull);
		if(sucefull)
			return "Menu";
		return "this";
	}

	public String cadastrar()
	{
		boolean sucefull = false;
		Usuario usuario = new Usuario(nickname, senha, nome, email);
		sucefull = usuDao.cadastrarUsuario(usuario);
		if(sucefull)
			return "Menu.xhtml";
		return"";
	}
	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UsuarioDAO getUsuDao() {
		return usuDao;
	}

	public void setUsuDao(UsuarioDAO usuDao) {
		this.usuDao = usuDao;
	}
	
}
