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

package project.gamedev.deathrally;

import project.gamedev.deathrally.game.DeathRallyGame;
import project.gamedev.deathrally.game.model.Player;
import project.gamedev.deathrally.game.model.Players;
import project.gamedev.deathrally.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public boolean onPrepareOptionsMenu(Menu menu){
    	getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
    }
    
    public void launchGame(View view){
    	Intent intent = new Intent(this, DeathRallyGame.class);
    	startActivity(intent);
    }
    
    public void launchCreateProfileUI(View view){
    	setContentView(R.layout.create_player);
    	openOptionsMenu();
    }
    
    public void createNewProfile(View view){
    	EditText mEdit = (EditText) findViewById(R.id.editText1);
    	String name = mEdit.getText().toString();
    	Log.d("CreatePlayer", name);
    	Players admin = Players.getInstance();
    	Player player = new Player(name);
    	admin.setActivePlayer(player);
    	setContentView(R.layout.activity_main);
    }
}
