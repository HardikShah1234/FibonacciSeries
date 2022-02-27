package com.harry.fibonacciseries

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.harry.fibonacciseries.adapter.RecyclerViewAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var mRecyclerView: RecyclerView
    private val recycler_view_items = 20 //No. of items added to recyclerview at a time
    private val TAG = MainActivity::class.qualifiedName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mLayoutManager = LinearLayoutManager(this)
        supportActionBar?.title = "Fibonacci Fun"
        setContentView(R.layout.activity_main)
        mRecyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        mRecyclerView.layoutManager = mLayoutManager
        mRecyclerView.itemAnimator = DefaultItemAnimator()
        val recyclerViewAdapter = RecyclerViewAdapter(recycler_view_items);
        mRecyclerView.adapter = recyclerViewAdapter

        mRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                Log.d(
                    TAG,
                    "last visible position: ${mLayoutManager.findLastCompletelyVisibleItemPosition()}, total count: ${mLayoutManager.itemCount}"
                )
                if (mLayoutManager.findLastCompletelyVisibleItemPosition() >= mLayoutManager.itemCount - 1) {
                    if (recycler_view_items.toULong() <= ULong.MAX_VALUE){
                        recyclerViewAdapter.addItems(itemsAddCount = recycler_view_items)
                    }
                }
            }
        })
    }
}