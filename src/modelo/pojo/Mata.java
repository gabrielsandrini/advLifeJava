package modelo.pojo;

public class Mata {
	
	public Mata(int id, String descricao) {
		super();
		this.id = id;
		this.descricao = descricao;
	}
	
	private int id;
	private String descricao;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
