package game;

public class WarriorSkill1 extends Skill {

  public WarriorSkill1(){
    this.skillName = "Slash";
    this.critProbability = 0.1;
    this.failProbability = 0.1;
    this.damage = new Damage(new DamageType("Physical"), 42);
  }

  public void use(){

  }
}
