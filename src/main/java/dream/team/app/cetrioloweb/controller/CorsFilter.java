package dream.team.app.cetrioloweb.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CorsFilter implements Filter {
    private ServletContext context;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest requisicao = (HttpServletRequest) request;
        this.context.log("CORSFilter HTTP Request: " + requisicao.getMethod());

        // Autoriza
        HttpServletResponse resposta = (HttpServletResponse) response;
        resposta.addHeader("Access-Control-Allow-Origin", "*");
     
        resposta.addHeader("Access-Control-Allow-Headers","*");
        
        // Para requisicoes com metodo OPTIONS
        if (requisicao.getMethod().equals("OPTIONS")) {
            resposta.setStatus(HttpServletResponse.SC_ACCEPTED);
            return;
        }
 
        // Passa adiante demais requisicoes
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Aqui pode-se desalocar qualquer recurso
        
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        this.context = config.getServletContext();
        this.context.log("CORS - Filtro inicializado!");
    }
}
