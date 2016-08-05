package controleur;

import java.io.IOException;
import java.io.Serializable;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import langue.languages;
import pojo.Agentp;
import pojo.Personnep;
import controleur.Message.TYPEMESSAGE;
import dao.Dao;
@ManagedBean
@ViewScoped
public class Authentification extends Agentp implements Serializable {

	private static final long serialVersionUID = 1L;
	private String login, password;
	private Agentp agent = new Agentp();

	public void authentification() {

		if (!this.login.trim().equalsIgnoreCase("")||!this.password.trim().equalsIgnoreCase("")) {
			FacesContext context = FacesContext.getCurrentInstance();
			HttpSession s = (HttpSession) context.getExternalContext()
					.getSession(true);

			if (Dao.getInstance().checkLogin(login, password) != null)

				agent = Dao.getInstance().checkLogin(login, password);

			if (agent.getPersonne() != null) {
				s.setAttribute("nom", agent.getPersonne().getNom());
				s.setAttribute("prenom",agent.getPersonne().getPrenom());
				s.setAttribute("id",agent.getId());
				s.setAttribute("profil", agent.getProfil());
				s.setAttribute("image",agent.getPersonne().getImage());
				
				String locale1 = agent.getLangue();
				switch (locale1) {
				case "francais":
					languages.setLocale(Locale.FRENCH);
					break;
				case "anglais":
					languages.setLocale(Locale.ENGLISH);
					break;
				default:
					languages.setLocale(Locale.FRENCH);
					break;
				}

				try {
					context.getExternalContext().redirect( "/opde/MES-PAGES/index.jsf");
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			} else {
				Message.getInstance().showMessage(TYPEMESSAGE.LOGFEILURE);
				return;
			}
			
		} else
			Message.getInstance().showMessage(TYPEMESSAGE.LOGFEILURE);

	}

	public static void logOut() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext()
				.getSession(true);
		session.invalidate();

		try {
			context.getExternalContext().redirect("/opde/MES-PAGES/Authentification.jsf");
		} catch (IOException e) {

			System.out.println(e.getMessage());
		}

	}

	public static Agentp GetProperties() {

		Agentp u = new Agentp();
		Personnep p=new Personnep();
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext()
				.getSession(true);

		u.setProfil(session.getAttribute("profil").toString());
		p.setNom(session.getAttribute("nom").toString());
		p.setPrenom(session.getAttribute("prenom").toString());
		u.setPersonne((p));
		u.setId(Integer.valueOf(session.getAttribute("id").toString()));
		

		return u;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
