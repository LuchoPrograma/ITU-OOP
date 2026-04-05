/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.util.ArrayList;
import java.util.List;
import logica.Comprobante;
import logica.Proveedor;
import logica.Recibo;

/**
 *
 * @author lucia
 */
public class ControladoraPersistencia {
    
    ComprobanteJpaController comprobanteJPA = new ComprobanteJpaController();
    ProveedorJpaController proveedorJPA = new ProveedorJpaController();
    
    //COMPROBANTES
    public void crearComprobante(Recibo comprobante){
        comprobanteJPA.create(comprobante);
    }
    
    public ArrayList<Recibo> traerComprobantes(){
        List<Recibo> comprob = comprobanteJPA.findReciboEntities();
        return new ArrayList<>(comprob);
    }
    
    public Comprobante traerUNComprobante(int numero) {
        return comprobanteJPA.findRecibo(numero);
    }
    
    //PROVEEDORES
    public void crearProveedor(Proveedor proveedor){
        proveedorJPA.create(proveedor);
    }
    
    public ArrayList<Proveedor> traerProveedores(){
        List<Proveedor> proveedores = proveedorJPA.findProveedorEntities();
        return new ArrayList<>(proveedores);
    }

    public Proveedor traerUNProveedor(int codigo) {
        return proveedorJPA.findProveedor(codigo);
    }
    
    

   
    
    
}
