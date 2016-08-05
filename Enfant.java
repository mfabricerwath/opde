package controleur;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;

import pojo.Agentp;
import pojo.Enfantp;
import pojo.Famillep;
import pojo.Personnep;
import controleur.Message.TYPEMESSAGE;
import dao.Dao;
@ManagedBean
@ViewScoped
public class Enfant extends Enfantp {

	private static final long serialVersionUID = 1L;
	private Enfantp selected;
	private Personnep p=new Personnep();
	private Agentp a=new Agentp();
	private Famillep f=new Famillep();
	private List<Agentp>ListeAgent=new ArrayList<Agentp>();
	private String chaine;
	
    public Enfant(){
	   
          }
    
    @PostConstruct
    public void init(){
		this.setListe(Dao.getInstance().getAllEnfant());
		this.setNumIdentification(Dao.getInstance().numIdentification("enfant"));
	    ListeAgent=Dao.getInstance().getAllAgentliste();
	    this.setDeleted(Dao.getInstance().getAllEnfantDeleted());
	} 
    public void trie(){
    	this.setL(Dao.getInstance().listeTrieEnfant(chaine));
    }
    
     public void save(){
    	 if(this.p.getDatenaissance().compareTo(new Date())<=0){
    	 this.setPersonne(p);
    	 if(Authentification.GetProperties().getProfil().equalsIgnoreCase("familleAcceuil")){
         this.setAgentacceuillant(Authentification.GetProperties());}
    	 this.setAgent(a);
    	 this.setFamille(this.getF());
    	
    	 if(Dao.getInstance().insertUpdateEnfant(this)) {
    		 Message.getInstance().showMessage(TYPEMESSAGE.SAVE_SUCCESS);
    	 reset();
    	 } 
    	 else 
    		 Message.getInstance().showMessage(TYPEMESSAGE.SAVE_ERROR); 
    	 }else
    		 Message.getInstance().showMessage(TYPEMESSAGE.DATE_INVALIDE);
    	
     }
     public void delete(){
    	 if(Dao.getInstance().deleteEnfant(this)) {Message.getInstance().showMessage(TYPEMESSAGE.DELETE_SUCCESS); 
    	 reset();}else Message.getInstance().showMessage(TYPEMESSAGE.DELETE_SUCCESS);
     }
     public void Archive(){
    	 if(Dao.getInstance().archive("enfant",this.getId())) {
    		 Message.getInstance().showMessage(TYPEMESSAGE.DELETE_SUCCESS); 
    	reset(); 
    	}else Message.getInstance().showMessage(TYPEMESSAGE.DELETE_SUCCESS);
     }
     public void desarchive(){
    	 if(Dao.getInstance().desarchive("enfant",this.getId())) {
    		 Message.getInstance().showMessage(TYPEMESSAGE.DELETE_SUCCESS); 
    	reset(); 
    	}else Message.getInstance().showMessage(TYPEMESSAGE.DELETE_SUCCESS);
     }
     public  void onrowSelect(SelectEvent event){
    	selected=(Enfantp)event.getObject();
    	if(selected!=null){
    		this.setNumIdentification(selected.getNumIdentification());
    		this.setId(selected.getId());
    		this.setP(selected.getPersonne());
    		this.setA(selected.getAgent());
    		this.setNiveau(selected.getNiveau());
    		this.setTypeFormation(selected.getTypeFormation());
    		this.setTypeEducation(selected.getTypeEducation());
    		this.setNomEcole(selected.getNomEcole());
    		this.setCommentaireDirecteur(selected.getCommentaireDirecteur());
    		this.setAvisAgent(selected.getAvisAgent());
    		this.setValidationDirecteur(selected.getValidationDirecteur());
    		this.setDateValidation(selected.getDateValidation());
    		this.setObservationFamille(selected.isObservationFamille());
    		this.setF(selected.getFamille());
    		this.setAgentacceuillant(selected.getAgentacceuillant());
    		this.setDatePlacement(selected.getDatePlacement());
    	}
     }
     public void reset(){
    	this.setNumIdentification("");
 		this.setP(new Personnep());
 		this.setA(new Agentp());
 		this.setNiveau("");
 		this.setTypeFormation("");
 		this.setTypeEducation("");
 		this.setNomEcole("");
 		this.setCommentaireDirecteur("");
 		this.setAvisAgent("");
 		this.setValidationDirecteur("");
 		this.setDateValidation(null);
 		this.setObservationFamille(false);
 		this.setF(new Famillep());
 		this.setAgentacceuillant(new Agentp());
 		this.setDatePlacement(null);
 		init();
     	
     }
     
     
	public Enfantp getSelected() {
		return selected;
	}

	public void setSelected(Enfantp selected) {
		this.selected = selected;
	}

	public Personnep getP() {
		return p;
	}

	public void setP(Personnep p) {
		this.p = p;
	}

	public Agentp getA() {
		return a;
	}

	public void setA(Agentp a) {
		this.a = a;
	}
	public Famillep getF() {
		return f;
	}
	public void setF(Famillep f) {
		this.f = f;
	}
	public List<Agentp> getListeAgent() {
		return ListeAgent;
	}
	public void setListeAgent(List<Agentp> listeAgent) {
		ListeAgent = listeAgent;
	}

	public String getChaine() {
		return chaine;
	}

	public void setChaine(String chaine) {
		this.chaine = chaine;
	}

}
