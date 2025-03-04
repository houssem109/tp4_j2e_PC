package dao;

import java.util.List;

import pc.entities.Pc;

public interface IPcDao {
	public Pc save(Pc p);
	//mc: mot cle
	public List<Pc> pcsParMC(String mc);

	public Pc getPc(Long id);

	public Pc updatePc(Pc p);

	public void deletePc(Long id);
}
