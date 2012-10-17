package dat255.autumn2012.deathrally.game.gamemodel.weapons;

public abstract class Weapon {
	//Ammo-related
	private int magCapacity;
	private int ammo;
	
	//Position on car
	private float posX;
	private float posY;
	
	public abstract void fire();
	public void reload(int shells){
		if(magCapacity < ammo+shells){
			ammo = magCapacity;
		}else{
			ammo+= shells;
		}
	}
	protected float getX(){
		return posX;
	}
	protected float getY(){
		return posY;
	}
	
}
