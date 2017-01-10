package org.sfaci.gestion.util;

import javax.swing.*;
import java.text.DecimalFormat;
import java.text.ParseException;

/**
 * Clase Util
 */
public class Util {

    public static String formatMoneda(float cantidad) {

        DecimalFormat decimalFormat =
                new DecimalFormat("#.00 €");

        return decimalFormat.format(cantidad);
    }

    public static float unFormatMoneda(String cantidad)
        throws ParseException {

        DecimalFormat decimalFormat =
                new DecimalFormat("#.00 €");

        return decimalFormat.parse(cantidad).floatValue();
    }

    public static void mensajeInformacion(String titulo,
                                         String mensaje) {

        JOptionPane.showMessageDialog(null,
                mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);

    }

    public static void mensajeError(String titulo,
                                          String mensaje) {

        JOptionPane.showMessageDialog(null,
                mensaje, titulo, JOptionPane.ERROR_MESSAGE);

    }

    public static int mensajeConfirmacion(String titulo,
                                          String mensaje) {

        return JOptionPane.showConfirmDialog(null,
                mensaje, titulo, JOptionPane.YES_NO_OPTION);
    }
}
