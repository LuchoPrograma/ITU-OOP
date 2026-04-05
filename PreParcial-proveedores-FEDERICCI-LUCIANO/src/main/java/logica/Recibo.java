/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Recibo extends Comprobante {
    
    @JoinColumn(name="comp_prov_cod")
    @OneToOne
    private Proveedor proveedor = new Proveedor();
    
    @Column(name="comp_total")
    private float total;
    
    @Column(name="comp_detalle")
    private String detalle;

    public Recibo() {
    }
    
    public Recibo(char tipo, String detalle, float total) {
        super(tipo);
        this.detalle = detalle;
        this.total = total;
    }
    public Recibo(char tipo, int numero) {
        super(tipo, numero);
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }
    
    
}
