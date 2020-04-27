/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.TipoSolicitud;

/**
 *
 * @author CharliePC
 */
@Stateless
public class TipoSolicitudDAO extends AbstractFacade<TipoSolicitud>{

    @PersistenceContext(unitName = "WebMariaDBPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public TipoSolicitudDAO(){
        super(TipoSolicitud.class);
    }
    
}
