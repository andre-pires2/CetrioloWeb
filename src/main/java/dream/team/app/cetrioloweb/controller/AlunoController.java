package dream.team.app.cetrioloweb.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import dream.team.app.cetrioloweb.dao.AlunoDao;
import dream.team.app.cetrioloweb.entity.Aluno;

public class AlunoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Long id = Long.valueOf(req.getParameter("id"));
			AlunoDao alunoDao = new AlunoDao();
			Aluno aluno = alunoDao.searchAluno(id);
			String alunoJson = "{}";
			if (aluno != null) {
				ObjectMapper mapper = new ObjectMapper();
				alunoJson = mapper.writeValueAsString(aluno);
			}
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			resp.setStatus(200);
			PrintWriter out = resp.getWriter();
			out.print(alunoJson);
			out.flush();
		} catch (Exception e) {
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			resp.setStatus(400);
			PrintWriter out = resp.getWriter();
			out.print(e);
			out.flush();
			throw e;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			ObjectMapper mapper = new ObjectMapper();
			Aluno aluno = mapper.readValue(req.getReader(), Aluno.class);
			AlunoDao alunoDao = new AlunoDao();
			alunoDao.createOrUpdateAluno(aluno);
			String alunoJson = mapper.writeValueAsString(aluno);
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			resp.setStatus(201);
			String location = req.getServerName() + ":" + req.getServerPort() + req.getContextPath() + "/aluno?id="
					+ aluno.getId();
			resp.setHeader("Location", location);
			PrintWriter out = resp.getWriter();
			out.print(alunoJson);
			out.flush();
		} catch (Exception e) {
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			resp.setStatus(400);
			PrintWriter out = resp.getWriter();
			out.print(e);
			out.flush();
			throw e;
		}
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Long id = Long.valueOf(req.getParameter("id"));
			ObjectMapper mapper = new ObjectMapper();
			Aluno alunoInfo = mapper.readValue(req.getReader(), Aluno.class);
			alunoInfo.setId(id);
			AlunoDao alunoDao = new AlunoDao();
			alunoDao.createOrUpdateAluno(alunoInfo);
			String alunoJson = mapper.writeValueAsString(alunoInfo);
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			resp.setStatus(200);
			PrintWriter out = resp.getWriter();
			out.print(alunoJson);
			out.flush();
		} catch (Exception e) {
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			resp.setStatus(400);
			PrintWriter out = resp.getWriter();
			out.print(e);
			out.flush();
			throw e;
		}
	}
}