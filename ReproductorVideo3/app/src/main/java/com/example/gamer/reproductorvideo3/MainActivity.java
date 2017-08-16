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
        ArrayList<ContenidoLista> listavideosnoticia= new ArrayList<>();
        ArrayList<ContenidoLista> listavideosdeporte= new ArrayList<>();
        listacategoria.add("Musica");
        listacategoria.add("Noticias");
        listacategoria.add("Deportes");


        listavideos.add(new ContenidoLista("VIDEO 1 "
                ,"https://i.ytimg.com/vi/CJinWua98NA/maxresdefault.jpg"
                ,"CJinWua98NA"));
        listavideos.add(new ContenidoLista("VIDEO 2"
                , "http://images.eonline.com/eol_images/Entire_Site/2017530//rs_569x355-170630055438-Captura_de_pantalla_2017-06-30_a_las_09.34.59.png"
                ,"wnJ6LuUFpMo"));
        listavideos.add(new ContenidoLista("VIDEO 3"
                , "https://i.ytimg.com/vi/Fp5_Ezoj60U/hqdefault.jpg"
                ,"Fp5_Ezoj60U"));

        listavideosnoticia.add(new ContenidoLista("VIDEO 1"
                ,"http://tv.milenio.com/milenio_noticias/donald_trump-rechazo-ataques-racistas-virginia-milenio-noticias_MILVID20170815_0017_10.jpg"
                ,"Q1zv6iiBNcQ"));
        listavideosnoticia.add(new ContenidoLista("VIDEO 2"
                ,"http://eleconomista.com.mx/files/imagecache/Soft/emvideo-youtube-APwZa9T6hP8_0.jpg"
                ,"APwZa9T6hP8"));
        listavideosnoticia.add(new ContenidoLista("VIDEO 3"
                ,"http://p-gruporpp-media.s3.amazonaws.com/2016/08/15/18669-piscoinforme-219552mp4_219553.png"
                ,"sKDYJTP_Ik8"));


        listavideosdeporte.add(new ContenidoLista("VIDEO 1"
                ,"https://media.metrolatam.com/2017/08/15/resultadopartidohoffenheimliverpoolpreviachampionsleague-1200x600.jpg"
                ,"m-mnx0YVDgU"));
        listavideosdeporte.add(new ContenidoLista("VIDEO 1"
                ,"https://i.ytimg.com/vi/P1Vb70mTDnU/maxresdefault.jpg"
                ,"y-ROoLQ5CuI"));
        listavideosdeporte.add(new ContenidoLista("VIDEO 1"
                ,"https://i.ytimg.com/vi/HL8yozeXaHg/maxresdefault.jpg"
                ,"HL8yozeXaHg"));


        mapchild.put(listacategoria.get(0),listavideos);
        mapchild.put(listacategoria.get(1),listavideosnoticia);
        mapchild.put(listacategoria.get(2),listavideosdeporte);



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
