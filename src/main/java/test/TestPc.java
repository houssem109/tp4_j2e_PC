package test;

import java.util.List;
import dao.PcDaoImpl;
import pc.entities.Pc;

public class TestPc {
	public static void main(String[] args) {
		PcDaoImpl pdao = new PcDaoImpl();
		Pc pc = pdao.save(new Pc("Lenovo LOQ", 2400));
		System.out.println(pc);
		List<Pc> pcs = pdao.pcsParMC("ip");
		for (Pc p : pcs)
			System.out.println(p);
	}
}