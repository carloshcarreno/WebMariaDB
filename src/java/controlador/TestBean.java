/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Part;
import org.omnifaces.util.Servlets;

/**
 *
 * @author CharliePC
 */
@Named(value = "testBean")
@SessionScoped
public class TestBean implements Serializable {

    private Part file;
    String folder = "c:\\uploads";

    public TestBean() {
    }

    public void upload() {
        if (file != null) {
         
            try(InputStream content = file.getInputStream()){
                String fileName = file.getSubmittedFileName();
                Files.copy( content , new File(folder, fileName).toPath());
                
                File archivoBorrar = new File("c:\\uploads\\Error.png");
                archivoBorrar.delete();
                
            } catch (IOException ex) {
                Logger.getLogger(TestBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }
    
    
    

}
