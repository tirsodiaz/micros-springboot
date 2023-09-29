package inicio;

import static util.Constants.CLAVE;
import static util.Constants.ENCABEZADO;
import static util.Constants.PREFIJO_TOKEN;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
	
	public JWTAuthorizationFilter(AuthenticationManager authManager) {
		super(authManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		String header = req.getHeader(ENCABEZADO);
		if (header == null || !header.startsWith(PREFIJO_TOKEN)) {
			chain.doFilter(req, res);
			return;
		}
		//obtenemos los datos del usuario a partir del token
		UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
		//la informacion del usuario se almacena en el contexto de seguridad
		//para que pueda ser utilizada por Spring security durante el 
		//proceso de autorizacion
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(req, res);
	}
	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		//el token viene en la cabecera de la peticion
		String token = request.getHeader(ENCABEZADO);
		if (token != null) {
			// Se procesa el token y se recupera el usuario y los roles.
			Claims claims=Jwts.parserBuilder()
					.setSigningKey(CLAVE.getBytes())
					.build()
					.parseClaimsJws(token.replace(PREFIJO_TOKEN, ""))
					.getBody();
			String user = claims.getSubject();
			List<String> authorities=(List<String>) claims.get("authorities");
			if (user != null) {
				//creamos el objeto con la informacion del usuario
				return new UsernamePasswordAuthenticationToken(user, null, authorities.stream()
													.map(SimpleGrantedAuthority::new)
													.collect(Collectors.toList()));
			}
			return null;
		}
		return null;
	}
}
