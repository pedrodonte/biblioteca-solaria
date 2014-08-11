package cl.jsoft.solaria.web.jsf;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class EscuchadorDeSesion
 *
 */
@WebListener
public class EscuchadorDeSesion implements HttpSessionListener, HttpSessionAttributeListener {

    /**
     * Default constructor. 
     */
    public EscuchadorDeSesion() {
        System.out.println("EscuchadorDeSesion()");
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent arg0) {
        System.out.println("CREA:"+arg0.getSession().getId());
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent arg0) {
    	 System.out.println("DESTROY:"+arg0.getSession().getId()+" "+arg0.getSession().getCreationTime());
    	 TimeOutListener timeOutListener = (TimeOutListener) arg0.getSession().getAttribute("TO");
    	 timeOutListener.cerrarSesion();
    }

	@Override
	public void attributeAdded(HttpSessionBindingEvent arg0) {
		System.out.println(arg0.getName());
		
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
