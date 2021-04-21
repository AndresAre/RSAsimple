/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firmadigital;

import java.security.*;
import java.security.Signature;

//Formato de codificacion de firma
import sun.misc.BASE64Encoder;

public class FirmaDigital {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
       
        //Primero vamos a generar la instancia de RSA
        KeyPairGenerator generador = KeyPairGenerator.getInstance("RSA");
        //Inicializamos la llave 
        generador.initialize(2048);
        
        //Generamos la llave
        KeyPair llaves = generador.genKeyPair();
        
        //ejemplo del documento a firmar, que en este caso es un string
        
       byte[] dato = "Mugiwaras".getBytes("UTF8");
       
       byte[] dato1 = "Mugiwaras 2.0".getBytes("UTF8");
       
       //Aqui usaremos a signature para firmar
       Signature firma = Signature.getInstance("SHA1withRSA");
        /*
            Existe la posibilidad de añadir un Hash dentro del algoritmo pero por lo mismo
            Se agregara mas relleno entonces aumentara el tamaño de la firma
       */
        
        firma.initSign(llaves.getPrivate());
        /*
            Aqui se actualiza el dato que vamos a firmar ya que se aplica la llave
        */
        firma.update(dato);
        
        //La firma siempre sera un grupo de bytes
        byte[] firmabytes = firma.sign(); //Aqui se firma el documento
        
        //esta es la cadena que imprime en todos los documentos originales
        System.out.println("firma: "+ new BASE64Encoder().encode(firmabytes));
    
        //VERIFICACION
        //Para su verificacion se utiliza la llave publica
        
        firma.initVerify(llaves.getPublic());
        
        //En este caso, se necesita el documento o dato para su pronta comparacion
        firma.update(dato1);
        
        //True si es el mismo, False lo contrario
        System.out.println(firma.verify(firmabytes));
        
    }
    
}
