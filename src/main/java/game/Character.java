package game;

public class Character {
	private static final double MEDIUMPROB = 0.5;
	private String name;
	private int experience;
	private int hp;
	private int level;
	public transient CharacterLevelObservable levelObservable;
	public transient CharacterLifePointsObservable lifePointsObservable;
	private Enemy activeEnemy;
	private CharacterClass charaClass;

	public Character(){
		this.lifePointsObservable = new CharacterLifePointsObservable();
		this.levelObservable = new CharacterLevelObservable();
		this.experience = 0;
		this.level = 1;
		this.hp = 100;
		this.lifePointsObservable.next(this.hp);
		this.levelObservable.next(this.level);
	}

	public void move(String direction){

	}

	public Damage fight(){
		if(this.activeEnemy==null) {
			throw new IllegalStateException("No hay un enemigo al cual atacar");
		}
		return charaClass.fight();
	}

	public void rest(){
		double rand = Math.random();
		int toAddHp;
		int toDecreaseHp;
		if(rand > MEDIUMPROB) {
			if(this.hp<this.charaClass.getMaxHealth()*0.3) {
				toAddHp=this.hp/2;
			}
			else if(this.hp<this.charaClass.getMaxHealth()*0.6){
				toAddHp=this.hp/4;
			}
			else {
				toAddHp=this.hp/8;
			}
			if(this.hp + toAddHp > this.charaClass.getMaxHealth())
				this.hp = this.charaClass.getMaxHealth();
			else
				this.hp += toAddHp;
		}
		else if (rand > MEDIUMPROB / 2){
			if(this.hp<this.charaClass.getMaxHealth()*0.3) {
				toDecreaseHp=this.hp/20;
			}
			else if(this.hp<this.charaClass.getMaxHealth()*0.5){
				toDecreaseHp=this.hp/15;
			}
			else {
				toDecreaseHp=this.hp/10;
				
			}
			if(this.hp - toDecreaseHp <= 0)
				this.hp = 1;
			else
				this.hp -= toDecreaseHp;
		}
		this.lifePointsObservable.next(this.hp);
	}

	public boolean runAway(){
		if(Math.random()<0.5) {
		this.setActiveEnemy(null);
		return true;
	}
	else return false;	
	}
	public void levelUp(){
		this.setLevel( this.getLevel() + 1);
		int toIncreaseMaxHp;

		toIncreaseMaxHp= Math.round ( ( this.charaClass.getMaxHealth() / 100 ) );
		this.charaClass.setMaxHealth( this.charaClass.getMaxHealth() + toIncreaseMaxHp );
		
		this.charaClass.statsLevelUp();
		this.charaClass.SkillsLevelUp();
		
		this.levelObservable.next(this.level);
	}

	public void addExp(int exp){
		if(exp >= 0) {
			this.setExperience(this.getExperience() + exp);
		}else {
			throw new IllegalArgumentException("La experiencia no puede ser negativa.");
		}
		if(this.experience >= 300 * (this.level * 0.5)) {
			this.setExperience(this.experience - (300 * this.level / 2));
			this.levelUp();
		}

	}
	
	
	
	public boolean receiveDamage(int dm){
		if(dm < 0) {
			throw new IllegalArgumentException("El da�o recibido no puede ser negativo.");
		}
		if(this.hp > dm) {
			this.hp -= dm;
			this.lifePointsObservable.next(this.hp);
			return true;
		}else {
			this.hp = 0;
			this.lifePointsObservable.next(this.hp);
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
		this.lifePointsObservable.next(this.hp);
	}

	public int getExperience(){
		return this.experience;
	}

	public void setExperience(int experience){
		this.experience = experience;
	}
	
	public CharacterClass getCharaClass(){
		return this.charaClass;
	}
	public void setCharaClass(CharacterClass charaClass){
		this.charaClass = charaClass;
	}
	

	
	public void trapDamage(){
		int damage = (int) Math.ceil(0.1 * this.hp);
		this.hp = (damage > this.hp + 1) ? 1 : this.hp - damage;
		this.lifePointsObservable.next(this.hp);
	}

}
