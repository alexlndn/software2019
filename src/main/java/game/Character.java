package game;

public class Character {
	private static final double MEDIUMPROB = 0.5;
	private String name;
	private String gender;
	private int experience;
	private int hp;
	private int maxHp;
	private int level;
	public transient CharacterLevelObservable levelObservable;
	private Enemy activeEnemy;
	private CharacterClass charaClass;

	public Character(){
		this.levelObservable = new CharacterLevelObservable();
		this.experience = 0;
		this.level = 1;
		this.maxHp = 100;
	}

	public void move(String direction){

	}

	public Damage fight(){
		return charaClass.fight();
	}

	public void rest(){
		double rand = Math.random();
		if(rand > MEDIUMPROB) {
			int toAddHp = (int) Math.floor(Math.random() * this.hp / 2);
			if(this.hp + toAddHp > this.maxHp)
				this.hp = this.maxHp;
			else
				this.hp += toAddHp;
		}
		else if (rand > MEDIUMPROB / 2){
			int toDecreaseHp = (int) Math.floor(Math.random() * this.hp / 2);
			if(this.hp - toDecreaseHp <= 0)
				this.hp = 1;
			else
				this.hp -= toDecreaseHp;
		}
	}

	public void runAway(){
		this.setActiveEnemy(null);
	}

	public void levelUp(){
		this.setLevel(this.getLevel() + 1);
	}

	public void addExp(int exp){
		if(exp >= 0) {
			this.setExperience(this.getExperience() + exp);
		}else {
			throw new IllegalArgumentException("La experiencia no puede ser negativa.");
		}

	}
	
	public boolean receiveDamage(int dm){
		if(dm < 0) {
			throw new IllegalArgumentException("El da�o recibido no puede ser negativo.");
		}
		if(this.hp > dm) {
			this.hp -= dm;
			return true;
		}else {
			this.hp = 0;
			return false;
		}
	}

	// ACCESSORS
	public void setActiveEnemy(Enemy enemy){
		this.activeEnemy = enemy;
	}

	public Enemy getActiveEnemy(){
		return this.activeEnemy;
	}

	public int getLevel(){
		return this.level;
	}

	public void setLevel(int level){
		this.level = level;
		this.levelObservable.next(this.level);
	}

	public String getName(){
		return this.name;
	}

	public void setName(String name){
		this.name = name;
	}

	public int getHp(){
		return this.hp;
	}

	public void setHp(int hp){
		this.hp = hp;
	}

	public int getExperience(){
		return this.experience;
	}

	public void setExperience(int experience){
		this.experience = experience;
	}
	
	public void setMaxHp(int hp) {
		this.maxHp = hp;
	}
	
	public int getMaxHp() {
		return this.maxHp;
	}
	
	public void trapDamage(){
		int damage = (int) Math.ceil(0.1 * this.hp);
		this.hp = (damage > this.hp + 1) ? 1 : this.hp - damage;
	}

}
