package dat255.HT2012.deathrally;

import dat255.HT2012.deathrally.Game.DeathRallyGame;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

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
    
    public void launchGame(View view){
    	System.out.println("Launch Game!");
    	Intent intent = new Intent(this, DeathRallyGame.class);
    	startActivity(intent);
    }
}
