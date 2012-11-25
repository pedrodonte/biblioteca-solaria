package cl.jsoft.solaria.web.backend.fotografia;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.ServletContext;

import org.primefaces.model.CroppedImage;

@ManagedBean
@RequestScoped
public class ImageCropperBean {  
	
private CroppedImage croppedImage;

	private String imgDefault = "/images/barca/camp_nou.jpg"; //imageCropperBean.imgDefault

	private String newImageName;
	
	public String crop() {
		if(croppedImage == null)
			return null;
		
		setNewImageName(getRandomImageName()+".jpg");
		
		String newFileName = FotoServlet.URL_FOTO + File.separator + getNewImageName();
		
		FileImageOutputStream imageOutput;
		try {
			imageOutput = new FileImageOutputStream(new File(newFileName));
			imageOutput.write(croppedImage.getBytes(), 0, croppedImage.getBytes().length);
			imageOutput.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private String getRandomImageName() {
		int i = (int) (Math.random() * 100000);
		
		return String.valueOf(i);
	}
	
	public String getNewImageName() {
		return newImageName;
	}

	public void setNewImageName(String newImageName) {
		this.newImageName = newImageName;
	}

	public String getImgDefault() {
		return imgDefault;
	}

	public void setImgDefault(String imgDefault) {
		this.imgDefault = imgDefault;
	}
	
	public CroppedImage getCroppedImage() {
		return croppedImage;
	}

	public void setCroppedImage(CroppedImage croppedImage) {
		this.croppedImage = croppedImage;
	}
}  