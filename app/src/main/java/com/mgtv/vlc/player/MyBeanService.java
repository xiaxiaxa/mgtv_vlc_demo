package com.mgtv.vlc.player;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import momo.cn.edu.fjnu.androidutils.base.BaseBeanService;

public abstract class MyBeanService<T> implements BaseBeanService<T> {
    private final String TAG = MyBeanService.class.getSimpleName();
    @Override
    public void save(T object) {
        try {
            PlayerApplication.mDBManager.save(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(T object) {
        try {
            PlayerApplication.mDBManager.delete(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(T object) {
        try {
            PlayerApplication.mDBManager.update(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<T> getAll(Class<T> tClass) {
        List<T> lists = new ArrayList<T>();
        try {
            lists = PlayerApplication.mDBManager.findAll(tClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lists;
    }

    @Override
    public T getObjectById(Class<T> tClass, Object id) {
        T object = null;
        try {
            object = PlayerApplication.mDBManager.findById(tClass, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }

    @Override
    public abstract boolean isExist(T object) ;

    @Override
    public void saveAll(List<T> objects) {
        try {
            PlayerApplication.mDBManager.save(objects);
        } catch (Exception e) {
            Log.e(TAG, "saveAll->exception:" + e);
            e.printStackTrace();
        }

    }

    @Override
    public void updateAll(List<T> objects) {
        try {
            PlayerApplication.mDBManager.update(objects);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveOrUpdateAll(List<T> objects) {
        try {
            PlayerApplication.mDBManager.saveOrUpdate(objects);
        } catch (Exception e) {
            Log.e(TAG, "saveOrUpdateAll->exception:" + e);
            e.printStackTrace();
        }
    }

    public void saveOrUpdate(T object){
        try {
            PlayerApplication.mDBManager.saveOrUpdate(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
