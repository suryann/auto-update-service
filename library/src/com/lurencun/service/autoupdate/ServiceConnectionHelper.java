package com.lurencun.service.autoupdate;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

public class ServiceConnectionHelper implements ServiceConnection {

	public ComponentName name;
	public AutoUpgrade upgradeService;
	public boolean isBindingService = false;
	public Activity context;

	@Override
	public void onServiceConnected(ComponentName name, IBinder service) {
		this.name = name;
		upgradeService = (AutoUpgrade) service;
		isBindingService = true;
		upgradeService.registerReceiver();
	}

	@Override
	public void onServiceDisconnected(ComponentName name) {
		isBindingService = false;
		upgradeService.unregisterReceiver();
	}
	
	public static void bindService(Activity context,ServiceConnectionHelper connection){
		Intent intent  = new Intent();  
		intent.setClass(context, AutoUpgradeService.class); 
		connection.context = context;
		context.bindService(intent, connection, Context.BIND_AUTO_CREATE);
	}
	
	public static void unbindService(Activity context,ServiceConnectionHelper connection){
		Intent intent  = new Intent();  
		intent.setClass(context, AutoUpgradeService.class); 
		connection.context = context;
		context.unbindService(connection);
	}
	
	public static void startService(Activity context){
		Intent intent  = new Intent();  
		intent.setClass(context, AutoUpgradeService.class); 
		context.startService(intent);
	}
	
	public static void stopService(Activity context){
		Intent intent  = new Intent();  
		intent.setClass(context, AutoUpgradeService.class); 
		context.stopService(intent);
	}

}