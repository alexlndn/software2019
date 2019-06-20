package game;

import java.util.ArrayList;

public class Mage extends CharacterClass {
	  public Mage(){
		  this.className="Archer";
		  this.skills.add(new MageSkill1());
		  this.skills.add(new MageSkill2());
		  this.skills.add(new MageSkill3());
		  activeSkill=null;
		  maxHealth=200;
		  armor=150;
		  magicResist=100;

	  }

	  public void fight(){
	    this.activeSkill.use();
	  }
	}