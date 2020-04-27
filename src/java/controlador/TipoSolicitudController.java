/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.SolicitudDAO;
import dao.TipoSolicitudDAO;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import modelo.Solicitud;
import modelo.TipoSolicitud;

/**
 *
 * @author CharliePC
 */
@Named(value = "tipoSolicitudController")
@SessionScoped
public class TipoSolicitudController implements Serializable {

    
    private DataModel<TipoSolicitud> items;
    private TipoSolicitud current;

    @EJB
    private TipoSolicitudDAO ejbFacade;
  
    
    public TipoSolicitudController() {
        current = new TipoSolicitud();
    }

    
    public DataModel<TipoSolicitud> getListado() {
        items = new ListDataModel<TipoSolicitud>(ejbFacade.findAll());
        return items;
    }

    
    
    public DataModel<TipoSolicitud> getItems() {
        return items;
    }

    public void setItems(DataModel<TipoSolicitud> items) {
        this.items = items;
    }

    public TipoSolicitud getCurrent() {
        return current;
    }

    public void setCurrent(TipoSolicitud current) {
        this.current = current;
    }
    
    
    
}
