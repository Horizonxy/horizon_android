package com.horizon.android.db;

import android.content.Context;

import com.horizon.android.model.bean.CommonCacheVo;

import java.sql.SQLException;

public class CommonDaoImpl extends BaseDaoImpl<CommonCacheVo, Long> {

    public CommonDaoImpl(Context context, Class<CommonCacheVo> clazz) {
        super(context, clazz);
    }

    public void deleteAll(){
        try {
            super.deleteAll(CommonCacheVo.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
