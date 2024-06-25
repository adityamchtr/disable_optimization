package id.mchtr.disable_optimization.devices;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import id.mchtr.disable_optimization.utils.ActionsUtils;
import id.mchtr.disable_optimization.utils.Manufacturer;
import id.mchtr.disable_optimization.utils.SystemUtils;

public class OnePlus extends DeviceAbstract {

    private static final ComponentName[] ONEPLUS_AUTO_START = {new ComponentName("com.oneplus.security", "com.oneplus.security.chainlaunch.view.ChainLaunchAppListActivity"),
            new ComponentName("com.oneplus.security", "com.oneplus.security.autorun.AutorunMainActivity")};

    private static final ComponentName[] ONEPLUS_POWER_SAVE = {new ComponentName("com.oneplus.security", "com.oneplus.security.highpowerapp.view.HighPowerAppActivity"),
            new ComponentName("com.oneplus.security", "com.oneplus.security.cleanbackground.view.ManageBackgroundAppListActivity")};

    @Override
    public boolean isThatRom() {
        return Build.BRAND.equalsIgnoreCase(getDeviceManufacturer().toString()) ||
                Build.MANUFACTURER.equalsIgnoreCase(getDeviceManufacturer().toString()) ||
                Build.FINGERPRINT.toLowerCase().contains(getDeviceManufacturer().toString());
    }

    // This is mandatory for new oneplus version android 8
    @Override
    public boolean needToUseAlongWithActionDoseMode(){
        return true;
    }

    @Override
    public Manufacturer getDeviceManufacturer() {
        return Manufacturer.ONEPLUS;
    }

    @Override
    public boolean isActionPowerSavingAvailable(Context context) {
        return getActionPowerSaving(context) != null;
    }

    @Override
    public boolean isActionAutoStartAvailable(Context context) {
        return getActionAutoStart(context) != null;
    }

    @Override
    public boolean isActionNotificationAvailable(Context context) {
        return false;
    }

    @Override
    public Intent getActionPowerSaving(Context context) {
        Intent intent = null;
        for (ComponentName component : ONEPLUS_POWER_SAVE) {
            if (ActionsUtils.isIntentAvailable(context, component)) {
                intent = ActionsUtils.createIntent();
                intent.setComponent(component);
                break;
            }
        }
        if (intent == null) {
            intent = SystemUtils.getAppInfoIntent(context.getPackageName());
        }
        return intent;
    }

    @Override
    public Intent getActionAutoStart(Context context) {
        Intent intent = null;
        for (ComponentName component : ONEPLUS_AUTO_START) {
            if (ActionsUtils.isIntentAvailable(context, component)) {
                intent = ActionsUtils.createIntent();
                intent.setComponent(component);
                break;
            }
        }
        return intent;
    }

    @Override
    public Intent getActionNotification(Context context) {
        return null;
    }

    @Override
    public String getExtraDebugInformation(Context context) {
        return null;
    }
}
