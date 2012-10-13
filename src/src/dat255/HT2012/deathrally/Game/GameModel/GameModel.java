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

package dat255.HT2012.deathrally.Game.GameModel;


public class GameModel {
	private boolean 				isRunning;
	private boolean 				gameEnded;
	private Level 					level;
	private Player 					player;
	
	public GameModel() {
		this.level = new Level();
		this.gameEnded = false;
	}
	
	public void startGame() {
		this.isRunning = true;
		this.gameEnded = false;
	}
	
	public void performAction(GameAction action) {
		// TODO Complete action performing sequences
		switch (action) {
		case TURN_LEFT:
			break;
		case TURN_RIGHT:
			break;
		case ACCELERATE:
			break;
		case HANDBRAKE:
			break;
		case SHOOT:
			break;
		case START_GAME:
			break;
		case RESTART_GAME:
			break;
		case PAUSE_GAME:
			break;
		case NO_ACTION:
			break;
		}
	}
	
	public void stopAction(GameAction action) {
		// TODO Complete action performing sequences
		switch (action) {
		case TURN_LEFT:
			break;
		case TURN_RIGHT:
			break;
		case ACCELERATE:
			break;
		case HANDBRAKE:
			break;
		case SHOOT:
			break;
		default:
			break;
		}
	}
	
	public void endGame() {
		if (level != null && isRunning) {
			this.isRunning = false;
//			this.world = null;
			this.gameEnded = true;
		}
	}
	
	public void update(int elapsedTime) {
		if (level != null && isRunning) {
			level.update(elapsedTime);
		}
		if (level.isGameOver()) {
			if (level.hasCompletedRace()) {
			    this.endGame();
			}
		}
	}
//	public void movePlayerVehicle(Player player, Direction direction) {
//		player.moveVehicle(direction);
//	}
//	
//	public void handBrakePlayerVehicle(Player player) {
//		player.setHandBrakeOn();
//	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

}
