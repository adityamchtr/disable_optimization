package id.mchtr.disable_optimization.devices;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import id.mchtr.disable_optimization.utils.ActionsUtils;
import id.mchtr.disable_optimization.utils.Manufacturer;
import id.mchtr.disable_optimization.utils.SystemUtils;

public class Xiaomi extends DeviceAbstract {

    private static final ComponentName[] MIUI_AUTO_START = {new ComponentName("com.miui.securitycenter", "com.miui.permcenter.autostart.AutoStartManagementActivity")};

    private static final ComponentName[] MIUI_POWER_SAVE = {new ComponentName("com.miui.powerkeeper", "com.miui.powerkeeper.ui.HiddenAppsConfigActivity")};

    @Override
    public boolean isThatRom() {
        return Build.BRAND.equalsIgnoreCase(getDeviceManufacturer().toString()) ||
                Build.MANUFACTURER.equalsIgnoreCase(getDeviceManufacturer().toString()) ||
                Build.FINGERPRINT.toLowerCase().contains(getDeviceManufacturer().toString());
    }

    @Override
    public Manufacturer getDeviceManufacturer() {
        return Manufacturer.XIAOMI;
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
        for (ComponentName component : MIUI_POWER_SAVE) {
            if (ActionsUtils.isIntentAvailable(context, component)) {
                intent = ActionsUtils.createIntent();
                intent.setComponent(component);
                break;
            }
        }
        if (intent == null && isActionAutoStartAvailable(context)) {
            intent = SystemUtils.getAppInfoIntent(context.getPackageName());
        }
        return intent;
    }

    @Override
    public Intent getActionAutoStart(Context context) {
        Intent intent = null;
        for (ComponentName component : MIUI_AUTO_START) {
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
