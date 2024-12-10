package pl.sm.smapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DiscordUser {
    private String id;
    private String username;
    private String discriminator;
    private String globalName = null;
    private String avatar;
    private Boolean bot = null;
    private Boolean system = null;
    private Boolean mfaEnabled = null;
    private String banner = null;
    private Integer accentColor = null;
    private String locale = null;
    private Boolean verified = null;
    private String email = null;
    private Integer flags = null;
    private Integer premiumType = null;
    private Integer publicFlags = null;
    private AvatarDecorationData avatarDecorationData = null;

    public DiscordUser(String id, String username) {
        this.id = id;
        this.username = username;
    }
}
