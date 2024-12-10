package pl.sm.smapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.sm.smapp.model.GuildMember;
import pl.sm.smapp.model.ObjectResponse;
import pl.sm.smapp.model.UserData;
import pl.sm.smapp.service.DiscordService;
import pl.sm.smapp.service.JwtService;

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
        // todo refactor
        log.debug("Generating JWT token");
        GuildMember guildMemberData = discordService.getGuildMemberData(accessToken);
        Set<String> mappedGuildRoles = discordService.mapGuildRoles(guildMemberData);
        String token = jwtService.generateToken(guildMemberData, mappedGuildRoles);
        UserData userData = new UserData(guildMemberData.getUser(), guildMemberData.getAvatar());
        return ResponseEntity.created(URI.create("/" + token)).body(new ObjectResponse<>(userData, token));
    }

}
