package org.sfaci.gestion.util;

import java.text.DecimalFormat;

/**
 * Clase Util
 */
public class Util {

    public static String formatMoneda(float cantidad) {

        DecimalFormat decimalFormat =
                new DecimalFormat("#.00 €");

        return decimalFormat.format(cantidad);
    }
}
