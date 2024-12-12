package pl.sm.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.sm.dto.discord.GuildMember;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DiscordService {
    private final RestTemplate restTemplate;

    public DiscordService() {
        this.restTemplate = new RestTemplate();
    }

    public GuildMember getGuildMemberData(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer ".concat(accessToken));
        GuildMember guildMemberData = restTemplate.exchange("https://discord.com/api/v10/users/@me/guilds/{guild.id}/member", HttpMethod.GET, new HttpEntity<>(headers), GuildMember.class, 306053110663741442L)
            .getBody();
        if (guildMemberData == null) {
            // todo exception
            throw new RuntimeException();
        }
        return guildMemberData;
    }

    public Set<String> mapGuildRoles(GuildMember guildMemberData) {
        Set<String> roles = new HashSet<>();
        List<String> discordRoleIds = guildMemberData.getRoles();
        if (!guildMemberData.getRoles().isEmpty()) {
            discordRoleIds.forEach(roleId -> {
                switch (roleId) {
                    // todo refactor (ale później)
                    // grzeczny chłopczyk
                    case "737229559795154994" -> roles.add("ROLE_grzeczny_chlopczyk");
                    // admion
                    case "393163768860901377" -> roles.add("ROLE_admion");
                }
            });
        }
        return roles;
    }
}