/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service_Negocio;

import Model_Dominio.Jugador;
import Model_Dominio.Partida;
import Repository_Persistencia.JugadorRepository;
import java.util.Optional;
import java.util.Random;
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

    @Autowired
    private JugadorService jugadorService;

    @Autowired
    private PartidaService partidaService;

    private Random random = new Random();

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

    //metodo para lanzar lso dados
    public int lanzarDados() {
        //simulacion de dados de patolli del 1 al 6
        return random.nextInt(6) + 1; // genera un numero entre 1 y 6
    }

    //mmetodo para manejar un turno
    public void jugarTurno(Long jugadorId, Long partidaId) {
        //obtener el jugador y la partida
        Jugador jugador = jugadorService.obtenerJugador(jugadorId);
        Partida partida = partidaService.obtenerPartida(partidaId);

        //verificar que la partida este en progreso
        if (!"en progreso".equals(partida.getEstado())) {
            throw new RuntimeException("La partida ha finalizado o no está en progreso");
        }

        //Lanzar dados para el jugador
        int resultadoDados = lanzarDados();
        System.out.println("jugador: " + jugador.getNombre() + "ha lanzado los dados y obtuvo: " + resultadoDados);

        //calcular la nueva posicion del jugador
        int nuevaPosicion = jugador.getPosicionFicha() + resultadoDados;

        //condifcio de victoria (si alzanca la meta (50 espacios)
        if (nuevaPosicion >= 50) {
            System.out.println("Jugador " + jugador.getNombre() + " ha ganado la partida!");
        } else {
            //mover la ficha del jugador a la nueva posicion
            jugadorService.moverFicha(jugadorId, nuevaPosicion);
            System.out.println("Jugador " + jugador.getNombre() + " se ha movido a la posicion: " + nuevaPosicion);
        }
    }

    /**
     * // Método para iniciar una partida public Partida
     * iniciarNuevaPartida(List<Long> jugadoresIds) { Partida partida =
     * partidaService.iniciarPartida();
     *
     * // Inicia el juego para los jugadores System.out.println("Iniciando
     * partida con los jugadores: " + jugadoresIds);
     *
     * return partida; }
     */
}
