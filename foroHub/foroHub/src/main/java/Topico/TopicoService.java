package Topico;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TopicoService {
    private final TopicoRepository topicoRepo;

    public void crearTopico(Topico topico){
        topicoRepo.save(topico);
    }

    public List<Topico> listarTopicos() {
        return topicoRepo.findAll();
    }

    public Topico buscarTopico(Integer id) {
        return topicoRepo.findById(id).orElse(null);
    }

    public void eliminarTopico(Integer id) {
        topicoRepo.deleteById(id);
    }

    public Topico actualizarTopico(Topico topico) {
        return topicoRepo.save(topico);
    }
}
