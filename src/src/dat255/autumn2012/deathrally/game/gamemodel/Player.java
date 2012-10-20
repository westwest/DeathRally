/*
 * Copyright (c) 2012 Mike Phoohad <anno.tao@gmail.com>
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

package dat255.autumn2012.deathrally.game.gamemodel;

public class Player {
	private static String defaultName = "Anonymous";
	private String name;
	private Vehicle vehicle;
	int id;
	
	public Player(String name) {
		if(isName(name)){
			this.name = name;
		}else
			this.name = defaultName;
	}
	
	public String getName(){
		return name;
	}
	
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	
	public Vehicle getVehicle() {
		return vehicle;
	}
	
	public boolean hasVehicle(){
		return vehicle != null;
	}
	
	public String toString(){
		return Player.class.getSimpleName() + ": " + getName();
	}
	
	public static String getDefaultName(){
		return defaultName;
	}
	
	private boolean isName(String name){
		if(name == null){
			return false;
		}
		if(name.isEmpty()){
			return false;
		}
		return true;
	}
	
}
