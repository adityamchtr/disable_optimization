package id.mchtr.disable_optimization.devices;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import id.mchtr.disable_optimization.utils.ActionsUtils;
import id.mchtr.disable_optimization.utils.Manufacturer;

public class ZTE extends DeviceAbstract {

    private static final String ZTE_HEARTYSERVICE_PACKAGE_NAME ="com.zte.heartyservice";
    private static final String ZTE_HEARTYSERVICE_AUTOSTART_ACTIVITY ="com.zte.heartyservice.autorun.AppAutoRunManager";
    private static final String ZTE_HEARTYSERVICE_POWERSAVING_ACTIVITY ="com.zte.heartyservice.setting.ClearAppSettingsActivity";

    @Override
    public boolean isThatRom() {
        return Build.BRAND.equalsIgnoreCase(getDeviceManufacturer().toString()) ||
                Build.MANUFACTURER.equalsIgnoreCase(getDeviceManufacturer().toString()) ||
                Build.FINGERPRINT.toLowerCase().contains(getDeviceManufacturer().toString());
    }

    @Override
    public Manufacturer getDeviceManufacturer() {
        return Manufacturer.ZTE;
    }

    @Override
    public boolean isActionPowerSavingAvailable(Context context) {
        return true;
    }

    @Override
    public boolean isActionAutoStartAvailable(Context context) {
        return true;
    }

    @Override
    public boolean isActionNotificationAvailable(Context context) {
        return false;
    }

    @Override
    public Intent getActionPowerSaving(Context context) {
        Intent intent = ActionsUtils.createIntent();
        intent.setComponent(new ComponentName(ZTE_HEARTYSERVICE_PACKAGE_NAME,ZTE_HEARTYSERVICE_POWERSAVING_ACTIVITY));
        return intent;
    }

    @Override
    public Intent getActionAutoStart(Context context) {
        Intent intent = ActionsUtils.createIntent();
        intent.setComponent(new ComponentName(ZTE_HEARTYSERVICE_PACKAGE_NAME,ZTE_HEARTYSERVICE_AUTOSTART_ACTIVITY));
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
