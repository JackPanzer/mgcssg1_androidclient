package es.uhu.mgcss.uhurasmus;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class LAGesActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lages);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lages, menu);
		return true;
	}

}
