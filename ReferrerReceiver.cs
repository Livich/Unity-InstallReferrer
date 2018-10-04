using System;
using UnityEngine;

public static class ReferrerReceiver
{
    private class AndroidCallbackHandler<T> : AndroidJavaProxy
    {
        private readonly Action<T> _resultHandler;

        public AndroidCallbackHandler(Action<T> resultHandler) : base("com.livich.unity_installreferrer.StringObjectCallback")
        {
            _resultHandler = resultHandler;
        }

        public void onResult(T result)
        {
            if (_resultHandler != null)
            {
                _resultHandler.Invoke(result);
            }
        }
    }

    public static AndroidJavaProxy ActionToJavaObject<T>(Action<T> action)
    {
        return new AndroidCallbackHandler<T>(action);
    }

    public static void getInstallReferrer(Action<String> callback, bool isTest = false)
    {
#if UNITY_ANDROID && !UNITY_EDITOR
        AndroidJavaObject androidObject;
        AndroidJavaObject unityObject;

        AndroidJavaClass unityClass = new AndroidJavaClass("com.unity3d.player.UnityPlayer");
        unityObject = unityClass.GetStatic<AndroidJavaObject>("currentActivity");

        AndroidJavaClass androidClass = new AndroidJavaClass("com.livich.unity_installreferrer.InstallReferrer");
        androidObject = androidClass.GetStatic<AndroidJavaObject>("self");

        androidObject.Call("getInstallReferrer", unityObject, isTest, ReferrerReceiver.ActionToJavaObject<String>(callback));
        return;
#endif
        callback("");
    }
}
