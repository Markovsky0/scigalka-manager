package pl.sm.smapp.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;
import pl.sm.smapp.model.GuildMember;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class JwtService {
    private final JwtDecoder jwtDecoder;
    private final JwtEncoder jwtEncoder;

    public JwtService(JwtDecoder jwtDecoder, JwtEncoder jwtEncoder) {
        this.jwtDecoder = jwtDecoder;
        this.jwtEncoder = jwtEncoder;
    }

    public String generateTokenFromOAuth2User(OAuth2User user) {
        Instant now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
            .issuer("self")
            .expiresAt(now.plus(15, ChronoUnit.MINUTES))
            .issuedAt(now)
            .subject(user.getName())            // tak ma być
            .claims(customClaims -> {
                customClaims.put("username", user.getAttribute("username"));
                customClaims.put("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet()));
                customClaims.put("avatar", user.getAttribute("avatar"));
            })
            .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public String generateToken(GuildMember guildMember, Set<String> authorities) {
        Instant now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
            .issuer("self")
            .expiresAt(now.plus(15, ChronoUnit.MINUTES))
            .issuedAt(now)
            .subject(guildMember.getUser().getId())
            .claims(customClaims -> {
                customClaims.put("username", guildMember.getUser().getUsername());
                customClaims.put("roles", authorities);
            })
            .build();
        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public Jwt parseToken(String token) {
        return jwtDecoder.decode(token);
    }

    public boolean isTokenValid(Jwt jwt) {
        Instant now = Instant.now();
        return now.isAfter(Objects.requireNonNull(jwt.getIssuedAt())) && now.isBefore(Objects.requireNonNull(jwt.getExpiresAt()));
    }

    public List<SimpleGrantedAuthority> getRoles(Jwt jwt) {
        List<String> roles = (List<String>) jwt.getClaims().get("roles");
        return roles.stream().map(SimpleGrantedAuthority::new).toList();
    }
}
