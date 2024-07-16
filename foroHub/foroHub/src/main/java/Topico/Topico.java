package Topico;

import jakarta.persistence.Basic;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Topico {
    @Id
    @GeneratedValue
    private Integer idtopico;
    @Basic
    private String titulo;
    private String mensaje;
    private Date fechacreacion;
}
