/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Conexion;

import Interfaces.FrmLogin;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.sql.SQLException;
import javax.swing.*;

/**
 *
 * @author Diego
 */
public class ClassConexion  {
    
    private Connection conexion;
    
    public ClassConexion()
    {
        
                String url;
		String server = "localhost";
		String base = "gefa"; 
		String usuario = "gefa";
		String pass = "gefa";
                
                try
                {
	       
                    Class.forName("com.mysql.jdbc.Driver");
                    url ="jdbc:mysql://" + server + "/"+ base;
                    url+="?connectTimeout=7000&socketTimeout=7000";
                    DriverManager.setLoginTimeout(1);
                    conexion =DriverManager.getConnection(url,usuario, pass);
	   	}
                catch(Exception e ) {
			String 	message="<html><p><b>No se ha podido conectar con la base de datos </b></p>" +
					"<p>Verifica los parametros de conexion.</p> ";
                        JOptionPane.showMessageDialog(new JFrame(), message);
                       System.exit(0);
	    }	     
    }
    public String consultarUsuario(String usuario, String contraseña) throws SQLException
    {
        ResultSet resultado;
        String sql="SELECT count(codigo) as usuario FROM `usuario` WHERE `login`='"+usuario+"' and"+
                " `password`=md5('"+contraseña+"') and estado='1' ";
        try
        {
            
            Statement sentencia=conexion.createStatement();
            resultado=sentencia.executeQuery(sql);
            
        }
        catch(Exception e)
        {
            resultado=null;
        }
        resultado.first();
        return resultado.getString("usuario");
        
    }
    
    
    public String consultarCodigoUsuario(String usuario, String contraseña) throws SQLException
    {
        ResultSet resultado;
        String sql="SELECT max(codigo) as usuario FROM `usuario` WHERE `login`='"+usuario+"' and"+
                " `password`=md5('"+contraseña+"') and estado='1' ";
        try
        {
            
            Statement sentencia=conexion.createStatement();
            resultado=sentencia.executeQuery(sql);
            
        }
        catch(Exception e)
        {
            resultado=null;
        }
        resultado.first();
        return resultado.getString("usuario");
        
    }
    
    public void cerrarConexion() throws SQLException
    {
        conexion.close();
    }
    public boolean guardarRUC(String usuario, String razonSocial, String ruc, String representante,
            String fechaInicioActividades) throws SQLException
    {
        boolean estado=false;
        String sql="INSERT INTO `gefa`.`ruc` (`Codigo`, `RazonSocial`, `RUC`, `Representante`,"+
                " `FechaInicioActividades`, `Usuario`, `Estado`) VALUES"+
                " (NULL, '"+razonSocial+"', '"+ruc+"', '"+representante+"',"+
                " '"+fechaInicioActividades+"', '"+usuario+"', '1');";
        try
        {
            Statement sentencia=conexion.createStatement();
            sentencia.executeUpdate(sql);
            cerrarConexion();
            estado=true;
        }
        catch(Exception e)
        {
            estado=false;
        }
        return estado;
    }
    
    
    public boolean guardarProveedor(String razonSocial, String ruc, String pais,
            String ciudad, String direccion, String telefono) throws SQLException
    {
        boolean estado=false;
        String sql="INSERT INTO `gefa`.`proveedor` (`Codigo`, `RUC`, `RazonSocial`, `Pais`, `Ciudad`, `Direccion`,"+
                " `Telefono`, `Estado`) VALUES (NULL, '"+ruc+"', '"+razonSocial+"', '"+pais+"', '"+ciudad+"', '"+direccion+"',"+
                " '"+telefono+"', '1');";
        try
        {
            Statement sentencia=conexion.createStatement();
            sentencia.executeUpdate(sql);
            cerrarConexion();
            estado=true;
        }
        catch(Exception e)
        {
            estado=false;
        }
        return estado;
    }
    
    
    public boolean guardarUsuario(String identificacion, String nombres, String apellidos,
            String fechaNacimiento, String direccion, String correo,
            String usuario, String contraseña) throws SQLException
    {
        boolean estado=false;
        String sql="INSERT INTO `gefa`.`usuario` (`codigo`, `identificacion`, `nombres`, `apellidos`,"+
                " `fechaNacimiento`, `direccion`, `correo`, `login`, `password`, `estado`)"+
                " VALUES (NULL, '"+identificacion+"', '"+nombres+"', '"+apellidos+
                "', '"+fechaNacimiento+"', '"+direccion+"', '"+correo+"',"+
                " '"+usuario+"', MD5('"+contraseña+"'), '1');";
        try
        {
            Statement sentencia=conexion.createStatement();
            sentencia.executeUpdate(sql);
            cerrarConexion();
            estado=true;
        }
        catch(Exception e)
        {
            estado=false;
        }
        return estado;
    }
}
