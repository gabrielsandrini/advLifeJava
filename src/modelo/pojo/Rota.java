package modelo.pojo;

/**
 * Essa classe se refere a rota de cada trilha presente no sistema, ela será implementada posteriormente
 * */
public class Rota {
	private int latitude, longitude, latInicial, latFinal, longInicial, longFinal;

	public Rota(int latitude, int longitude, int latInicial, int latFinal, int longInicial, int longFinal) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.latInicial = latInicial;
		this.latFinal = latFinal;
		this.longInicial = longInicial;
		this.longFinal = longFinal;
	}
	

	public int getLatitude() {
		return latitude;
	}

	public void setLatitude(int latitude) {
		this.latitude = latitude;
	}

	public int getLongitude() {
		return longitude;
	}

	public void setLongitude(int longitude) {
		this.longitude = longitude;
	}

	public int getLatInicial() {
		return latInicial;
	}

	public void setLatInicial(int latInicial) {
		this.latInicial = latInicial;
	}

	public int getLatFinal() {
		return latFinal;
	}

	public void setLatFinal(int latFinal) {
		this.latFinal = latFinal;
	}

	public int getLongInicial() {
		return longInicial;
	}

	public void setLongInicial(int longInicial) {
		this.longInicial = longInicial;
	}

	public int getLongFinal() {
		return longFinal;
	}

	public void setLongFinal(int longFinal) {
		this.longFinal = longFinal;
	}
	
	
	
	
}
