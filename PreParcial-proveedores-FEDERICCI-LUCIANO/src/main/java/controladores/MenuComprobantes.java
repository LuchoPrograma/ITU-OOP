/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import logica.Comprobante;
import logica.Controladora;
import logica.Proveedor;
import logica.Recibo;
import vistas.Vista;

/**
 *
 * @author lucia
 */
public class MenuComprobantes implements ActionListener {

    private Vista vistaMenu;
    private Controladora controlador;

    public MenuComprobantes() {
        this.vistaMenu = new Vista();
        this.controlador = new Controladora();

        this.vistaMenu.getCargarReciboBTN().addActionListener(this);

        this.vistaMenu.setLocationRelativeTo(null);
        this.vistaMenu.setVisible(true);
        traerProveedores();
        traerComprobantes();
    }

    public MenuComprobantes(Vista vistaMenu) {
        this.vistaMenu = vistaMenu;
    }

    // CREATE
    public void crearComprobante() {
        ArrayList<Proveedor> proveedores = controlador.traerProveedores();

        char texto = this.vistaMenu.getTipoCBX().getSelectedItem().toString().charAt(0);
        System.out.println(texto);

        String textoString = this.vistaMenu.getDetalleTBX().getText().toString();
        System.out.println(textoString);

        try {
            Recibo nuevoRecibo = new Recibo(
                    this.vistaMenu.getTipoCBX().getSelectedItem().toString().charAt(0),
                    this.vistaMenu.getDetalleTBX().getText(),
                    Float.parseFloat(this.vistaMenu.getImporteTotalTBX().getText()));
            nuevoRecibo.getFecha().setDia(Integer.parseInt(this.vistaMenu.getDiaTBX().getText()));
            nuevoRecibo.getFecha().setMes(Integer.parseInt(this.vistaMenu.getMesTBX().getText()));
            nuevoRecibo.getFecha().setAnio(Integer.parseInt(this.vistaMenu.getAnioTBX().getText()));

            for (Proveedor prov : proveedores) {
                if (prov.getRazonSocial().equals(this.vistaMenu.getRazonSocialCMBX().getSelectedItem().toString())) {
                    nuevoRecibo.setProveedor(prov);
                }
            }

            controlador.crearComprobante(nuevoRecibo);
            JOptionPane.showMessageDialog(vistaMenu, "Recibo Cargado Correctamente");
            traerComprobantes();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(vistaMenu, e + "No fue posible cargar el nuevo recibo.");
        }
    }

    // READ
    public void traerComprobantes() {
        ArrayList<Recibo> recibos = controlador.traerComprobantes();
        DefaultTableModel model = (DefaultTableModel) this.vistaMenu.getTablaRecibosTBL().getModel();

        model.setRowCount(0);

        for (Recibo recibo : recibos) {
            model.addRow(new Object[] {
                    recibo.getTipo(),
                    recibo.getFecha().getDia() + "-" + recibo.getFecha().getMes() + "-" + recibo.getFecha().getAnio(),
                    recibo.getProveedor().getRazonSocial(),
                    recibo.getDetalle(),
                    recibo.getTotal()

            });
        }
    }

    public void traerProveedores() {
        ArrayList<Proveedor> proveedores = controlador.traerProveedores();

        try {
            for (Proveedor proveedor : proveedores) {
                this.vistaMenu.getRazonSocialCMBX().addItem(proveedor.getRazonSocial());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vistaMenu, "Error al cargar lista de Proveedores");
        }
    }

    // Insertar nuevo
    @Override
    public void actionPerformed(ActionEvent e) {
        crearComprobante();
    }

}
