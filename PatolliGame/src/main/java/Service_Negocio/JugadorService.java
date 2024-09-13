/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service_Negocio;

import Model_Dominio.Jugador;
import Repository_Persistencia.JugadorRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author SDavidLedesma
 */
@Service
public class JugadorService {

    @Autowired
    private JugadorRepository jugadorRepository;

    //registrar un nuevo jugador
    public Jugador registrarJugador(String nombre) {
        Jugador jugador = new Jugador(nombre, 0);
        return jugadorRepository.save(jugador);
    }

    //mueve la ficha del jugador a una nueva posición
    public void moverFicha(Long jugadorId, int nuevaPosicion) {
        Optional<Jugador> optional = jugadorRepository.findById(jugadorId);
        if (optional.isPresent()) {
            Jugador jugador = optional.get();
            jugador.setPosicionFicha(nuevaPosicion);
            jugadorRepository.save(jugador);
        } else {
            throw new RuntimeException("Jugador no encontrado");
        }
    }

    // Obtiene un jugador por su ID
    public Jugador obtenerJugador(Long jugadorId) {
        return jugadorRepository.findById(jugadorId).orElseThrow(()
                -> new RuntimeException("Jugador no encontrado"));
    }
}
