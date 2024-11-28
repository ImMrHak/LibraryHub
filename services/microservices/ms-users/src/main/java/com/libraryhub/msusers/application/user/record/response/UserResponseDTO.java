package com.libraryhub.msusers.application.user.record.response;


public record UserResponseDTO(
        String idUser,
        String firstName,
        String lastName,
        String email,
        String username
) {
}
