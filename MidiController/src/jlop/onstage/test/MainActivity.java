package jlop.onstage.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void onOpenMidiActivityClick(View view) {
//		Intent i = new Intent(this, ActivityTwo.class);
//		i.putExtra("Value1", "This value one for ActivityTwo ");
//		i.putExtra("Value2", "This value two ActivityTwo");
		Intent intent = new Intent(this, MidiActivity.class);
		intent.putExtra("ProgramNumber", "64");
		startActivity(intent);
	}
}
