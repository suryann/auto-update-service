auto-update-service
============

An Android Service, provide a easy way to update app automatically.

Android App自动更新服务。

## 说明
	AppUpdate为Android App自动升级功能提供两个便捷的接口：

	- 1: checkLatestVersion(String updateUrl,ResponseParser parser);
		检测指定URL的版本信息，如果版本信息高于当前应用的版本号，则弹出版本信息窗口，提示用户升级。
		弹出窗口可以通过setCustomDisplayer(Displayer d)设置自定义显示窗口。

	- 2: checkAndInstallDirectly(String updateUrl,ResponseParser parser);
		检测指定URL的版本信息，如果版本信息高于当前应用版本号，则直接下载并安装。

## 响应解析 (ResponseParser)
	ResponseParser解析接口。指定URL返回的响应数据，需要通过此接口解析成Version对象。
	**所以，你需要实现此接口**

## 简单的使用例子

``` java

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
				// 检查最新版本，不弹出提示窗口，直接下载升级
				appUpdate.checkAndInstallDirectly("http://api.ilovedeals.sg/app_release/latest?app_type=android-mobile", 
						new SimpleJSONParser());
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

```



## Copyright and License

```
Copyright 2013 chenyoca@gmail.com

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at
 
   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
````