/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
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
public class SolicitudDAO extends AbstractFacade<Solicitud>{

    @PersistenceContext(unitName = "WebMariaDBPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public SolicitudDAO(){
        super(Solicitud.class);
    }
    
    public List<Solicitud> getSolicitudesByUser(Integer userId){
       
        List<Solicitud> resultList = null;
        Query q = getEntityManager().createQuery("SELECT s from Solicitud s WHERE s.idUsuarioSolicitud.idUsuario = :id ");
        q.setParameter("id", userId);
        
        try {
            resultList = (List<Solicitud>) q.getResultList();
        } catch (NoResultException e) {
            Logger.getLogger("SolicitudDAO").log(Level.SEVERE, "No se encontraron registros");
        } catch (Exception e) {
            Logger.getLogger("SolicitudDAO").log(Level.SEVERE, "Error inesperado " + e.getLocalizedMessage());
        }
        
        return resultList;
    }
    
}
