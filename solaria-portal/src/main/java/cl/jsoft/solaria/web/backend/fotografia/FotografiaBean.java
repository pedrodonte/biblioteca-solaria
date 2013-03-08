package cl.jsoft.solaria.web.backend.fotografia;

import java.io.File;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.primefaces.event.CaptureEvent;
import org.primefaces.model.CroppedImage;

import cl.jsoft.solaria.dominio.vos.VoCliente;
import cl.jsoft.solaria.servicios.ClienteServicesEJB;
import cl.jsoft.solaria.web.componentes.BuscaClienteBean;
import cl.jsoft.solaria.web.componentes.IBuscaCliente;
import cl.jsoft.solaria.web.controllers.MensajesBean;

@ManagedBean(name="fotoBean")
@ViewScoped
public class FotografiaBean {
	
	final String PNG=".png";
	
	static final String FOTO_BLANCO = "blanco.png";
	
	Logger logger = Logger.getLogger(getClass());
	
	@EJB
	private ClienteServicesEJB clienteServicesEJB;
	
	private String nombreFinalArchivo;
	
	//
	private String nombreFoto = FotoServlet.URL_SIN_FOTO;
	
	//
	private CroppedImage croppedImage;
	private String imgDefault = "/images/sin_foto.jpg"; //imageCropperBean.imgDefault
	private String newImageName = FOTO_BLANCO;
	
	@ManagedProperty(value="#{mensajesBean}")
	private MensajesBean mensajesBean;
	
	@ManagedProperty(value="#{buscaClienteBean}")
	private BuscaClienteBean buscaClienteBean;
	private IBuscaCliente iBuscaCliente = new IBuscaCliente() {
		@Override
		public void setClienteEncontrado(VoCliente cliente) {
			logger.debug("Me estan pasando un cliente: "+cliente);
			try{
			clienteSeleccionado = cliente;
			newImageName = clienteSeleccionado.getClienteImg();
			setNombreFinalArchivo(cliente.getClienteIdentificador().toString());
			}catch(Exception e){
				newImageName  = FOTO_BLANCO;
			}
		}
	};
	private VoCliente clienteSeleccionado = new VoCliente();

	
	@PostConstruct
	public void inicializarBean(){
		buscaClienteBean.addBuscaListener(iBuscaCliente);
	}

    private String getRandomImageName() {  
        int i = (int) (Math.random() * 10000000);  
          
        return String.valueOf(i);  
    }
    
    public void doCambiarNombreArchivo(ActionEvent actionEvent) {
    	

    	try {
        	if(newImageName.endsWith(FOTO_BLANCO)){
        		throw new Exception("No se ha sacado ninguna foto aún.");
        	}
        	
        	logger.info(newImageName+" -> "+nombreFinalArchivo);
    		
			cambiarNombreArchivo(newImageName,nombreFinalArchivo+PNG);
			mensajesBean.msgInfo(nombreFinalArchivo+PNG+" Cambio exitoso!");
			
			clienteSeleccionado.setClienteImg(nombreFinalArchivo+PNG);
			clienteServicesEJB.actualizarRegistro(clienteSeleccionado);
			
		} catch (Exception e) {
			logger.warn(e.getMessage());
			mensajesBean.msgWarn(e.getMessage());
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
   
      
	public void onSacaFoto(CaptureEvent captureEvent) {
		logger.debug("Sacando la foto.");
		
		String fotoCapturada = getRandomImageName();
		byte[] data = captureEvent.getData();
		nombreFoto = fotoCapturada + PNG;
		//String newFileName = FotoServlet.URL_FOTO + File.separator + nombreFoto;
		
		ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		String serverFile = servletContext.getRealPath("") + File.separator + "images" + File.separator + nombreFoto;
		
		
		imgDefault = "/images/"+nombreFoto;
		
		crearArchivoNuevo(data, serverFile);
		//crearArchivoNuevo(data, newFileName);
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
		
		//se elimina la foto nueva, en caso de existir una con el mismo nombre
		newfile.delete();
 
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

	public BuscaClienteBean getBuscaClienteBean() {
		return buscaClienteBean;
	}

	public void setBuscaClienteBean(BuscaClienteBean buscaClienteBean) {
		this.buscaClienteBean = buscaClienteBean;
	}

	public IBuscaCliente getiBuscaCliente() {
		return iBuscaCliente;
	}

	public void setiBuscaCliente(IBuscaCliente iBuscaCliente) {
		this.iBuscaCliente = iBuscaCliente;
	}

	public VoCliente getClienteSeleccionado() {
		return clienteSeleccionado;
	}

	public void setClienteSeleccionado(VoCliente clienteSeleccionado) {
		this.clienteSeleccionado = clienteSeleccionado;
	}

	

}
