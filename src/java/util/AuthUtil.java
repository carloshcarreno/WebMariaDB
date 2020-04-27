/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CharliePC
 */
public class AuthUtil {
    
    public static String crearClaveSegura(String clave){
        
        String claveGenerada = null;
        
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            String preSalt = "!:=32_8?"; 
            byte[] salt= preSalt.getBytes();
            md.update(salt);
            
            byte[] bytes = md.digest(clave.getBytes());
            
            StringBuilder sb = new StringBuilder();
            
            for(int i=0; i<bytes.length; i++){
                sb.append(  Integer.toString((bytes[i] & 0xff) + 0x100, 16  ).substring(1));
            }
            
            claveGenerada = sb.toString();
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(AuthUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return claveGenerada;
        
        
    }
    
}
