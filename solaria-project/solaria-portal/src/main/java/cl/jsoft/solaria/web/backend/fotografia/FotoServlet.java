package cl.jsoft.solaria.web.backend.fotografia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class FotoServlet
 */
@WebServlet("/FotoServlet")
public class FotoServlet extends HttpServlet {
	private static final long serialVersionUID = 161848263L;
	
	Logger logger = Logger.getLogger(getClass());
	
	public static final String URL_FOTO = "c:\\pcarrasco\\imgsola";
	public static final String URL_SIN_FOTO = "sinfoto.jpg";
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        String filename = request.getParameter("filename");
        String filepath = URL_FOTO+File.separatorChar+filename;
        File file = new File(filepath);
        
        boolean existeFoto = file.exists();
        logger.debug(filepath+" existe? "+ existeFoto);
        
        if (!existeFoto) {
        	filepath = URL_FOTO+File.separator+URL_SIN_FOTO;
        	file = new File(filepath);
		}
        
        String mimeType = getMimeTypeFromFile(filepath); //
        
        try {
        	
	        response.setContentType(mimeType);
	        response.setContentLength((int)file.length());
	    
			// Abrir el archivo de traerlo
			FileInputStream in = new FileInputStream(file);
			OutputStream out = response.getOutputStream();
   
			// Copiar el contenido en un stream de bytes.
			byte[] buf = new byte[1024];
			int count = 0;
			while ((count = in.read(buf)) >= 0) {
			    out.write(buf, 0, count);
			}
			in.close();
			out.close();
		} catch (FileNotFoundException e) {
			logger.warn(file.getAbsoluteFile()+" NO FOUND!!!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private String getMimeTypeFromFile(String filepath) {
		String mimeType = getServletContext().getMimeType(filepath);
		if (mimeType == null) {
        	logger.warn("Could not get MIME type of "+filepath);
        	filepath = URL_FOTO+File.separator+URL_SIN_FOTO;
        	mimeType = getServletContext().getMimeType(filepath);
        }
		return mimeType;
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
