/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller_Presentacion;

import Model_Dominio.Partida;
import Service_Negocio.PartidaService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author SDavidLedesma
 */
public class PartidaController {
    
    @Autowired
    private PartidaService partidaService;

    //iniciar una nueva partida
    public Partida iniciarPartida() {
        return partidaService.iniciarPartida();
    }

    //finalizar partida
    public void finalizarPartida(Long id) {
        partidaService.finalizarPartida(id);
    }

    //obtener partida por su id
    public Partida obtenerPartida(Long id) {
        return partidaService.obtenerPartida(id);
    }
}
