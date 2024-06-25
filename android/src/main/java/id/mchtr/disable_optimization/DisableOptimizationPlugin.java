package id.mchtr.disable_optimization;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import id.mchtr.disable_optimization.managers.KillerManager;
import id.mchtr.disable_optimization.utils.BatteryOptimizationUtil;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;

/**
 * DisableOptimizationPlugin
 */
public class DisableOptimizationPlugin implements FlutterPlugin, MethodCallHandler, ActivityAware {
    /// The MethodChannel that will the communication between Flutter and native Android
    ///
    /// This local reference serves to register the plugin with the Flutter Engine and unregister it
    /// when the Flutter Engine is detached from the Activity
    private MethodChannel channel;

    private Activity mActivity;
    private Context mContext;

    private final String TAG = "DisableOptimization";
    private static final String CHANNEL_NAME = "disable_optimization";

    @Override
    public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
        channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), CHANNEL_NAME);
        channel.setMethodCallHandler(this);
    }

    @Override
    public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
        switch (call.method) {
            case "isAutoStartAvailable":
                result.success(getAutoStart());
                break;
            case "isBatteryOptimizationAvailable":
                result.success(getBatteryOptimization());
                break;
            case "isBatteryOptimizationDisabled":
                result.success(BatteryOptimizationUtil.isIgnoringBatteryOptimizations(mContext));
                break;
            case "showEnableAutoStart":
                KillerManager.doActionAutoStart(mContext);
                break;
            case "showDisableBatteryOptimization":
                try {
                    showIgnoreBatteryPermissions();
                    result.success(true);
                } catch (Exception ex) {
                    Log.e(TAG, "Exception in showDisableBatteryOptimization. " + ex);
                    result.success(false);
                }
                break;
            default:
                result.notImplemented();
        }
    }

    @Override
    public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
        channel.setMethodCallHandler(null);
    }

    @Override
    public void onAttachedToActivity(@NonNull ActivityPluginBinding binding) {
        mActivity = binding.getActivity();
        mContext = mActivity.getApplicationContext();
        channel.setMethodCallHandler(this);
    }

    @Override
    public void onDetachedFromActivityForConfigChanges() {
        mActivity = null;
    }

    @Override
    public void onReattachedToActivityForConfigChanges(@NonNull ActivityPluginBinding binding) {
        mActivity = binding.getActivity();
    }

    @Override
    public void onDetachedFromActivity() {
        mActivity = null;
        channel.setMethodCallHandler(null);
    }

    private void showIgnoreBatteryPermissions() {
        final Intent ignoreBatteryOptimizationsIntent = BatteryOptimizationUtil.getIgnoreBatteryOptimizationsIntent(mContext);
        if (ignoreBatteryOptimizationsIntent != null) {
            mContext.startActivity(ignoreBatteryOptimizationsIntent);
        } else {
            Log.i(TAG, "Can't ignore the battery optimization as the intent is null");
        }
    }

    public boolean getAutoStart() {
        boolean isAutoStartAvailable = KillerManager.isActionAvailable(mContext, KillerManager.Actions.ACTION_AUTOSTART);
        return isAutoStartAvailable;
    }

    public boolean getBatteryOptimization() {
        boolean isBatteryOptimizationAvailable = KillerManager.isActionAvailable(mContext, KillerManager.Actions.ACTION_POWER_SAVING);
        return isBatteryOptimizationAvailable;
    }
}
