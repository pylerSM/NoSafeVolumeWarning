package com.pyler.nosafevolumewarning;

import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

public class NoSafeVolumeWarning implements IXposedHookZygoteInit {
	@Override
	public void initZygote(IXposedHookZygoteInit.StartupParam startupParam)
			throws Throwable {
		Class<?> AudioService = XposedHelpers.findClass(
				"android.media.AudioService", null);
		XposedHelpers.findAndHookMethod(AudioService, "enforceSafeMediaVolume",
				new XC_MethodHook() {

					@Override
					protected void beforeHookedMethod(MethodHookParam param)
							throws Throwable {
						param.setResult(null);
					}
				});
		XposedHelpers.findAndHookMethod(AudioService, "checkSafeMediaVolume",
				int.class, int.class, int.class, new XC_MethodHook() {

					@Override
					protected void beforeHookedMethod(MethodHookParam param)
							throws Throwable {
						param.setResult(true);
					}
				});
	}
}
