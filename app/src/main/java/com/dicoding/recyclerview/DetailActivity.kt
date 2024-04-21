package com.dicoding.recyclerview

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DetailActivity : AppCompatActivity(), View.OnClickListener {
    private val EXTRA_PHOTO = "nilai_foto"
    private val EXTRA_NAME = "nilai_nama"
    private val EXTRA_DESCRIPTION = "description"

    private lateinit var rvHeroes: RecyclerView
    private val list = ArrayList<Hero>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_detail)

        val fotoPahlawan = intent.getIntExtra(EXTRA_PHOTO, 0)
        val namaPahlawan = intent.getStringExtra(EXTRA_NAME)
        val judulDeskripsi = intent.getStringExtra(EXTRA_DESCRIPTION)

        val IVfotoPahlawan = findViewById<ImageView>(R.id.img_item_photo)
        val TVnamaPahlawan = findViewById<TextView>(R.id.tv_item_name)
        val TVjudulDeskripsi = findViewById<TextView>(R.id.tv_item_description)

        IVfotoPahlawan.setImageResource(fotoPahlawan)
        TVnamaPahlawan.text = namaPahlawan
        TVjudulDeskripsi.text = judulDeskripsi

        setupRecyclerView()

        val nama: TextView = findViewById(R.id.tv_item_name)
        nama.setOnClickListener(this)

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu2, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about_page -> {
                startActivity(Intent(this, ProfileActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.rv_heroes)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val listHeroAdapter = ListHeroAdapter(list)
        recyclerView.adapter = ListHeroAdapter(getRelatedHeroes())

        listHeroAdapter.setOnItemClickCallback(object : ListHeroAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Hero) {
                val pindahDataIntent = Intent(this@DetailActivity, DetailActivity::class.java)
                pindahDataIntent.putExtra("nilai_nama", data.name)
                pindahDataIntent.putExtra("nilai_foto", data.photo)
                pindahDataIntent.putExtra("description", data.description)
                startActivity(pindahDataIntent)
            }
        })
    }
    private fun showRecyclerList() {
        rvHeroes.layoutManager = LinearLayoutManager(this)
        val listHeroAdapter = ListHeroAdapter(list)
        rvHeroes.adapter = listHeroAdapter

        listHeroAdapter.setOnItemClickCallback(object : ListHeroAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Hero) {
                val pindahDataIntent = Intent(this@DetailActivity, DetailActivity::class.java)
                pindahDataIntent.putExtra("nilai_nama", data.name)
                pindahDataIntent.putExtra("nilai_foto", data.photo)
                pindahDataIntent.putExtra("description", data.description)
                startActivity(pindahDataIntent)
            }
        })
    }
    // Contoh fungsi untuk mendapatkan data terkait, ini perlu disesuaikan berdasarkan kebutuhan Anda
    private fun getRelatedHeroes(): ArrayList<Hero> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listHero = ArrayList<Hero>()
        for (i in dataName.indices) {
            val hero = Hero(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
            listHero.add(hero)
        }
        return listHero
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_item_name -> {
                val berpindah = Intent(this@DetailActivity, MainActivity::class.java)
                startActivity(berpindah)
            }

            R.id.tv_item_name -> {

            }
        }
    }


}