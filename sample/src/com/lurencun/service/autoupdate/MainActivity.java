package com.lurencun.service.autoupdate;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.lurencun.service.autoupdate.internal.SimpleJSONParser;

public class MainActivity extends Activity {

	AppUpdate appUpdate;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		appUpdate = AppUpdateService.getAppUpdate(this);
		
		View check = findViewById(R.id.check);
		check.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// 检查最新版本，并弹出窗口
				appUpdate.checkLatestVersion("http://api.ilovedeals.sg/app_release/latest?app_type=android-mobile", 
						new SimpleJSONParser());
			}
		});
		
		View download = findViewById(R.id.download);
		download.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// 在执行检查操作后，用户取消下载，可以通过此方法，下载最新版本。
				appUpdate.downloadAndInstall();
			}
		});
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		
		// ******** 
		appUpdate.callOnResume();
	}
	
	@Override
	protected void onPause(){
		super.onPause();
		
		// ******** 
		appUpdate.callOnPause();
	}

}
