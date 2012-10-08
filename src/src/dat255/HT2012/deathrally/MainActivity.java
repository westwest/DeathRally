package dat255.HT2012.deathrally;

import dat255.HT2012.deathrally.Game.DeathRallyGame;
import dat255.HT2012.deathrally.Game.MainGamePanel;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
	private static final String TAG = MainActivity.class.getSimpleName();
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "Main activity created");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        Log.d(TAG, "Main menu inflated");
        return true;
    }
    
    public void launchGame(View view){
    	Intent intent = new Intent(this, DeathRallyGame.class);
    	startActivity(intent);
        Log.d(TAG, "Game launched");
    }
}
