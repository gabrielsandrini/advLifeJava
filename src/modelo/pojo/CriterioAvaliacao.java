package modelo.pojo;

import modelo.dao.CriterioAvaliacaoDAO;

public class CriterioAvaliacao {
	protected int idCriterio;
	protected String descricao;
	protected int nota;

	public CriterioAvaliacao(int idCriterio, String descricao) {
		this.idCriterio = idCriterio;
		this.descricao = descricao;
	}
	
	public CriterioAvaliacao(int idCriterio, String descricao, int nota) {
		this.idCriterio = idCriterio;
		this.descricao = descricao;
		this.nota = nota;
	}

	public CriterioAvaliacao(int idCriterio, int nota) {
		this.idCriterio = idCriterio;
		CriterioAvaliacaoDAO criterioDao = new CriterioAvaliacaoDAO();
		this.descricao = criterioDao.consultarDescricaoCriterio(idCriterio);
		this.nota = nota;
	}
	
	public CriterioAvaliacao(int idCriterio) {
		this.idCriterio = idCriterio;
		CriterioAvaliacaoDAO criterioDao = new CriterioAvaliacaoDAO();
		this.descricao = criterioDao.consultarDescricaoCriterio(idCriterio);
	}
	
	public int getIdCriterio() {
		return idCriterio;
	}
	public void setIdCriterio(int idCriterio) {
		this.idCriterio = idCriterio;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}
	
}
