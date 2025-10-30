package br.com.coletaresiduos.coleta.dto;

import br.com.coletaresiduos.coleta.model.User;

public record UserDisplayDTO(
        Long userId,
        String name,
        String email
) {

    public UserDisplayDTO(User user) {
        this(user.getUserId(), user.getName(), user.getEmail());
    }
}
