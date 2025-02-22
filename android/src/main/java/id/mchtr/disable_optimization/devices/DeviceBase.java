package id.mchtr.disable_optimization.devices;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.DrawableRes;

import id.mchtr.disable_optimization.utils.Manufacturer;

public interface DeviceBase {
    boolean isThatRom();

    Manufacturer getDeviceManufacturer();

    boolean isActionPowerSavingAvailable(Context context);

    boolean isActionAutoStartAvailable(Context context);

    boolean isActionNotificationAvailable(Context context);

    boolean needToUseAlongWithActionDoseMode();

    Intent getActionPowerSaving(Context context);

    Intent getActionAutoStart(Context context);

    // FIXME IS IT REALLY NEEDED ? ==> REPLACE BY OTHER FUNCTION ?
    Intent getActionNotification(Context context);

    // TODO ADD FOR MEMORY OPTIMIZATION : https://github.com/00aj99/CRomAppWhitelist
    String getExtraDebugInformation(Context context);

    /**
     * Function common in all devices
     *
     * @param context the current context
     * @return the Intent to open the doze mode settings
     */
    Intent getActionDozeMode(Context context);

    boolean isActionDozeModeNotNecessary(Context context);
}
