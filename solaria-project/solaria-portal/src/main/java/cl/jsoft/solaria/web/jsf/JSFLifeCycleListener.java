package cl.jsoft.solaria.web.jsf;

import javax.faces.application.NavigationHandler;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

public class JSFLifeCycleListener implements PhaseListener {

	public static final String FORWARD_TO_LOGIN_FORM = "/faces/login-formulario.xhtml?faces-redirect=true";
	public static final String XHTML_FORM_LOGIN = "login-formulario.xhtml";

	/**
	 * 
	 */
	private static final long serialVersionUID = 65256868977L;
	private static final Logger log = Logger
			.getLogger(JSFLifeCycleListener.class);

	@Override
	public void afterPhase(PhaseEvent phaseEvent) {
		if (log.isInfoEnabled()) {
			// log.info("AFTER PHASE: " + phaseEvent.getPhaseId().toString());
		}

		comprobarEstadoDeSession(phaseEvent);
	}

	private void comprobarEstadoDeSession(PhaseEvent phaseEvent) {
		FacesContext facesContext = phaseEvent.getFacesContext();
		String currentPage = facesContext.getViewRoot().getViewId();

		log.info("currentPage: " + currentPage);

		boolean isLoginPage = (currentPage.lastIndexOf(XHTML_FORM_LOGIN) > -1);

		HttpSession session = (HttpSession) facesContext.getExternalContext()
				.getSession(false);
		log.info("isLoginPage: " + isLoginPage);

		if (session != null) {
			log.info("session_id: " + session.getId());

		} else {
			log.info("La session ha sido anulada.");
			if (!isLoginPage) {
				log.info("Lanzando ViewExpiredException");
				NavigationHandler nh = facesContext.getApplication()
						.getNavigationHandler();
				nh.handleNavigation(facesContext, null, FORWARD_TO_LOGIN_FORM);
				throw new ViewExpiredException();
			}
		}

	}

	@Override
	public void beforePhase(PhaseEvent phaseEvent) {
		if (log.isInfoEnabled()) {
			// log.info("BEFORE PHASE: " + phaseEvent.getPhaseId().toString());
		}
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}
}
