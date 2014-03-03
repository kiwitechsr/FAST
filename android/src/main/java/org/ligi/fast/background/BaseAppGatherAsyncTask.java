package org.ligi.fast.background;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.AsyncTask;

import android.util.Log;
import org.ligi.fast.model.AppInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Async-Task to Retrieve / Store Application Info needed by this App
 */
public class BaseAppGatherAsyncTask extends AsyncTask<Void, AppInfo, Void> {
    private final Context ctx;
    protected int appCount;
    protected List<AppInfo> appInfoList;
    private final List<AppInfo> oldAppList;

    public BaseAppGatherAsyncTask(Context ctx) {
        this(ctx, null);
    }

    public BaseAppGatherAsyncTask(Context ctx, List<AppInfo> oldAppList) {
        this.ctx = ctx;
        appInfoList = new ArrayList<AppInfo>();
        this.oldAppList = oldAppList;
    }

    @Override
    protected Void doInBackground(Void... params) {
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        try {
            List<ResolveInfo> resolveInfoList = ctx.getPackageManager().queryIntentActivities(mainIntent, 0);
            String[] excludeActivities = {
                    "com.android.mms.ui.ConversationList",
                    "com.google.android.youtube.videos.EntryPoint",
                    "com.google.android.apps.plus.phone.ConversationListActivity",
                    "com.google.android.gms.app.settings.GoogleSettingsActivity",
                    "com.android.launcher2.Launcher",
                    "com.motorola.genie.app.DashboardActivity",
                    "com.google.android.gms.games.ui.destination.main.MainActivity",
                    "com.motorola.frictionless.writer.MigrateLaunchActivity",
                    "com.motorola.contextaware.ui.GalleryActivity",
                    "com.painless.pc.settings.LaunchActivity",
                    "jp.u1aryz.products.metrostation.activity.MainActivity",
                    "in.vineetsirohi.customwidget.WidgetEditorActivityNewInterface",
                    "com.teslacoilsw.launcher.prime.NovaLauncherPrimeActivity",
                    "com.google.apps.dots.android.app.activity.CurrentsStartActivity",
                    "com.touchtype.LauncherActivity",
                    "com.tagstand.launcher.activity.MainActivity"};
            List<String> excludeList = Arrays.asList(excludeActivities);
            appCount = resolveInfoList.size();
            for (ResolveInfo info : resolveInfoList) {
                AppInfo actAppInfo = new AppInfo(ctx, info);
                if (!ctx.getPackageName().equals(actAppInfo.getPackageName())
                        && !excludeList.contains(actAppInfo.getActivityName())) { // ignore self
                    // TODO: SAMIR - remove unwanted packages
                    // Update call count from current index that is being used.
                    // This is because we may have updated the call count since the last time
                    // we saved the package list. An alternative would be to save the package list
                    // each time we leave
                    if (oldAppList != null) {
                        for(AppInfo oldInfo : oldAppList) {
                            if (oldInfo.getActivityName().equals(actAppInfo.getActivityName())) {
                                actAppInfo.setCallCount(oldInfo.getCallCount());
                                break;
                            }
                        }
                    }
                    appInfoList.add(actAppInfo);
                    publishProgress(actAppInfo);
                }
            }
        } catch (Exception e) {
            Log.d("SAMIR", "Exception occurred when getting activities skipping...!");
        }

        return null;
    }


}
