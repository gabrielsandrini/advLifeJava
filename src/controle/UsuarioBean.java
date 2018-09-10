package controle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import modelo.dao.UsuarioDAO;
import modelo.pojo.Md5;
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
		String hash = Md5.stringToMd5(senha);
		boolean sucefull = usuDao.colsultaSenha(nickname, hash);
		System.out.println(sucefull);
		FacesContext fc = FacesContext.getCurrentInstance();
		if(sucefull) {
			ExternalContext ec = fc.getExternalContext();
			HttpSession session = (HttpSession) ec.getSession(false);
			session.setAttribute("nickname", nickname);
			return "TrilhasUtilizadas";
		}
		else {
			FacesMessage fm = new FacesMessage("Usuario e/ou senha inválidos");
			fm.setSeverity(FacesMessage.SEVERITY_ERROR);
			fc.addMessage(null, fm);
			return "this"; 
		}
	}
	
	public String logout() 
	{
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(false);
		session.invalidate();
		return "Login.xhtml";
		
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
