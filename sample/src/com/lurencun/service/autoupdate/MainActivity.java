package com.lurencun.service.autoupdate;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.lurencun.service.autoupdate.internal.SimpleJSONParser;

public class MainActivity extends Activity {

private ServiceConnectionHelper upgadeManager = new ServiceConnectionHelper();
	
	final static String url = "http://api.ilovedeals.sg/app_release/latest?app_type=android-mobile";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		View check = findViewById(R.id.check);
		check.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				upgadeManager.upgradeService.checkLatestVersion(url, new SimpleJSONParser());
			}
		});
		
		View download = findViewById(R.id.download);
		download.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				upgadeManager.upgradeService.downloadAndInstall();
			}
		});
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		ServiceConnectionHelper.bindService(this, upgadeManager);
	}
	
	@Override
	protected void onPause(){
		super.onPause();
		ServiceConnectionHelper.unbindService(this,upgadeManager);
	}
	
	@Override
	protected void onStop(){
		super.onStop();
		ServiceConnectionHelper.stopService(this);
	}

}
