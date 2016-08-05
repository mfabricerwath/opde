package controleur;


import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import pojo.Agentp;
import pojo.Personnep;
import controleur.Message.TYPEMESSAGE;
import dao.Dao;
@ManagedBean
@SessionScoped
public class ConfigurationAgent extends Agentp{
	private static final long serialVersionUID = 1L;
    
	private Personnep p=new Personnep();
    private Agentp selected;
	public ConfigurationAgent() {
		// TODO Auto-generated constructor stub
	}
	 @PostConstruct
     public  void avoirAgent(){
    	selected=Dao.getInstance().getAgent(Authentification.GetProperties().getId());
    	if(selected!=null){
    	this.setP(selected.getPersonne());
    	this.setCandelete(selected.isCandelete());
    	this.setCansave(selected.isCansave());
    	this.setCanupdate(selected.isCanupdate());
    	this.setEtatcivil(selected.getEtatcivil());
    	this.setId(selected.getId());
    	this.setLogin(selected.getLogin());
    	this.setPassword(selected.getPassword());
    	this.setPersonne(selected.getPersonne());
    	this.setProfil(selected.getProfil());
    	this.setTel(selected.getTel());
    	this.setMatricule(selected.getMatricule());
    	this.setLangue(selected.getLangue());
    	}
     }
     public void save(){
    	
    	 this.setPersonne(p);
    	 if(Dao.getInstance().insertUpdateAgent(this)){ Message.getInstance().showMessage(TYPEMESSAGE.SAVE_SUCCESS);
    	   avoirAgent();}
    	 else  Message.getInstance().showMessage(TYPEMESSAGE.SAVE_ERROR); 
     }
     public void delete(){
    	 if(Dao.getInstance().deleteAgent(this)) {Message.getInstance().showMessage(TYPEMESSAGE.DELETE_SUCCESS); 
    	reset(); }else Message.getInstance().showMessage(TYPEMESSAGE.DELETE_SUCCESS);
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
     	this.setProfil("");
     	this.setTel("");
     	this.setMatricule("");
     	this.setLangue("francais");
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
	
}
