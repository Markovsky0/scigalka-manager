package pl.sm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sm.dto.discord.DiscordUser;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserData {
    private String id;
    private String name;
    private String avatarHash;

    public UserData(DiscordUser userData, String avatarHash) {
        this.id = userData.getId();
        this.name = userData.getUsername();
        this.avatarHash = avatarHash != null ? avatarHash : userData.getAvatar();
    }
}
