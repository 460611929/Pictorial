package com.pictorial.net;

import com.android.volley.VolleyError;

/**
 * @author zhangbing1
 *
 */
public interface RequestListener  {

    /** 成功 */
    public void requestSuccess(String json);

    /** 错误 */
    public void requestError(VolleyError e);
}
