package pl.sm.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.sm.dto.ObjectResponse;
import pl.sm.dto.discord.GuildMember;
import pl.sm.model.UserData;
import pl.sm.services.DiscordService;
import pl.sm.services.JwtService;

import java.net.URI;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {
    private final JwtService jwtService;
    private final DiscordService discordService;

    public AuthController(JwtService jwtService, DiscordService discordService) {
        this.jwtService = jwtService;
        this.discordService = discordService;
    }

    @PostMapping(value = "/jwt", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ObjectResponse<UserData>> generateJwtToken(@RequestParam("access_token") String accessToken) {
        log.info("POST /auth/jwt?access_token={} endpoint invoked", accessToken);
        GuildMember guildMemberData = discordService.getGuildMemberData(accessToken);
        Set<String> mappedGuildRoles = discordService.mapGuildRoles(guildMemberData);
        String token = jwtService.generateToken(guildMemberData, mappedGuildRoles);
        UserData userData = new UserData(guildMemberData.getUser(), guildMemberData.getAvatar());
        return ResponseEntity.created(URI.create("/" + token)).body(new ObjectResponse<>(userData, token));
    }

}
