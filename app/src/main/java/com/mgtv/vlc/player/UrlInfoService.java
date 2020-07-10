/**
 * 
 */
package com.mgtv.vlc.player;

import org.xutils.ex.DbException;

import java.util.List;


public class UrlInfoService extends MyBeanService<UrlInfo>{

	@Override
	public boolean isExist(UrlInfo object) {
		return false;
	}

	public List<UrlInfo> getAll(){
		return getAll(UrlInfo.class);
	}
	
	public void deleteAll(){
		try {
			PlayerApplication.mDBManager.delete(UrlInfo.class);
		} catch (DbException e) {
			e.printStackTrace();
		}
	}
}
