/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package controladores;

import logica.Controladora;
import logica.Proveedor;

/**
 *
 * @author lucia
 */
public class Main {
    // Recordar forzar nombres de atributos(columnas) usando anotaciones. Tambien
    // forzar en la clase hijo(recibo) el nombre para que corresponda a Comprobante
    // para juntar todo en una tabla?
    public static void main(String[] args) {
         Controladora controlador = new Controladora();
        
         Proveedor prov1 = new Proveedor("Telmo S.A");
         Proveedor prov2 = new Proveedor("Intrumio Corp.");
         Proveedor prov3 = new Proveedor("Salto Palto S.A.");
        
         controlador.crearProveedor(prov1);
         controlador.crearProveedor(prov2);
         controlador.crearProveedor(prov3);

        new MenuComprobantes();
    }
}
