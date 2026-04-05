/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.util.ArrayList;
import persistencia.ControladoraPersistencia;

/**
 *
 * @author lucia
 */
public class Controladora {
    ControladoraPersistencia controlPersis = new ControladoraPersistencia();
    
    //Comprobantes
    public void crearComprobante(Recibo comprob){
        controlPersis.crearComprobante(comprob);
    }
    
    public ArrayList<Recibo> traerComprobantes(){
        return controlPersis.traerComprobantes();
    }
    
    public Comprobante traerUNComprobante(int numero){
        return controlPersis.traerUNComprobante(numero);
    }
    
    //Proveedores
    public void crearProveedor(Proveedor prove){
        controlPersis.crearProveedor(prove);
    }
    
    public ArrayList<Proveedor> traerProveedores(){
        return controlPersis.traerProveedores();
    }
    
    public Proveedor traerUNProveedor(int codigo){
        return controlPersis.traerUNProveedor(codigo);
    }
}
