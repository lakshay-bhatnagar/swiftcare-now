package com.example.swiftcare.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MedicineAdapter extends RecyclerView.Adapter<MedicineAdapter.ViewHolder> {

    private List<String> medicineList;
    private List<String> filteredList;

    public MedicineAdapter(List<String> medicineList) {
        this.medicineList = new ArrayList<>(medicineList);
        this.filteredList = new ArrayList<>(medicineList);
    }

    public void filter(String query) {
        filteredList.clear();
        if (query.isEmpty()) {
            filteredList.addAll(medicineList);
        } else {
            for (String medicine : medicineList) {
                if (medicine.toLowerCase().contains(query.toLowerCase())) {
                    filteredList.add(medicine);
                }
            }
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(filteredList.get(position));
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(View view) {
            super(view);
            textView = view.findViewById(android.R.id.text1);
        }
    }
}

