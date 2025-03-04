package web;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.catalina.connector.Response;
import dao.IPcDao;
import dao.PcDaoImpl;
import pc.entities.Pc;

@WebServlet(name = "cs", urlPatterns = { "/controleur", "*.do" })
public class ControleurServlet extends HttpServlet {
	IPcDao PcMetier;

	@Override
	public void init() throws ServletException {
		PcMetier = new PcDaoImpl();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getServletPath();
		if (path.equals("/index.do")) {
			request.getRequestDispatcher("pcs.jsp").forward(request, response);
		} else if (path.equals("/chercher.do")) {
			String motCle = request.getParameter("motCle");
			PcModele model = new PcModele();
			model.setMotCle(motCle);
			List<Pc> prods = PcMetier.pcsParMC(motCle);
			model.setPcs(prods);
			request.setAttribute("model", model);
			request.getRequestDispatcher("pcs.jsp").forward(request, response);

		} else if (path.equals("/saisie.do")) {
			request.getRequestDispatcher("saisiePc.jsp").forward(request, response);
			
		} else if (path.equals("/save.do") && request.getMethod().equals("POST")) {
			String nom = request.getParameter("nom");
			double prix = Double.parseDouble(request.getParameter("prix"));
			Pc p = PcMetier.save(new Pc(nom, prix));
			request.setAttribute("pc", p);

			//request.getRequestDispatcher("confirmation.jsp").forward(request, response);
			response.sendRedirect("chercher.do?motCle=");
		} else if (path.equals("/supprimer.do")) {
			Long id = Long.parseLong(request.getParameter("id"));
			PcMetier.deletePc(id);
			response.sendRedirect("chercher.do?motCle=");

		

		} else if (path.equals("/editer.do")) {
			Long id = Long.parseLong(request.getParameter("id"));
			Pc p = PcMetier.getPc(id);
			request.setAttribute("pc", p);
			request.getRequestDispatcher("editerPc.jsp").forward(request, response);
			
		} else if (path.equals("/update.do")) {
			Long id = Long.parseLong(request.getParameter("id"));
			String nom = request.getParameter("nom");
			double prix = Double.parseDouble(request.getParameter("prix"));
			Pc p = new Pc();
			p.setIdPc(id);
			p.setNomPc(nom);
			p.setPrix(prix);
			PcMetier.updatePc(p);
			request.setAttribute("pc", p);
			//request.getRequestDispatcher("confirmation.jsp").forward(request, response);
			response.sendRedirect("chercher.do?motCle=");
		}

		else {
			response.sendError(Response.SC_NOT_FOUND);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}