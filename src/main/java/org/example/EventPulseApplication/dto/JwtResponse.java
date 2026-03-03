package org.example.EventPulseApplication.dto;

public record JwtResponse(String token, String username, String role) {/*immutable*/}