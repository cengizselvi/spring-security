package edu.firat.finwise.security;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CurrentUserDTO {
    private int id;
    private String email;
    private String customerNumber;
}
