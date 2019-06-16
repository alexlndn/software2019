package src.main.java.game;

public abstract class Enemy {
	protected String enemyName;
	protected int level;
	protected int hp;
	protected int dps;
	protected int armor;
	protected int critProb;
	protected int failProb;
	protected int magicResist;
	protected int expKilled;
	
	public Enemy(String name, int lv, int hp, int dps, int armor, int critProb, int failProb, int magicResist, int expKilled) {
		this.enemyName = name;
		this.level = lv;
		this.critProb = critProb;
		this.magicResist = magicResist;
		this.expKilled = expKilled;
		this.hp = hp;
		this.dps = dps;
		this.armor = armor;
		this.failProb = failProb;
	}
	
	public abstract void fight(Character character);
	public void levelUp() {
		level++;
	}

	public String getEnemyName(){
		return this.enemyName;
	}

	public void setEnemyName(String enemyName){
		this.enemyName = enemyName;
	}

	public int getLevel() {
		return this.level;
	}
	public int getHp() {
		return this.hp;
	}
	public int getDps() {
		return this.dps;
	}
	public int getArmor() {
		return this.armor;
	}
	public int getCritProb() {
		return this.critProb;
	}
	public int getFailProb() {
		return this.failProb;
	}
	public int getMagicResist() {
		return this.magicResist;
	}
	public int getExpKilled() {
		return this.expKilled;
	}

	public void setLevel(int level){
		this.level = level;
	}
	public void setHp(int hp){
		this.hp = hp;
	}
	public void setDps(int dps){
		this.dps = dps;
	}
	public void setArmor(int armor){
		this.armor = armor;
	}
	public void setCritProb(int critProb){
		this.critProb = critProb;
	}
	public void setFailProb(int failProb){
		this.failProb = failProb;
	}
	public void setMagicResist(int magicResist){
		this.magicResist = magicResist;
	}
	public void setExpKilled(int expKilled){
		this.expKilled = expKilled;
	}
}
