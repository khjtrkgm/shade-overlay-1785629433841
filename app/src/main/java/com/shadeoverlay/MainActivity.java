package com.shadeoverlay;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle s) {
        super.onCreate(s);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_apps).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { startActivity(new Intent(MainActivity.this, AppListActivity.class)); }
        });
        findViewById(R.id.btn_settings).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { startActivity(new Intent(MainActivity.this, SettingsActivity.class)); }
        });
        findViewById(R.id.btn_toggle).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { toggleOverlay(); }
        });
        updateToggleLabel();
    }

    private void updateToggleLabel() {
        Button b = findViewById(R.id.btn_toggle);
        b.setText(new Prefs(this).running() ? "إيقاف الطبقة" : "تشغيل الطبقة");
    }

    private void toggleOverlay() {
        Prefs p = new Prefs(this);
        if (p.running()) {
            stopService(new Intent(this, OverlayService.class));
            p.running(false);
        } else {
            if (Build.VERSION.SDK_INT >= 23 && !Settings.canDrawOverlays(this)) {
                Toast.makeText(this, "امنح اذن العرض فوق التطبيقات", Toast.LENGTH_LONG).show();
                Intent i = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
                startActivity(i);
                return;
            }
            if (Build.VERSION.SDK_INT >= 33 && checkSelfPermission("android.permission.POST_NOTIFICATIONS") != 0) {
                requestPermissions(new String[]{"android.permission.POST_NOTIFICATIONS"}, 1);
            }
            startForegroundService(new Intent(this, OverlayService.class));
            p.running(true);
        }
        updateToggleLabel();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateToggleLabel();
    }
}
