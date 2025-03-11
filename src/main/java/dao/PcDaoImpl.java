package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pc.entities.Pc;

public class PcDaoImpl implements IPcDao {
	@Override
	public Pc save(Pc p) {
		Connection conn = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO PCS(NOM_PC,PRIX) VALUES(?,?)");
			ps.setString(1, p.getNomPc());
			ps.setDouble(2, p.getPrix());
			ps.executeUpdate();
			PreparedStatement ps2 = conn.prepareStatement("SELECT MAX(ID_PC) as MAX_ID FROM PCS");
			ResultSet rs = ps2.executeQuery();
			if (rs.next()) {
				p.setIdPc(rs.getLong("MAX_ID"));
			}
			ps.close();
			ps2.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}

	@Override
	public List<Pc> pcsParMC(String mc) {
		List<Pc> pcs = new ArrayList<Pc>();
		Connection conn = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from PCS where NOM_PC LIKE ?");
			ps.setString(1, "%" + mc + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Pc p = new Pc();
				p.setIdPc(rs.getLong("ID_PC"));
				p.setNomPc(rs.getString("NOM_PC"));
				p.setPrix(rs.getDouble("PRIX"));
				pcs.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pcs;
	}

	@Override
	public Pc getPc(Long id) {

		Connection conn = SingletonConnection.getConnection();
		Pc p = new Pc();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from PCS where ID_PC = ?");
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				p.setIdPc(rs.getLong("ID_PC"));
				p.setNomPc(rs.getString("NOM_PC"));
				p.setPrix(rs.getDouble("PRIX"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}

	@Override
	public Pc updatePc(Pc p) {
		Connection conn = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("UPDATE PCS SET NOM_PC=?,PRIX=? WHERE ID_PC=?");
			ps.setString(1, p.getNomPc());
			ps.setDouble(2, p.getPrix());
			ps.setLong(3, p.getIdPc());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}

	@Override
	public void deletePc(Long id) {
		Connection conn = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM PCS WHERE ID_PC = ?");
			ps.setLong(1, id);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
