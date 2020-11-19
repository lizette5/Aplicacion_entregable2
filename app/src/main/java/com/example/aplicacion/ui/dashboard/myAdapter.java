package com.example.aplicacion.ui.dashboard;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.view.*;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplicacion.MainActivity;
import com.example.aplicacion.R;
import android.content.Context;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> implements View.OnClickListener{
    private String[] mDataset;
    private List<MusicElemnt>mData;
    private LayoutInflater mInflater;
    private Context context;
    private View.OnClickListener listener;
    @Override
    public void onClick(View view) {
        if(listener!=null){
            listener.onClick(view);
        }
    }

    // Proporcione una referencia a las vistas para cada elemento de datos
    // Los elementos de datos complejos pueden necesitar más de una vista por elemento, y
    // proporciona acceso a todas las vistas para un elemento de datos en un titular de vista
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView iconImage;
        TextView name, artista;
        // each data item is just a string in this case
        public TextView textView;
        public MyViewHolder(View v) {
            super(v);
            iconImage = itemView.findViewById(R.id.imagenCard);
            name = itemView.findViewById(R.id.nameCard);
            artista = itemView.findViewById(R.id.artistaCard);

        }
        void bindData(final MusicElemnt item){
            //iconImage.setColorFilter(Color.parseColor(item.getArtist()));
            name.setText(item.getName());
            artista.setText(item.getArtist());

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<MusicElemnt>myDataset, Context context ) {
        this.mData = myDataset;
        this.context = context;
        mInflater = LayoutInflater.from(context);

    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        View view = mInflater.inflate(R.layout.list_music_element, null);
        view.setOnClickListener(this);
        return new MyAdapter.MyViewHolder(view);

        // create a new view
        /*TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_music, parent, false);
            //demás
        MyViewHolder vh = new MyViewHolder(v);
        return vh;*/
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        //holder.textView.setText(mDataset[position]);
        holder.bindData(mData.get(position));
    }

    public void setitem(List<MusicElemnt>items){
        mData = items;
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mData.size();
    }


}
