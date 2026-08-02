package com.shadeoverlay;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class AppAdapter extends RecyclerView.Adapter<AppAdapter.Holder> {
    private final List<ApplicationInfo> items;
    private final PackageManager pm;
    public AppAdapter(List<ApplicationInfo> items, PackageManager pm) {
        this.items = items; this.pm = pm;
    }
    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_app, parent, false);
        return new Holder(v);
    }
    @Override
    public void onBindViewHolder(Holder h, int position) {
        ApplicationInfo ai = items.get(position);
        try { h.label.setText(pm.getApplicationLabel(ai)); } catch (Exception e) { h.label.setText(ai.packageName); }
        try { h.icon.setImageDrawable(pm.getApplicationIcon(ai)); } catch (Exception e) {}
        h.pkg.setText(ai.packageName);
    }
    @Override
    public int getItemCount() { return items == null ? 0 : items.size(); }
    static class Holder extends RecyclerView.ViewHolder {
        ImageView icon; TextView label; TextView pkg;
        Holder(View v) { super(v); icon = v.findViewById(R.id.icon); label = v.findViewById(R.id.label); pkg = v.findViewById(R.id.pkg); }
    }
}
