package Topico;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topico")
@RequiredArgsConstructor
public class TopicoController {
    private final TopicoService topicoService;

    @PostMapping("crear")
    public void crearTopico(@RequestBody Topico topico){
        topicoService.crearTopico(topico);
    }

    @GetMapping("/listar")
    public List<Topico> listarTopicos() {
        return topicoService.listarTopicos();
    }

    @GetMapping("/prueba")
    public String hello() {
        return "hola putita";
    }

    public Topico buscarTopico(@RequestBody Integer id) {
        return topicoService.buscarTopico(id); //.orElse(null);
    }

    public void elimiarTopico(Integer id) {
        topicoService.eliminarTopico(id);
    }

    public void actualizarTopico(Topico topico) {
        topicoService.crearTopico(topico);
    }
}
