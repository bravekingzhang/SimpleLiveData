package com.brzhang.livedata;

import android.content.Context;
import android.util.ArrayMap;

/**
 * Created by brzhang on 2018/5/17.
 * Description :
 */
public class LiveDataManger {
    private ArrayMap<Context, LiveData> contextArrayMap = new ArrayMap<>();

    private static LiveDataManger mInstance;

    private LiveDataManger() {

    }

    /**
     * 很明显这里做了一个单例
     *
     * @return
     */
    public static LiveDataManger getInstance() {
        if (mInstance == null) {
            synchronized (LiveDataManger.class) {
                if (mInstance == null) {
                    mInstance = new LiveDataManger();
                } else {
                    return mInstance;
                }
            }
        }
        return mInstance;
    }

    /**
     * 注册实例，共享数据
     *
     * @param context
     * @param liveData
     */
    public void registe(Context context, LiveData liveData) {
        if (!contextArrayMap.containsKey(context)) {
            contextArrayMap.put(context, liveData);
        }
    }

    /**
     * 注销实例，清楚引用，防止内存泄漏
     *
     * @param context
     */
    public void unregiste(Context context) {
        if (contextArrayMap.containsKey(context)) {
            contextArrayMap.remove(context);
        }
    }

    /**
     * 拿到context实例共享数据
     *
     * @param context
     * @return
     */
    public LiveData obtain(Context context) throws LiveDataNotInitializationException {
        if (contextArrayMap.containsKey(context)) {
            return contextArrayMap.get(context);
        }
        throw new LiveDataNotInitializationException();
    }
}
