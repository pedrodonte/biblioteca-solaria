package cl.jsoft.solaria.web.backend.fotografia;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CaptureEvent;
import org.primefaces.model.CroppedImage;

import cl.jsoft.solaria.util.HelperFechas;
import cl.jsoft.solaria.web.backend.prestamos.PrestamoSessionBean;
import cl.jsoft.solaria.web.controllers.MensajesBean;

@ManagedBean(name="fotoBean")
@ViewScoped
public class FotografiaBean {
	
	final String PNG=".png";
	
	Logger logger = Logger.getLogger(getClass());
	
	
	
	private String nombreFinalArchivo;
	
	//
	private String nombreFoto = FotoServlet.URL_SIN_FOTO;
	
	//
	private CroppedImage croppedImage;
	private String imgDefault = "/images/sin_foto.jpg"; //imageCropperBean.imgDefault
	private String newImageName;
	
	@ManagedProperty(value="#{mensajesBean}")
	private MensajesBean mensajesBean;

    
    private String getRandomImageName() {  
        int i = (int) (Math.random() * 10000000);  
          
        return String.valueOf(i);  
    }
    
    public void doCambiarNombreArchivo(ActionEvent actionEvent) {
    	logger.info(newImageName+" -> "+nombreFinalArchivo);
    	try {
			cambiarNombreArchivo(newImageName,nombreFinalArchivo+PNG);
			mensajesBean.msgInfo(nombreFinalArchivo+PNG+" Cambio exitoso!");
		} catch (Exception e) {
			long prefijo = HelperFechas.getInstancia().obtenerDateActual().getTime();
			nombreFinalArchivo=nombreFinalArchivo+"_"+prefijo+PNG;
			logger.warn(e.getMessage());
			mensajesBean.msgError("Problemas al ejecutar la operación");
		}
    }
  
    public String crop() {
		if(croppedImage == null)
			return null;
		
		setNewImageName(getRandomImageName()+PNG);
		
		String newFileName = FotoServlet.URL_FOTO + File.separator + getNewImageName();
		
		crearArchivoNuevo(croppedImage.getBytes(), newFileName);
		
		return null;
	}
   
      
	public void oncapture(CaptureEvent captureEvent) {
		String fotoCapturada = getRandomImageName();
		byte[] data = captureEvent.getData();
		nombreFoto = fotoCapturada + PNG;
		String newFileName = FotoServlet.URL_FOTO + File.separator + nombreFoto;
		
		ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		String serverFile = servletContext.getRealPath("") + File.separator + "images" + File.separator + nombreFoto;
		
		
		imgDefault = "/images/"+nombreFoto;
		
		crearArchivoNuevo(data, serverFile);
		crearArchivoNuevo(data, newFileName);
	}

    private void crearArchivoNuevo(byte[] dataFile, String nameFile){
    	logger.debug("La foto se guardara en :"+nameFile);
    	FileImageOutputStream imageOutput;  
        try {  
            imageOutput = new FileImageOutputStream(new File(nameFile));  
            imageOutput.write(dataFile, 0, dataFile.length);  
            imageOutput.close();  
        }  
        catch(Exception e) {
            e.printStackTrace();
        }  
    }
    
    private void cambiarNombreArchivo(String oldName, String newName) throws Exception{
    	
    	File oldfile =new File(FotoServlet.URL_FOTO + File.separator +oldName);
		File newfile =new File(FotoServlet.URL_FOTO + File.separator +newName);
 
		if(oldfile.renameTo(newfile)){
			logger.info("Rename succesful");
		}else{
			logger.info("Rename failed");
			throw new Exception("No es posible ejecutar el cambio de nombre");
		}
    }
   

	public String getNombreFoto() {
		return nombreFoto;
	}

	public void setNombreFoto(String nombreFoto) {
		this.nombreFoto = nombreFoto;
	}

	public CroppedImage getCroppedImage() {
		return croppedImage;
	}

	public void setCroppedImage(CroppedImage croppedImage) {
		this.croppedImage = croppedImage;
	}

	public String getImgDefault() {
		return imgDefault;
	}

	public void setImgDefault(String imgDefault) {
		this.imgDefault = imgDefault;
	}

	public String getNewImageName() {
		return newImageName;
	}

	public void setNewImageName(String newImageName) {
		this.newImageName = newImageName;
	}

	public String getNombreFinalArchivo() {
		return nombreFinalArchivo;
	}

	public void setNombreFinalArchivo(String nombreFinalArchivo) {
		this.nombreFinalArchivo = nombreFinalArchivo;
	}

	public MensajesBean getMensajesBean() {
		return mensajesBean;
	}

	public void setMensajesBean(MensajesBean mensajesBean) {
		this.mensajesBean = mensajesBean;
	}

	

}
