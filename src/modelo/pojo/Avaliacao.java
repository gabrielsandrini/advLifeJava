package modelo.pojo;

import java.time.LocalDate;
/**
 * Essa classe se remete a parte de avaliações de trilhas presente no sistema
 * */
public class Avaliacao extends CriterioAvaliacao {
	private String idAvaliacao, comentario;
	private LocalDate dataDeRealizacao;
	
	/**
	 * Construtor para a criação de um objeto quando já se tem o id da avaliacao que ele se refere
	 * @param idAvaliacao id da avaliação a que o objeto se refere
	 * @param comentario comentário presente na avaliação realizada, inserido pelo usuario e referente
	 *  ao critério de avaliacao especificado
	 * @param idCriterio id do criterio de avaliação utilizado, presente na tbCriterioDeAvaliacao
	 * @param nota nota dada pelo usuario, referente ao critério especificado
	 * */
	public Avaliacao(String idAvaliacao, String comentario, int idCriterio, int nota)
	{
		super(idCriterio, nota);
		this.idAvaliacao = idAvaliacao;
		this.comentario = comentario;
		dataDeRealizacao = LocalDate.now();
	}
	public Avaliacao(String idAvaliacao, int idCriterio, int nota)
	{
		super(idCriterio, nota);
		this.idAvaliacao = idAvaliacao;
		dataDeRealizacao = LocalDate.now();
	}
	
	/**
	 * Construtor para a criação de um objeto quando não se tem o id da avaliacao que ele se refere
	 * @param comentario comentário presente na avaliação realizada, inserido pelo usuario e referente
	 *  ao critério de avaliacao especificado
	 * @param idCriterio id do criterio de avaliação utilizado, presente na tbCriterioDeAvaliacao
	 * @param nota nota dada pelo usuario, referente ao critério especificado
	 * */
	public Avaliacao(int idCriterio, int nota, String comentario)
	{
		super(idCriterio);
		this.comentario = comentario;
		this.nota = nota;
		dataDeRealizacao = LocalDate.now();
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

	
}
