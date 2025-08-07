package com.ifarm.porosh.myimdb

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import com.ifarm.porosh.myimdb.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/

        //supportActionBar?.title = "My IMDB"

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)

        val searchItem = menu!!.findItem(R.id.menu_search)
        val searchView = searchItem.actionView as SearchView?
        searchView!!.setSubmitButtonEnabled(true)

        searchView.setOnCloseListener {
            Log.i("mainactivity", "Search data: close");
            false
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(s: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(s: String?): Boolean {
                val sQuery = s
                Log.i("mainactivity","search $sQuery")
                /*quoteViewModel.searchQuotes(sQuery)
                    .observe(this@MainActivity, object : Observer<MutableList<Quotes?>?> {
                        override fun onChanged(quotes: MutableList<Quotes?>?) {
                            if (quotes != null && !quotes.isEmpty()) {
                                opViewModel.setSearchQuotes(quotes)
                                Log.i(
                                    "mainactivity",
                                    "Search data: " + quotes.get(0).getQuote() + quotes.get(0)
                                        .getTitle()
                                )
                            }
                        }
                    })*/
                return true
            }
        })

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.menu_search -> {
                Toast.makeText(this@MainActivity,"Search", Toast.LENGTH_SHORT).show()
            }
            R.id.menu_wishlist -> {
                Toast.makeText(this@MainActivity,"Wishlist", Toast.LENGTH_SHORT).show()
            }
            R.id.menu_filter -> {
                Toast.makeText(this@MainActivity,"Filter", Toast.LENGTH_SHORT).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}