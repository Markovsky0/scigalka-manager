package pl.sm.smapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GuildMember {
    private DiscordUser user = null;
    private String nick = null;
    private String avatar = null;
    private String banner = null;
    private List<String> roles;
    private LocalDateTime joinedAt;
    private LocalDateTime premiumSince = null;
    private Boolean deaf;
    private Boolean mute;
    private int flags = 0;
    private Boolean pending = null;
    private String permissions = null;
    private LocalDateTime communicationDisabledUntil = null;
    private AvatarDecorationData avatarDecorationData = null;
}
