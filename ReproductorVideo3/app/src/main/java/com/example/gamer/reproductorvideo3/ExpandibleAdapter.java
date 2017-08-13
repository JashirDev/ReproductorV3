package com.example.gamer.reproductorvideo3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Gamer on 09/08/2017.
 */

public abstract class ExpandibleAdapter extends BaseExpandableListAdapter {
    private final ArrayList<String> categoria;
    private final Map<String , ArrayList<ContenidoLista>> mapchildren;// aca se almacena la categoria y los hijos(el contenido de la lista principal) que se va a desplegar
    private final Context contexto;
    public abstract void pasar(ContenidoLista lista);


    public ExpandibleAdapter(ArrayList<String> categoria, Map<String, ArrayList<ContenidoLista>> mapchildren, Context contexto) {
        this.categoria = categoria;
        this.mapchildren = mapchildren;
        this.contexto = contexto;

    }

    @Override
    //xtodo lo que se vea con grup hace referencia ala categoria es decir la parte celeste y los child al contenido de la lista (loque se despliega)
    public int getGroupCount() {
        return categoria.size(); // obtener cuantos grups tenemos
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mapchildren.get(categoria.get(groupPosition)).size();//obtenemos cuantos hijos tiene cada categoria(lo que se despliega)
    }

    @Override
    public Object getGroup(int groupPosition) {
        return categoria.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mapchildren.get(categoria.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override //aqui se implementan las vistas
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String titulocategoria=(String)getGroup(groupPosition);
        convertView = LayoutInflater.from(contexto).inflate(R.layout.lista_grupos_principales,null);
        TextView tvgroup= (TextView) convertView.findViewById(R.id.texto_lista_principal);
        tvgroup.setText(titulocategoria);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

       // Log.e("ExpadA","gp: "+groupPosition+" , cp: "+childPosition);
        convertView= LayoutInflater.from(contexto).inflate(R.layout.contenido_lista_principal,null);
        TextView tvchild= (TextView)convertView.findViewById(R.id.texto_contido);
        ArrayList<ContenidoLista> lista=mapchildren.get(categoria.get(groupPosition));
        //Log.e("Adapter","tamaño de lista: "+lista.size());
        final ContenidoLista item = lista.get(childPosition);
        tvchild.setText(item.getNombre());

        ImageView imagen=(ImageView) convertView.findViewById(R.id.imagenvideo);
        Glide.with(contexto).load(item.getImagenurl()).into(imagen);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pasar(item);
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }




}
