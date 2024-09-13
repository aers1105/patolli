/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller_Presentacion;

import Model_Dominio.Jugador;
import Service_Negocio.JugadorService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author SDavidLedesma
 */
public class JugadorController {
    
    @Autowired
    private JugadorService jugadorService;

    //registrar un nuevo jugador
    public Jugador registrarJugador(String nombre) {
        return jugadorService.registrarJugador(nombre);
    }
    
    public void moverFicha(Long id, int posicion) {
        jugadorService.moverFicha(id, posicion);
    }
    
    public Jugador obtenerJugador(Long id) {
        return jugadorService.obtenerJugador(id);
    }
}
