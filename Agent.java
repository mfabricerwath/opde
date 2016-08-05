package controleur;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;

import pojo.Agentp;
import pojo.Personnep;
import controleur.Message.TYPEMESSAGE;
import dao.Dao;
@ManagedBean
@ViewScoped
public class Agent extends Agentp{

	private static final long serialVersionUID = 1L;
    private Personnep p=new Personnep();
    private Agentp selected;
    private String chaine,pass;
	public Agent() {
		// TODO Auto-generated constructor stub
	}
	@PostConstruct
	public void init(){
		this.setListe(Dao.getInstance().getAllAgent());	
		this.setMatricule(Dao.getInstance().numIdentification("agent"));
		this.setDeleted(Dao.getInstance().getAllAgentDeleted());
	}
	public void trie(){
    	this.setL(Dao.getInstance().listeTrieAgent(chaine));
    }
     public void save(){
    	 if(this.p.getDatenaissance().compareTo(new Date())<=0){
    	if(!getPass().equalsIgnoreCase("")){
    	 this.setPersonne(p);
    	 this.setPassword(getPass());
    	 if(Dao.getInstance().insertUpdateAgent(this)){ Message.getInstance().showMessage(TYPEMESSAGE.SAVE_SUCCESS);
    	   reset();}
    	 else  Message.getInstance().showMessage(TYPEMESSAGE.SAVE_ERROR); }
    	else Message.getInstance().showMessage(TYPEMESSAGE.INCOMPLETE_FIELD);}
    	 else Message.getInstance().showMessage(TYPEMESSAGE.DATE_INVALIDE);
     }
     public void delete(){
    	 if(Dao.getInstance().deleteAgent(this)) {Message.getInstance().showMessage(TYPEMESSAGE.DELETE_SUCCESS); 
    	reset(); }else Message.getInstance().showMessage(TYPEMESSAGE.DELETE_SUCCESS);
     }
     
     public void Archive(){
    	 if(Dao.getInstance().archive("agent",this.getId())) {
    		 Message.getInstance().showMessage(TYPEMESSAGE.DELETE_SUCCESS); 
    	reset(); 
    	}else Message.getInstance().showMessage(TYPEMESSAGE.DELETE_SUCCESS);
     }
     public void desarchive(){
    	 if(Dao.getInstance().desarchive("agent",this.getId())) {
    		 Message.getInstance().showMessage(TYPEMESSAGE.DELETE_SUCCESS); 
    	reset(); 
    	}else Message.getInstance().showMessage(TYPEMESSAGE.DELETE_SUCCESS);
     }
     public  void onRowSelect(SelectEvent event){
    	selected=(Agentp)event.getObject();
    	if(selected!=null){
    	this.setP(selected.getPersonne());
    	this.setCandelete(selected.isCandelete());
    	this.setCansave(selected.isCansave());
    	this.setCanupdate(selected.isCanupdate());
    	this.setEtatcivil(selected.getEtatcivil());
    	this.setId(selected.getId());
    	this.setLogin(selected.getLogin());
    	this.setPass(selected.getPassword());
    	this.setPersonne(selected.getPersonne());
    	this.setProfil(selected.getProfil());
    	this.setTel(selected.getTel());
    	this.setMatricule(selected.getMatricule());
    	this.setLangue(selected.getLangue());
    	}
     }
     public void reset(){
    	 
    	this.setP(new Personnep());
     	this.setCandelete(false);
     	this.setCansave(false);
     	this.setCanupdate(false);
     	this.setEtatcivil("");
     	this.setId(0);
     	this.setLogin("");
     	this.setPassword("");
     	this.setPass("");
     	this.setProfil("");
     	this.setTel("");
     	this.setMatricule("");
     	this.setLangue("francais");
     	this.init();
     }
  
	public Agentp getSelected() {
		return selected;
	}
	public void setSelected(Agentp selected) {
		this.selected = selected;
	}
	public Personnep getP() {
		return p;
	}
	public void setP(Personnep p) {
		this.p = p;
	}
	public String getChaine() {
		return chaine;
	}
	public void setChaine(String chaine) {
		this.chaine = chaine;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	
}
