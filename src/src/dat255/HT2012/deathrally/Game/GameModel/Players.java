/*
 * Copyright (c) 2012 Johannes Vestlund <Johannes@westlundarna.se>
 * 
 * This file is part of DeathRally
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by 
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even implied warranty of
 * MERCHANTABILITY or FITTNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */


package dat255.HT2012.deathrally.Game.GameModel;

/**
 * Class is responsible for managing players. Key is keeping track of who's active. There
 * have to be only one instance in the system, so singleton is used. 
 * 
 * @author Johannes Vestlund
 *
 */
public class Players {
	private static volatile Players instance = null;
	private Player activePlayer;
	
	private Players(){
	}
	
	public static Players getInstance(){
		if(instance == null){
			synchronized( Players .class){
				if(instance == null){
					instance = new Players();
				}
			}
		}	
		return instance;
	}
	
	public Player getActivePlayer(){
		return activePlayer;
	}
	
	public void setActivePlayer(Player p){
		activePlayer = p;
	}
}
