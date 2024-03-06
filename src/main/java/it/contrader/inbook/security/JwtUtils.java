package it.contrader.inbook.security;


import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import it.contrader.inbook.converter.UserConverter;
import it.contrader.inbook.model.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;


@Component
public class JwtUtils {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${contrader.app.jwtSecret}")
    private String jwtSecret;

    @Value("${contrader.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    @Value("${contrader.app.jwtCookieName}")
    private String jwtCookie;

    @Autowired
    private UserConverter userConverter;


    public String getJwtFromHeader(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if(bearerToken != null && bearerToken.startsWith("Bearer "))
            return bearerToken.substring(7);
        return null;

    }
//--------
    public String getEmailFromJwtToken(String jwt) {
        return Jwts.parserBuilder().setSigningKey(key()).build()
                .parseClaimsJws(jwt).getBody().getSubject();
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public List<String> getRolesFromJwtToken(String jwt) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJwt(jwt)
                .getBody();
        String roles = claims.get("roles", String.class);
        return roles != null ? Arrays.asList(roles.split(",")) : Collections.emptyList();
    }
//--------
    public boolean validateJwtToken(String jwt) {
        try {
            JwtParserBuilder j = Jwts.parserBuilder();
            JwtParserBuilder j1 = j.setSigningKey(key());
            JwtParser j2 = j1.build();
            Jwt<?, ?> j3 = (Jwt<?, ?>) j2.parse(jwt);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
            logger.error("Token: {}", jwt);
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }


    public void setRequestJwt(String jwt) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(requestAttributes != null){
            requestAttributes.getRequest().getSession().setAttribute("Authorization", "Bearer " + jwt);
        }
    }

    public String getRequestJwt() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(requestAttributes != null){
            String bearerToken = (String) requestAttributes.getRequest().getSession().getAttribute("Authorization");
            return bearerToken.substring(7);
        }
        return null;
    }



    public String generateJwt(UserDetailsImpl userDetails){
        List<String> authorities = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        return generateTokenFromEmail(userDetails.getEmail(),authorities);
    }

    private String generateTokenFromEmail(String email, List<String> authorities) {
        String roles = String.join(",",authorities);
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .claim("roles", roles)
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }
}
