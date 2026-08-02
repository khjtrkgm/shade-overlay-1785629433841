package com.shadeoverlay;

import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {
    private final SharedPreferences sp;
    public Prefs(Context c) { sp = c.getSharedPreferences("shade", Context.MODE_PRIVATE); }
    public String effect() { return sp.getString("effect", "dim"); }
    public void effect(String v) { sp.edit().putString("effect", v).apply(); }
    public int color() { return sp.getInt("color", 0xFF000000); }
    public void color(int v) { sp.edit().putInt("color", v).apply(); }
    public int alpha() { return sp.getInt("alpha", 128); }
    public void alpha(int v) { sp.edit().putInt("alpha", v).apply(); }
    public boolean running() { return sp.getBoolean("running", false); }
    public void running(boolean v) { sp.edit().putBoolean("running", v).apply(); }
}
