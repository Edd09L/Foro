package Usuarios;

import jakarta.persistence.Basic;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import jakarta.persistence.Entity;

@Entity
@Data
public class Usuario {
    @Id
    @GeneratedValue
    private Integer idusuario;
    @Basic
    private String usuario;
    private String pass;
}