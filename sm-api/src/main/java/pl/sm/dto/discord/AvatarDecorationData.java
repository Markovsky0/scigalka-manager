package pl.sm.dto.discord;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvatarDecorationData {
    @NotNull
    private String asset;
    @NotNull
    private String skuId;
}