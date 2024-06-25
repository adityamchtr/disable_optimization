package id.mchtr.disable_optimization.devices;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import id.mchtr.disable_optimization.utils.ActionsUtils;
import id.mchtr.disable_optimization.utils.Manufacturer;

public class Oppo extends DeviceAbstract {
    private static final ComponentName[] OPPO_AUTOSTART = {new ComponentName("com.coloros.safecenter", "com.coloros.safecenter.permission.startup.StartupAppListActivity"),
            new ComponentName("com.coloros.safecenter", "com.coloros.safecenter.startupapp.StartupAppListActivity"),
            new ComponentName("com.oppo.safe", "com.oppo.safe.permission.startup.StartupAppListActivity"),
            new ComponentName("com.color.safecenter", "com.color.safecenter.permission.startup.StartupAppListActivity"),
            new ComponentName("com.color.safecenter", "com.color.safecenter.startupapp.StartupAppListActivity")};

    private static final ComponentName[] OPPO_POWER_SAVE = {new ComponentName("com.color.oppoguardelfr", "com.coloros.powermanager.fuelgaue.PowerConsumptionActivity"),
            new ComponentName("com.color.oppoguardelf", "com.coloros.powermanager.fuelgaue.PowerUsageModelActivity")};

    @Override
    public boolean isThatRom() {
        return Build.BRAND.equalsIgnoreCase(getDeviceManufacturer().toString()) ||
                Build.MANUFACTURER.equalsIgnoreCase(getDeviceManufacturer().toString()) ||
                Build.FINGERPRINT.toLowerCase().contains(getDeviceManufacturer().toString());
    }

    @Override
    public Manufacturer getDeviceManufacturer() {
        return Manufacturer.OPPO;
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
        for (ComponentName component : OPPO_POWER_SAVE) {
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
        for (ComponentName component : OPPO_AUTOSTART) {
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
