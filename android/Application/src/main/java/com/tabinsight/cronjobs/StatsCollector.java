package com.tabinsight.cronjobs;

import android.app.Service;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.example.android.appusagestatistics.StatsUsageInterval;
import com.tabinsights.constants.WhitelisteedApps;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class StatsCollector extends Service {
    public StatsCollector() {
    }
    UsageStatsManager mUsageStatsManager;
    @Override
    public IBinder onBind(Intent intent) {

        mUsageStatsManager = (UsageStatsManager) getSystemService(Context.USAGE_STATS_SERVICE);
        // TODO: Return the communication channel to the service.
        HashMap<String, UsageStats> usageStatsMap = getUsageStatsMap();
        WhitelisteedApps whiteListedApps = new WhitelisteedApps();
        List<String> packageNames= whiteListedApps.getPackageNames();
        List<String> log = new ArrayList<>();
        for(String name: packageNames){
            UsageStats usageStat = usageStatsMap.get(name);
            Log.d("TEST123",usageStat.getLastTimeUsed()+"\tdummydevice"+usageStat.getPackageName()+"\t"+usageStat.getTotalTimeInForeground());
        }
        //throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }


    @Override
    public void onCreate() {
        mUsageStatsManager = (UsageStatsManager) getSystemService(Context.USAGE_STATS_SERVICE);
        // TODO: Return the communication channel to the service.
        HashMap<String, UsageStats> usageStatsMap = getUsageStatsMap();
        WhitelisteedApps whiteListedApps = new WhitelisteedApps();
        List<String> packageNames= whiteListedApps.getPackageNames();
        List<String> log = new ArrayList<>();
        for(String name: packageNames){
            if(!usageStatsMap.containsKey(name)){
                continue;
            }
            UsageStats usageStat = usageStatsMap.get(name);
            Log.d("TEST123",usageStat.getLastTimeUsed()+"\tdummydevice"+usageStat.getPackageName()+"\t"+usageStat.getTotalTimeInForeground());
        }
    }

    private HashMap<String, UsageStats> getUsageStatsMap(){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -1);

        List<UsageStats> queryUsageStats = mUsageStatsManager
                .queryUsageStats(0, cal.getTimeInMillis(),
                        System.currentTimeMillis());
        HashMap<String, UsageStats> usageStatsMap = new HashMap<>();
        for(UsageStats qsm: queryUsageStats){
            usageStatsMap.put(qsm.getPackageName(),qsm);
        }
        return usageStatsMap;
    }
}
