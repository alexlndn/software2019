package game;

public class MageSkill1 extends Skill{
	public MageSkill1() {
		this.skillName = "Winter's Grasp";
		this.critProbability = 0.15;
		this.failProbability = 0.2;
		this.damage= new Damage(new DamageType("magical"), 53, 53);
	}


	@Override
	public Damage use() {
		if( (this.failProbability + Math.random()) < MEDIUMPROB) {
			damage.setDamagePoints(0);
		}
		else if (this.critProbability + Math.random() < MEDIUMPROB) {
			damage.setDamagePoints(damage.getBasicDamagePoints() * 2);
		}
		else {
			damage.setDamagePoints(damage.getBasicDamagePoints());
		}
		return damage;
	}
}

