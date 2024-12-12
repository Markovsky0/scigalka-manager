package pl.sm.dto.discord;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiscordUser {
    @NotNull
    private String id;
    @NotNull
    private String username;
    @NotNull
    private String discriminator;
    @NotNull
    private String globalName;
    @NotNull
    private String avatar;
    private Boolean bot;
    private Boolean system;
    private Boolean mfaEnabled;
    private String banner;
    private Integer accentColor;
    private String locale;
    private Boolean verified;
    private String email;
    private Integer flags;
    private Integer premiumType;
    private Integer publicFlags;
    private AvatarDecorationData avatarDecorationData;

    public DiscordUser(String id, String username) {
        this.id = id;
        this.username = username;
    }
}
