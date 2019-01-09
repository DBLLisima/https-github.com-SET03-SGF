/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gefa;

import Interfaces.FrmLogin;
import java.io.IOException;
import Conexion.ClassConexion;
import javax.swing.*;
/**
 *
 * @author Diego
 */
public class GEFA {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    
    public static void main(String[] args) throws IOException  {
        // TODO code application logic here
        FrmLogin inicio = new FrmLogin();
        inicio.setVisible(true);
        
    }
}
