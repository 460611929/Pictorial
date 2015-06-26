package com.pictorial.net;

import com.android.volley.VolleyError;

/**
 * @author zhangbing1
 * @param <T>
 */
public interface RequestJsonListener<T> {
    /**
     * 成功
     *
     * @param <T>
     */
    public void requestSuccess(T result);

    /**
     * 错误
     */
    public void requestError(VolleyError e);
}
