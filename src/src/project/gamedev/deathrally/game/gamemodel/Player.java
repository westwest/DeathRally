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

package project.gamedev.deathrally.game.gamemodel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.StreamCorruptedException;

import android.content.Context;
import android.util.Log;

public class Player implements Serializable{
	/**
	 * Generated serialVersion
	 */
	private static final int initialMoney = 10000;
	private static final long serialVersionUID = 6336819303006304934L;
	private static String defaultName = "Anonymous";
	private String name;
	private Vehicle vehicle;
	/**
	 * Standard amount of money, may be overridden by constructor
	 */
	private int money = initialMoney;
	
	/**
	 * Standard constructor for initializing completely new player. If
	 * name is invalid it defaults to "Anonymous"
	 * @param name Name of player. 
	 */
	public Player(String name) {
		setName(name);
	}
	
	/**
	 * Constructor for initializing saved player (or directly feed
	 * in player with more flexibility for test or cheat purpose).
	 * Invalid name defaults to "Anonymous".
	 * @param name Name of player.
	 * @param money Amount of money player have
	 * @param vehicle The vehicle player owns.
	 */
	public Player(String name, int money, Vehicle vehicle){
		setName(name);
		this.money = money;
		this.vehicle = vehicle;
	}
	
	/**
	 * 
	 * @return The amount of money player have at the moment
	 */
	public int getMoney(){
		return money;
	}
	
	/**
	 * 
	 * @param sum
	 */
	public void addMoney(int sum){
		money += sum;
	}
	
	/**
	 * Attempt execute a payment. Will return false if player 
	 * cannot pay
	 * @param sum to be payed
	 * @return True if player money > sum.
	 */
	public boolean pay(int sum){
		if(money > sum){
			money = money - sum;
		}else{
			return false;
		}
		return true;
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
	
	private void setName(String name){
		if(isName(name)){
			this.name = name;
		}else{
			this.name = defaultName;
		}
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
	
	public boolean save(Context context){
		String SAVENAME = name + ".txt";
		try {
			ObjectOutputStream OOS = new ObjectOutputStream(
					context.openFileOutput(SAVENAME, Context.MODE_PRIVATE));
			OOS.writeObject(this);
			OOS.close();
			return true;
		} catch (FileNotFoundException e) {
			Log.d("Player", "File not found.");
			e.printStackTrace();
		} catch (IOException e) {
			Log.d("Player", "IOException");
			e.printStackTrace();
		}
		return false;
	}
	
	public static Player load(Context context, String SAVENAME){
		if(!SAVENAME.contains(".txt")){
			SAVENAME = SAVENAME+".txt";
		}
		try {
			ObjectInputStream OIS = new ObjectInputStream(context.openFileInput(SAVENAME));
			try {
				Player p = (Player) OIS.readObject();
				return p;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (StreamCorruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean delete(String name, Context c){
		c.deleteFile(name+".txt");
		return false;
	}
}
