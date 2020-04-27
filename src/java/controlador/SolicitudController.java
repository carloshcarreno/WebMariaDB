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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import modelo.Solicitud;
import modelo.TipoSolicitud;

/**
 *
 * @author CharliePC
 */
@Named(value = "solicitudController")
@SessionScoped
public class SolicitudController implements Serializable {

    private DataModel<Solicitud> items;
    private Solicitud current;

    @EJB
    SolicitudDAO ejbFacade;

    @EJB
    TipoSolicitudDAO ejbFacadeTS;

    @Inject
    private AuthController auth;

    public SolicitudController() {
    }

    public DataModel<Solicitud> getListado() {
        
      //  items = new ListDataModel<Solicitud>(ejbFacade.findAll());
      Integer userId = auth.getUsuarioAutenticado().getIdUsuario();
      items = new ListDataModel<Solicitud>(ejbFacade.getSolicitudesByUser(userId));
      return items;
    }

    public String doCreate() {
        current.setIdUsuarioSolicitud(auth.getUsuarioAutenticado());
        ejbFacade.create(current);
        return prepareList();
    }
    
   public String doUpdate(){
       
        ejbFacade.edit(current);
        current = new Solicitud();
         return prepareList();
        
    }    

    // Métodos de navegación
    public String prepareList() {

        return "/solicitud/List?faces-redirect=true";

    }

    public String prepareCreate() {
        current = new Solicitud();
        return "/solicitud/Create?faces-redirect=true";

    }
    
    public String prepareEdit(){
        current = (Solicitud) getItems().getRowData();
        return  "/solicitud/Edit?faces-redirect=true";
    }

    public DataModel<Solicitud> getItems() {
        return items;
    }

    public void setItems(DataModel<Solicitud> items) {
        this.items = items;
    }

    public Solicitud getCurrent() {
        return current;
    }

    public void setCurrent(Solicitud current) {
        this.current = current;
    }
    
    
    

}
