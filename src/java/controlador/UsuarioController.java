/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.UsuarioDAO;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import modelo.Usuario;
import util.AuthUtil;

/**
 *
 * @author CharliePC
 */
@Named(value = "usuarioController")
@SessionScoped
public class UsuarioController implements Serializable {

    private DataModel<Usuario> items;
    private Usuario current;

    @EJB
    private UsuarioDAO ejbFacade;

    public UsuarioController() {
    }

    public DataModel<Usuario> getListado() {
        items = new ListDataModel<Usuario>(ejbFacade.findAll());
        return items;
    }

    public String doUserCreate() {

        String claveSegura = AuthUtil.crearClaveSegura(current.getPasswordUsuario());
        current.setPasswordUsuario(claveSegura);
        ejbFacade.create(current);
        current = new Usuario();
        return prepareList();

    }

    public String doUserUpdate() {
        String claveSegura = AuthUtil.crearClaveSegura(current.getPasswordUsuario());
        current.setPasswordUsuario(claveSegura);
        ejbFacade.edit(current);
        current = new Usuario();
        return prepareList();

    }

    public String doUserDelete() {

        ejbFacade.remove(current);
        current = new Usuario();
        return prepareList();

    }

    // Navegación
    public String prepareList() {
        return "/usuario/List?faces-redirect=true";
    }

    public String prepareCreate() {
        current = new Usuario();
        return "/usuario/Create?faces-redirect=true";
    }

    public String prepareEdit() {
        current = (Usuario) getItems().getRowData();
        return "/usuario/Edit?faces-redirect=true";
    }

    public void prepareDelete() {
        current = (Usuario) getItems().getRowData();
    }

    public String prepareView() {

        current = (Usuario) getItems().getRowData();

        return "/usuario/View?faces-redirect=true";
    }

    // Getters 
    public DataModel<Usuario> getItems() {
        return items;
    }

    public void setItems(DataModel<Usuario> items) {
        this.items = items;
    }

    public Usuario getCurrent() {
        return current;
    }

    public void setCurrent(Usuario current) {
        this.current = current;
    }

}
