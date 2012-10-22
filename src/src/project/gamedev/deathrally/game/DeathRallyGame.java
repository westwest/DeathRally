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

package project.gamedev.deathrally.game;


import project.gamedev.deathrally.game.model.*;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

public class DeathRallyGame extends Activity {
	MainGamePanel view;
	GameLoop gameLoop;
	
	private static final String TAG = DeathRallyGame.class.getSimpleName();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {				
		super.onCreate(savedInstanceState);		
		
		Log.d(TAG, "game activity created");
		view = new MainGamePanel(this);
		gameLoop = new GameLoop(view.getHolder(), view);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(view);
	}
	
	protected void onPause() {
		gameLoop.setRunning(false);
		super.onPause();
		view.onPause();
	}
	
	protected void onResume() {
		gameLoop.setRunning(true);
		super.onResume();
		view.onResume();
	}


}
