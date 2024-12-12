package pl.sm.dto.discord;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GuildMember {
    private DiscordUser user;
    private String nick;
    private String avatar;
    private String banner;
    @NotNull
    private List<String> roles;
    @NotNull
    private LocalDateTime joinedAt;
    private LocalDateTime premiumSince;
    @NotNull
    private Boolean deaf;
    @NotNull
    private Boolean mute;
    @NotNull
    private int flags = 0;
    private Boolean pending;
    private String permissions;
    private LocalDateTime communicationDisabledUntil;
    private AvatarDecorationData avatarDecorationData;
}
