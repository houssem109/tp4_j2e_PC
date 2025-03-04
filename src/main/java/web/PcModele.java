package web;

import java.util.ArrayList;
import java.util.List;

import pc.entities.Pc;

public class PcModele {
	private String motCle;
	List<Pc> pcs = new ArrayList<>();

	public String getMotCle() {
		return motCle;
	}

	public void setMotCle(String motCle) {
		this.motCle = motCle;
	}

	public List<Pc> getPcs() {
		return pcs;
	}

	public void setPcs(List<Pc> pcs) {
		this.pcs = pcs;
	}
}
