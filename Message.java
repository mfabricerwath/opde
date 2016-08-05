package controleur;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Message {

	private static Message obj;
	private String message;
	private FacesMessage messageFace;
	 
    public enum TYPEMESSAGE {
    	SAVE_SUCCESS,SAVE_ERROR,DELETE_SUCCESS,DELETE_ERROR,INCOMPLETE_FIELD,OBJECT_NOTFOUND,CODE_EXISTING,LOGFEILURE,
    	ELEMENT_EXIST,DATE_INVALIDE,VERIFIE_ARGUMENT,RESET_SUCCESS,ERREUR_INCONNU,
     }
	public static Message  getInstance(){
		obj = new Message();
		return obj;
	}
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    } 
    public void showMessage(TYPEMESSAGE msg) { 
        switch (msg) {
		case SAVE_SUCCESS:
			messageFace = new FacesMessage(FacesMessage.SEVERITY_INFO, "Succès", "Enregistrement réussi");
			FacesContext.getCurrentInstance().addMessage(null, messageFace);
			break;
		case SAVE_ERROR:
			messageFace = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur", "Echec de l'enregistrement");
			FacesContext.getCurrentInstance().addMessage(null, messageFace);
			break;
		case DELETE_SUCCESS:
			messageFace = new FacesMessage(FacesMessage.SEVERITY_INFO, "Succès", "Suppression réussie");
			FacesContext.getCurrentInstance().addMessage(null, messageFace);
			break;
		case DELETE_ERROR:
			messageFace = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur", "Echec de la suppression");
			FacesContext.getCurrentInstance().addMessage(null, messageFace);
			break;
		case INCOMPLETE_FIELD:
			messageFace = new FacesMessage(FacesMessage.SEVERITY_WARN, "Attention","Ceertains champs de saisie doivent être completés");
			FacesContext.getCurrentInstance().addMessage(null, messageFace);
		case OBJECT_NOTFOUND:
			messageFace = new FacesMessage(FacesMessage.SEVERITY_WARN,"Attention", "Aucun objet n'est sélectionné!");
			FacesContext.getCurrentInstance().addMessage(null, messageFace);
			break;
		case CODE_EXISTING:
			messageFace = new FacesMessage(FacesMessage.SEVERITY_WARN, "Attention","L'Employe  existe déjà, Veuillez Saisir un autre matricule");
			FacesContext.getCurrentInstance().addMessage(null, messageFace);
			break;
		case LOGFEILURE:
			messageFace = new FacesMessage(FacesMessage.SEVERITY_WARN, "Attention","Votre Login et/ou le mot de passe peut etre incorrecte , Veuillez les saisir de nouveau");
			FacesContext.getCurrentInstance().addMessage(null, messageFace);
			break;
		case ELEMENT_EXIST:
			messageFace=new FacesMessage(FacesMessage.SEVERITY_WARN," ","Cet Elément existe déjà ");
			FacesContext.getCurrentInstance().addMessage(null, messageFace);
			break;
		case DATE_INVALIDE:
			messageFace=new FacesMessage(FacesMessage.SEVERITY_WARN," ","La Date selectionnée est invalide ");
			FacesContext.getCurrentInstance().addMessage(null, messageFace);
			break;
		case VERIFIE_ARGUMENT:
			messageFace=new FacesMessage(FacesMessage.SEVERITY_WARN," ","Verifier votre Login et/ou le mot de passe ");
			FacesContext.getCurrentInstance().addMessage(null, messageFace);
			break;
		case RESET_SUCCESS:
			messageFace=new FacesMessage(FacesMessage.SEVERITY_INFO," ","Reset Success");
			FacesContext.getCurrentInstance().addMessage(null, messageFace);
			break;
		case ERREUR_INCONNU:
			messageFace=new FacesMessage(FacesMessage.SEVERITY_WARN," ","Une Erreur Inconnue s'est produit");
			FacesContext.getCurrentInstance().addMessage(null, messageFace);
			break;
		default:
			break;
		}    	         
    }
}
