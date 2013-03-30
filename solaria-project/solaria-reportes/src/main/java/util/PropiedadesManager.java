package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropiedadesManager {

	private static PropiedadesManager INSTANCIA;
	public PropiedadesManager() {}
	private static void crearInstancia() {
		if (INSTANCIA == null) {
			if (INSTANCIA == null) {
				INSTANCIA = new PropiedadesManager();
			}
		}
	}
	public static PropiedadesManager get() {
		crearInstancia();
        return INSTANCIA;
    }

	/*METODOS DE LA CLASE*/
	public static final String ARCHIVO_CONFIGURACION = "pathcredenciales.properties";
	public static final String PATH_CREDENCIALES = "path_credenciales";

	public static void main(String[] args) {
		get().leerArchivoConfiguracion();

	}

	public Properties leerArchivoConfiguracion(){
		Properties prop = null;
		if (comprobarExistenciaArchivo()) {

			prop = new Properties();

	    	try {
	            //carga propiedades del archivo
	    		prop.load(new FileInputStream(ARCHIVO_CONFIGURACION));
	    	} catch (IOException ex) {
	    		ex.printStackTrace();
	        }

		}else{
			System.out.println("El Archivo no Existe, se creará automaticamente.");
			crearArchivoPropiedades();
			System.out.println("Ejecute de nuevo el metodo.");
		}
		return prop;
	}

	private boolean comprobarExistenciaArchivo(){
		File archivo = new File(ARCHIVO_CONFIGURACION);
		if(archivo.exists()){
			return true;
		}
		return false;
	}

	protected void crearArchivoPropiedades(){
		Properties prop = new Properties();

    	try {
    		//definicion de propiedades y sus valores.
    		prop.setProperty(PATH_CREDENCIALES, "paquete.por.defecto");
 
    		//Guarda el archivo en la raiz del proyecto.
    		prop.store(new FileOutputStream(ARCHIVO_CONFIGURACION), "Archivo Configuración Generador VO");
    		System.out.println("Archivo Creado con Exito");
    	} catch (IOException ex) {
    		ex.printStackTrace();
        }
	}



}
