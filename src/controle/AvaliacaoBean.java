package controle;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;

import modelo.dao.AvaliacaoDao;
import modelo.dao.CriterioAvaliacaoDAO;
import modelo.pojo.Avaliacao;
import modelo.pojo.CriterioAvaliacao;

@ManagedBean
public class AvaliacaoBean {
	private String idAvaliacao, comentario, nickname;
	private int idCriterio, nota, idTrilha;
	private LocalDate dataDeRealizacao;
	private ArrayList<CriterioAvaliacao> criterios; 
	private int[] listaNotas = {1,2,3,4,5};
	Avaliacao avaliacao[] = null;
	
	public AvaliacaoBean() 
	{
		CriterioAvaliacaoDAO criterioDao= new CriterioAvaliacaoDAO(); 
		setCriterios(criterioDao.consultarCriterios());
		
		comentario = null;
		
		//set notas = 1:
		for(int i = 0; criterios.size() > i; i++ )
		{
			criterios.get(i).setNota(1);
		}
	}
	
	public String cadastrarAvaliacao()
	{
		AvaliacaoDao avaliacaoDao = new AvaliacaoDao();
		
		for(int i = 0; criterios.size() > i; i++ )
		{
			criterios.get(i).setNota(nota);
			avaliacao[i] = new Avaliacao(criterios.get(i).getIdCriterio(),criterios.get(i).getNota(), comentario);
		}		
		
		avaliacaoDao.inserirAvaliacao(idTrilha, nickname, avaliacao);
		return "Menu.xhtml";
	}
	
	public String getIdAvaliacao() {
		return idAvaliacao;
	}
	public void setIdAvaliacao(String idAvaliacao) {
		this.idAvaliacao = idAvaliacao;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public int getIdCriterio() {
		return idCriterio;
	}
	public void setIdCriterio(int idCriterio) {
		this.idCriterio = idCriterio;
	}
	public int getNota() {
		return nota;
	}
	public void setNota(int nota) {
		this.nota = nota;
	}
	public LocalDate getDataDeRealizacao() {
		return dataDeRealizacao;
	}
	public void setDataDeRealizacao(LocalDate dataDeRealizacao) {
		this.dataDeRealizacao = dataDeRealizacao;
	}

	public ArrayList<CriterioAvaliacao> getCriterios() {
		return criterios;
	}

	public void setCriterios(ArrayList<CriterioAvaliacao> criterios) {
		this.criterios = criterios;
	}

	public int[] getListaNotas() {
		return listaNotas;
	}

	public void setListaNotas(int[] listaNotas) {
		this.listaNotas = listaNotas;
	}

	public int getIdTrilha() {
		return idTrilha;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setIdTrilha(int idTrilha) {
		this.idTrilha = idTrilha;
	}
	
}
