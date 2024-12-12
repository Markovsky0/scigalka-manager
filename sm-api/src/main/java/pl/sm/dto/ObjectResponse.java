package pl.sm.dto;

public record ObjectResponse<T>(T value, String token) {
}
