package org.sfaci.gestion;

import org.sfaci.gestion.gui.Ventana;
import org.sfaci.gestion.gui.VentanaController;
import org.sfaci.gestion.gui.VentanaModel;

/**
 * Clase principal
 */
public class Principal {

    public static void main(String args[]) {
        Ventana ventana = new Ventana();
        VentanaModel model = new VentanaModel();
        VentanaController controller =
                new VentanaController(model, ventana);
    }
}
