package dream.team.app.cetrioloweb.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
Utilize autenticação básica (envio de usuário e senha em toda requisição)
implementada com Filter e proíba a usuário sem autorização do tipo "ADMIN"
o acesso aos serviços dos itens 3 e 4.
https://www.devmedia.com.br/controle-de-acesso-com-filtros/1720
*/

public class AuthFilter implements Filter { 
	
	private ServletContext context;
    private String username = "admin";
    private String password = "";
    private String realm = "PROTECTED";


	@Override
	public void doFilter(
			ServletRequest request,
			ServletResponse response,
			FilterChain filterChain)
					throws IOException, ServletException {
		
		this.context.log("Filtro acessado!");
		HttpServletRequest requisicao = (HttpServletRequest)request;
		HttpServletResponse resposta = (HttpServletResponse)response;

        String authHeader = requisicao.getHeader("Authorization");
        if (authHeader != null) {
            // Divide o conteúdo do header por espacos
            StringTokenizer strTkn = new StringTokenizer(authHeader);
            // Se possui conteúdo
            if (strTkn.hasMoreTokens()) {
                String basic = strTkn.nextToken();
                // Verifica se possui o prefixo Basic
                if (basic.equalsIgnoreCase("Basic")) {
                    try {
                        // Extrai as credenciais (Base64)
                        String credentials = 
                                new String(
                                    Base64.getDecoder()
                                        .decode(strTkn.nextToken()));
                        this.context.log("Credentials: " + credentials);
                        // Separa as credenciais em usuario e senha
                        Integer p = credentials.indexOf(":");
                        if (p != -1) {
                            String _username = 
                                    credentials.substring(0, p).trim();
                            String _password = 
                                    credentials.substring(p + 1).trim();
                            // Se nao bate com configuracao retorna erro
                            if (!username.equals(_username) || 
                                    !password.equals(_password)) {
                                unauthorized(resposta, "Credenciais inválidas");
                            }
                            // Prossegue com a requisicao
                            filterChain.doFilter(request, response);
                        } else {
                            unauthorized(resposta, 
                                    "Token inválido");
                        }
                    } catch (UnsupportedEncodingException e) {
                        throw new Error("Erro na autenticação", e);
                    }
                }
            }
        } else {
            unauthorized(resposta);
        }
		

	}

	@Override
	public void destroy() {
	// Aqui pode-se desalocar qualquer recurso
	}
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		this.context = config.getServletContext();
		this.context.log("Filtro inicializado!");
        this.context = config.getServletContext();
        this.context.log("Filtro inicializado!");
        this.username = config.getInitParameter("username");
        this.password = config.getInitParameter("password");
        String paramRealm = config.getInitParameter("realm");
        if (paramRealm != null && paramRealm.length() > 0) {
            this.realm = paramRealm;
        }
	}
	
    private void unauthorized(
    		HttpServletResponse response,
    		String message)
    				throws IOException {
        response.setHeader("WWW-Authenticate", "Basic realm=\"" + realm + "\"");
        response.sendError(401, message);
    }
    
    private void unauthorized(HttpServletResponse response) 
    		throws IOException {
        unauthorized(response, "Unauthorized");
    }

}