# Unity install referrer receiver for Android
This plugin allows unity applications to catch install referrer from Google Play.

## Usage
1. Build project
2. Copy `app/build/outputs/aar/app-release.aar` and `ReferrerReceiver.cs` to Unity project
3. Now you may call `ReferrerReceiver.getInstallReferrer`:
```
ReferrerReceiver.getInstallReferrer(s=>{
            //do something with s
        }, false);
```