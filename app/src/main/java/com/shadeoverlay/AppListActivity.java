package com.shadeoverlay;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AppListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle s) {
        super.onCreate(s);
        setContentView(R.layout.activity_app_list);
        RecyclerView rv = findViewById(R.id.recycler);
        rv.setLayoutManager(new LinearLayoutManager(this));
        final PackageManager pm = getPackageManager();
        List<ApplicationInfo> all = pm.getInstalledApplications(0);
        List<ApplicationInfo> res = new ArrayList<>();
        for (ApplicationInfo ai : all) {
            if (pm.getLaunchIntentForPackage(ai.packageName) != null) res.add(ai);
        }
        Collections.sort(res, new java.util.Comparator<ApplicationInfo>() {
            public int compare(ApplicationInfo a, ApplicationInfo b) {
                return pm.getApplicationLabel(a).toString().compareToIgnoreCase(pm.getApplicationLabel(b).toString());
            }
        });
        rv.setAdapter(new AppAdapter(res, pm));
    }
}
