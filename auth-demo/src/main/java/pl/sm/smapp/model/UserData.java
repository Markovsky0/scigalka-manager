package pl.sm.smapp.model;

public record UserData(String id, String name, String avatarHash) {
    public UserData(DiscordUser userData, String avatarHash) {
        this(userData.getId(), userData.getUsername(), avatarHash != null ? avatarHash : userData.getAvatar());
    }
}
