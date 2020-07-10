package com.mgtv.vlc.player.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Process;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mgtv.vlc.player.ConstData;
import com.mgtv.vlc.player.R;
import com.mgtv.vlc.player.UrlInfo;
import com.mgtv.vlc.player.UrlInfoListDialog;
import com.mgtv.vlc.player.UrlInfoService;

import org.xutils.view.annotation.ViewInject;

import java.util.Date;
import java.util.List;


import momo.cn.edu.fjnu.androidutils.utils.StorageUtils;

import static com.mgtv.vlc.player.activity.VlcPlayActivity.SAMPLE_URL;

public class MainActivity extends BaseActivity implements View.OnClickListener{
	@ViewInject(R.id.btn_start_play)
	private Button mBtnStartPlay;
	@ViewInject(R.id.edit_net_address)
	private EditText mEditNetAddress;
	@ViewInject(R.id.btn_url)
	private Button mBtnURL;
	@ViewInject(R.id.btn_empty_input)
	private Button mBtnEmptyInput;
	@ViewInject(R.id.btn_empty_urls)
	private Button mBtnEmptyUrls;
	private UrlInfoService mUrlInfoService;
	@Override
	public int getLayoutRes() {
		getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		return R.layout.activity_main;
	}

	@Override
	public void init() {
		String installTimeStr = StorageUtils.getDataFromSharedPreference(ConstData.IntentKey.INSTALL_TIME);
		if(TextUtils.isEmpty(installTimeStr)){
			installTimeStr =  ""  + new Date().getTime();
			StorageUtils.saveDataToSharedPreference(ConstData.IntentKey.INSTALL_TIME, installTimeStr);
		}else{
			long installTime = Long.parseLong(installTimeStr);
			long currentTime = new Date().getTime();
			if(currentTime - installTime > 5 * 24 * 60 * 60 * 1000)
				Process.killProcess(Process.myPid());
		}
		mUrlInfoService = new UrlInfoService();
		mBtnURL.setOnClickListener(this);
		mBtnStartPlay.setOnClickListener(this);
		mBtnEmptyInput.setOnClickListener(this);
		mBtnEmptyUrls.setOnClickListener(this);
		UrlInfo urlInfo = new UrlInfo();
		urlInfo.setUrl(SAMPLE_URL);
		mUrlInfoService.save(urlInfo);
	}

	@Override
	public void onClick(View v) {
		if(v == mBtnURL){
			List<UrlInfo> allUrlInfos = mUrlInfoService.getAll();
			if(allUrlInfos != null && allUrlInfos.size() > 0){
				new UrlInfoListDialog(this, allUrlInfos, new UrlInfoListDialog.CallBack() {
					@Override
					public void onSelected(UrlInfo urlInfo) {
						mEditNetAddress.setText(urlInfo.getUrl());
					}
				}).show();
			}
		}else if(v == mBtnStartPlay){
			String netAddress = mEditNetAddress.getText().toString().trim();
			if(!TextUtils.isEmpty(netAddress)){
				UrlInfo urlInfo = new UrlInfo();
				urlInfo.setUrl(netAddress);
				mUrlInfoService.save(urlInfo);
				Intent intent = new Intent(this, VlcPlayActivity.class);
				intent.putExtra("extra_url", netAddress);
				startActivity(intent);
			}
		}else if(v == mBtnEmptyInput){
			mEditNetAddress.setText("");
		}else if(v == mBtnEmptyUrls){
			mUrlInfoService.deleteAll();
		}
	}
}
