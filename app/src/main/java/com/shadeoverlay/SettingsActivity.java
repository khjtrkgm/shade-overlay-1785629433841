package com.shadeoverlay;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle s) {
        super.onCreate(s);
        setContentView(R.layout.activity_settings);
        final Prefs prefs = new Prefs(this);
        final ShaderOption[] opts = ShaderOption.all();
        List<String> names = new ArrayList<>();
        for (ShaderOption o : opts) names.add(o.name);
        Spinner sp = findViewById(R.id.spinner);
        ArrayAdapter<String> ad = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, names);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(ad);
        int sel = 0;
        for (int i = 0; i < opts.length; i++) if (opts[i].id.equals(prefs.effect())) sel = i;
        sp.setSelection(sel);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> p, View v, int pos, long id) { prefs.effect(opts[pos].id); }
            public void onNothingSelected(AdapterView<?> p) {}
        });
        SeekBar alpha = findViewById(R.id.alpha);
        alpha.setMax(255);
        alpha.setProgress(prefs.alpha());
        alpha.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar b, int p, boolean f) { prefs.alpha(p); }
            public void onStartTrackingTouch(SeekBar b) {}
            public void onStopTrackingTouch(SeekBar b) {}
        });
        int[] colors = {Color.BLACK, Color.WHITE, Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.CYAN, Color.MAGENTA, Color.GRAY};
        int[] ids = {R.id.c0, R.id.c1, R.id.c2, R.id.c3, R.id.c4, R.id.c5, R.id.c6, R.id.c7, R.id.c8};
        for (int i = 0; i < ids.length; i++) {
            final int col = colors[i];
            Button b = findViewById(ids[i]);
            b.setBackgroundColor(col);
            b.setOnClickListener(new View.OnClickListener() { public void onClick(View v) { prefs.color(col); }});
        }
    }
}
