package id.mchtr.disable_optimization.devices;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import id.mchtr.disable_optimization.utils.ActionsUtils;
import id.mchtr.disable_optimization.utils.Manufacturer;

public class Vivo extends DeviceAbstract {
    private static final ComponentName[] VIVO_AUTOSTART = {new ComponentName("com.iqoo.secure", "com.iqoo.secure.ui.phoneoptimize.AddWhiteListActivity"),
            new ComponentName("com.iqoo.secure", "com.iqoo.secure.ui.phoneoptimize.BgStartUpManager"),
            new ComponentName("com.vivo.permissionmanager", "com.vivo.permissionmanager.activity.BgStartUpManagerActivity")};

    private static final ComponentName[] VIVO_POWER_SAVE = {new ComponentName("com.vivo.abe", "com.vivo.applicationbehaviorengine.ui.ExcessivePowerManagerActivity")};

    // "com.vivo.abe", "com.vivo.applicationbehaviorengine.ui.ExcessivePowerManagerActivity"
    //com.iqoo.secure.MainGuideActivity ??
    @Override
    public boolean isThatRom() {
        return Build.BRAND.equalsIgnoreCase(getDeviceManufacturer().toString()) ||
                Build.MANUFACTURER.equalsIgnoreCase(getDeviceManufacturer().toString()) ||
                Build.FINGERPRINT.toLowerCase().contains(getDeviceManufacturer().toString());
    }

    @Override
    public Manufacturer getDeviceManufacturer() {
        return Manufacturer.VIVO;
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
        for (ComponentName component : VIVO_POWER_SAVE) {
            if (ActionsUtils.isIntentAvailable(context, component)) {
                intent = ActionsUtils.createIntent();
                intent.setComponent(component);
                break;
            }
        }
        return intent;
    }

    @Override
    public Intent getActionAutoStart(Context context) {
        Intent intent = null;
        for (ComponentName component : VIVO_AUTOSTART) {
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
