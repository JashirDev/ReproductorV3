package com.example.gamer.reproductorvideo3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ExpandableListView miexpandible;
    ExpandibleAdapter miadapter;
    ArrayList<String> listacategoria;
    Map<String , ArrayList<ContenidoLista>> mapchild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        miexpandible= (ExpandableListView)findViewById(R.id.expandiblelv);
        listacategoria= new ArrayList<>();
        mapchild = new HashMap<>();
        caragrdatos();
    }
    private void caragrdatos(){
        //creando las listas de categorias
        ArrayList<ContenidoLista> listavideos= new ArrayList<>();
        listacategoria.add("yotube");
        // listacategoria.add("vimeo");


        listavideos.add(new ContenidoLista("VIDEO 1 "
                ,"https://i.ytimg.com/vi/CJinWua98NA/maxresdefault.jpg"
                ,"CJinWua98NA"));
        listavideos.add(new ContenidoLista("VIDEO 2"
                , "http://images.eonline.com/eol_images/Entire_Site/2017530//rs_569x355-170630055438-Captura_de_pantalla_2017-06-30_a_las_09.34.59.png"
                ,"wnJ6LuUFpMo"));
        listavideos.add(new ContenidoLista("VIDEO 3"
                , "https://i.ytimg.com/vi/Fp5_Ezoj60U/hqdefault.jpg"
                ,"Fp5_Ezoj60U"));


        mapchild.put(listacategoria.get(0),listavideos);



        miadapter = new ExpandibleAdapter(listacategoria, mapchild, this) {
            @Override
            public void pasar(ContenidoLista lista) {
                Intent i = new Intent(MainActivity.this, Youtube.class);
                i.putExtra("videourl",lista.getUrlvideo());
                startActivity(i);
            }
        };
        miexpandible.setAdapter(miadapter);
    }
}
