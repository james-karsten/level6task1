package com.example.level6task1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel
    private val colors = arrayListOf<ColorItem>()
    private val colorAdapter = ColorAdapter(colors) { colorItem ->
        onColorClick(colorItem)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
    }

    private fun initViews() {
        rvColors.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rvColors.adapter = colorAdapter

        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        viewModel.colorItems.observe(this, Observer {
            colors.clear()
            colors.addAll(it)
            colorAdapter.notifyDataSetChanged()
        })
    }

    private fun onColorClick(colorItem: ColorItem) {
        Toast.makeText(this, "This color is: ${colorItem.name}", Toast.LENGTH_SHORT).show()
    }
}
