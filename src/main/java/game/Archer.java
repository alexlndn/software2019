package game;

import java.util.ArrayList;

public class Archer extends CharacterClass {
	  public Archer(){
		  this.className="Archer";
		  this.skills= new ArrayList<Skill>();
		  this.skills.add(new ArcherSkill1());
		  this.skills.add(new ArcherSkill2());
		  this.skills.add(new ArcherSkill3());
		  activeSkill=null;
		  maxHealth=200;
		  armor=150;
		  magicResist=100;

	  }

	  public Damage fight(){
	    return this.activeSkill.use();
	  }
	}