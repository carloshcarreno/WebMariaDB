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
import java.util.List;
import javax.ejb.EJB;
import modelo.Usuario;
import util.AuthUtil;

/**
 *
 * @author CharliePC
 */
@Named(value = "authController")
@SessionScoped
public class AuthController implements Serializable {

    private Usuario usuario;
    private Usuario usuarioAutenticado;

    @EJB
    UsuarioDAO ejbFacade;
    
    public AuthController() {
        usuario = new Usuario();
        usuarioAutenticado = null;
    }

    public String login() {

        String claveSegura = AuthUtil.crearClaveSegura(usuario.getPasswordUsuario());
        
        usuarioAutenticado = ejbFacade.authUser(usuario.getIdUsuario(), claveSegura );

        if(usuarioAutenticado!=null){
            return "home?faces-redirect=true";
        }else{
            return "index?faces-redirect=true";
        }
        
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuarioAutenticado() {
        return usuarioAutenticado;
    }

    public void setUsuarioAutenticado(Usuario usuarioAutenticado) {
        this.usuarioAutenticado = usuarioAutenticado;
    }

}
