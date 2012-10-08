package dat255.HT2012.deathrally.Game.GameModel;

import java.util.Observable;
import dat255.HT2012.deathrally.Game.Visual.*;

public abstract class Entity extends Observable {
	VisualEntity visualEntity;
	
	public Entity(VisualEntity visualEntity) {
		super();
		this.visualEntity = visualEntity;
		addObserver(visualEntity);
	}


	
}

