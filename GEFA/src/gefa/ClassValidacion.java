/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gefa;

import static javax.management.Query.*;

/**
 *
 * @author Diego
 */
public class ClassValidacion {
    
    private static final int NUM_PROVINCIAS = 24;
    private static int[] coeficientes = { 4, 3, 2, 7, 6, 5, 4, 3, 2 };
    private static int constante = 11;
    
    public boolean validarRUC(String ruc)
    {
        boolean resp_dato = false;
		final int prov = Integer.parseInt(ruc.substring(0, 2));
		if (!(prov>0 && prov<=24)) 
                {
			resp_dato = false;
		}
                int[] d = new int[10];
		int suma = 0;
 
		for (int i = 0; i < d.length; i++) 
                {
			d[i] = Integer.parseInt(ruc.charAt(i) + "");
		}
                for (int i = 0; i < d.length - 1; i++) {
			d[i] = d[i] * coeficientes[i];
			suma += d[i];
		}
                int aux, resp;
 
		aux = suma % constante;
		resp = constante - aux;
 
		resp = (aux == 0) ? 0 : resp;
 
		if (resp == d[9]) {
			resp_dato = true;
		} else {
			resp_dato = false;
		}
          return   resp_dato;
    }
}
