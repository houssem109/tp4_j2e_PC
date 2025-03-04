package pc.entities;

import java.io.Serializable;

public class Pc implements Serializable {
	private Long idPc;
	private String nomPc;
	private double prix;

	public Pc() {
		super();
	}

	public Pc(String nomPc, double prix) {
		super();
		this.nomPc = nomPc;
		this.prix = prix;
	}

	public Long getIdPc() {
		return idPc;
	}

	public void setIdPc(Long idPc) {
		this.idPc = idPc;
	}

	public String getNomPc() {
		return nomPc;
	}

	public void setNomPc(String nomPc) {
		this.nomPc = nomPc;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	@Override
	public String toString() {
		return "Pc [idPc=" + idPc + ", nomPc=" + nomPc + ", prix=" + prix + "]";
	}

}