package com.example.desktop.hci.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.desktop.hci.Main;
import com.example.desktop.hci.Model.ToDo;
import com.example.desktop.hci.R;

import java.util.List;

class listItemViews extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener
{
    itemClickListener itemClickListener;
    TextView itemTitle, itemDescription;

    public listItemViews(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        itemView.setOnCreateContextMenuListener(this);

        itemTitle = (TextView)itemView.findViewById(R.id.itemTitle);
        itemDescription = (TextView)itemView.findViewById(R.id.itemDescription);
    }

    public void setItemClickListener(com.example.desktop.hci.Adapter.itemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view){
        itemClickListener.onClick(view, getAdapterPosition(), false);
    }

    @Override
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        contextMenu.setHeaderTitle("Select the action");
        contextMenu.add(0, 0, getAdapterPosition(), "DELETE");
    }
}

public class listItemAdapter extends RecyclerView.Adapter<listItemViews> {

    Main main;
    List<ToDo> todoList;

    public listItemAdapter(Main main, List<ToDo> todoList) {
        this.main = main;
        this.todoList = todoList;
    }

    @Override
    public listItemViews onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(main.getBaseContext());
        View view = inflater.inflate(R.layout.listitem, parent, false);
        return new listItemViews(view);
    }

    @Override
    public void onBindViewHolder(listItemViews holder, int position) {

        holder.itemTitle.setText(todoList.get(position).getTitle());
        holder.itemDescription.setText(todoList.get(position).getTitle());

        holder.setItemClickListener(new itemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isClickLong) {
                main.title.setText(todoList.get(position).getTitle());
                main.description.setText(todoList.get(position).getTitle());

                main.isUpdate = true;
                main.idUpdate = todoList.get(position).getId();
            }
        });
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }
}
