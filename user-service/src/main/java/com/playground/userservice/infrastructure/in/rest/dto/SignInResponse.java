package com.playground.userservice.infrastructure.in.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record SignInResponse(
    @Schema(description = "access token", example = "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJzcHJpbmctcGxheWdyb3VuZCIsImlhdCI6MTY5NjEwMjk5NSwic3ViIjoiMSIsInR5cGUiOiJBQ0NFU1NfVE9LRU4iLCJleHAiOjE2OTYxMDY1OTV9.eTjB2vL1-1w7q6u_doPjusfMzvjQEI7aVbRC3ara2632QaqwDs9xtH1kH0hAtzI8me0xDvAV2IsFF_7tuUD9OQ") String accessToken,
    @Schema(description = "refresh token", example = "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJzcHJpbmctcGxheWdyb3VuZCIsImlhdCI6MTY5NjEwMjk5NSwic3ViIjoiMSIsInR5cGUiOiJSRUZSRVNIX1RPS0VOIiwiZXhwIjoxNjk2MTQ2MTk1fQ.GHUmlLEIaThqvY7uDeCT3IoeVkoeUSoI0QxIagSa-EJhgbvXHO0q65T00iYmnKmhW21ROxVmakQDbIv0NXBk3Q") String refreshToken
) {

}
