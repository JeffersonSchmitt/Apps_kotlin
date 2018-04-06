package io.github.jeffersonschmitt.kfeedreader

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Button
import com.pkmmte.pkrss.Article
import com.pkmmte.pkrss.Callback
import com.pkmmte.pkrss.PkRSS
import java.net.URI

class MainActivity : AppCompatActivity(), Callback {

    val listItens = arrayListOf<Item>()

    lateinit var listView: RecyclerView

    lateinit var adapter: RecyclerView.Adapter<ItemAdapter.itemViewHolder>



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnCarregarMais=findViewById(R.id.btnCarregarMais) as Button
        val layout = LinearLayoutManager(this)

        listView = findViewById(R.id.rv) as RecyclerView
        listView.layoutManager = layout

        adapter = ItemAdapter(listItens, this)
        listView.adapter = adapter

           PkRSS.with(this).load("https://rss.tecmundo.com.br/feed").callback(this).async();

        var nr: Int=10

        btnCarregarMais.setOnClickListener {

            listItens.clear()
           for (item in nr..nr+10){
               PkRSS.with(this).load("https://rss.tecmundo.com.br/feed/$item").callback(this).async();
           }
        }
    }

    fun onLoadFailed() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun onPreload() {
    }

    fun onLoaded(newArticles: MutableList<Article>?) {

        listItens.clear()
        newArticles?.mapTo(listItens) {
            Item(it.title, it.author, it.date, it.source, it.enclosure.url)
        }

        adapter.notifyDataSetChanged()

    }



    data class Item(val titulo: String, val autor: String, val data: Long, val link: Uri, val imagem: String)

}
