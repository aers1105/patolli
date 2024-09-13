/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service_Negocio;

import Model_Dominio.Partida;
import Repository_Persistencia.PartidaRepository;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author SDavidLedesma
 */
@Service
public class PartidaService {

    @Autowired
    private PartidaRepository partidaRepository;

    //iniciamos una neuva partida
    public Partida iniciarPartida() {
        Partida partida = new Partida("en progreso", new Date());
        return partidaRepository.save(partida);
    }

    //Finaliza la partida actual
    public void finalizarPartida(Long partidaId) {
        Optional<Partida> optional = partidaRepository.findById(partidaId);
        if (optional.isPresent()) {
            Partida partida = optional.get();
            partida.setEstado("terminada");
            partida.setFechaFin(new Date());
            partidaRepository.save(partida);
        } else {
            throw new RuntimeException("Partida no encontrada");
        }
    }

    //obtiene una partida por su ID
    // Obtiene una partida por su ID
    public Partida obtenerPartida(Long partidaId) {
        return partidaRepository.findById(partidaId).orElseThrow(()
                -> new RuntimeException("Partida no encontrada"));
    }
}
