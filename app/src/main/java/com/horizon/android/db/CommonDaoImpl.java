package com.horizon.android.db;

import android.content.Context;

import com.horizon.android.model.bean.CommonCacheVo;

public class CommonDaoImpl extends BaseDaoImpl<CommonCacheVo, Long> {

    public CommonDaoImpl(Context context, Class<CommonCacheVo> clazz) {
        super(context, clazz);
    }
}
