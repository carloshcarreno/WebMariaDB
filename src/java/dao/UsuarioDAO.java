/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modelo.Solicitud;
import modelo.Usuario;

/**
 *
 * @author CharliePC
 */
@Stateless
public class UsuarioDAO extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "WebMariaDBPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioDAO() {
        super(Usuario.class);
    }

    public Usuario authUser(Integer userId, String userPass) {

        Usuario found = null;

        Query q = getEntityManager().createQuery("Select u from Usuario u where u.idUsuario = :user and u.passwordUsuario = :pass");
        q.setParameter("user", userId);
        q.setParameter("pass", userPass);

        try {
            found = (Usuario) q.getSingleResult();
        } catch (NoResultException e) {
            Logger.getLogger("UsuarioDAO").log(Level.SEVERE, "No se encontraron registros");
        } catch (Exception e) {
            Logger.getLogger("UsuarioDAO").log(Level.SEVERE, "Error inesperado " + e.getLocalizedMessage());
        }
        return found;
    }

}
