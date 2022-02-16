package com.harry.fibonacciseries

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.harry.fibonacciseries.adapter.RecyclerViewAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private var mRecyclerView: RecyclerView? = null
    private val recycler_view_items = 100 //No. of items added to recyclerview at a time
    private val TAG = MainActivity::class.qualifiedName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mLayoutManager = LinearLayoutManager(this)
        supportActionBar!!.setTitle("Fibonacci Fun")
        setContentView(R.layout.activity_main)
        mRecyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        mRecyclerView!!.layoutManager = mLayoutManager
        mRecyclerView!!.itemAnimator = DefaultItemAnimator()
        val recyclerViewAdapter = RecyclerViewAdapter(recycler_view_items);
        mRecyclerView!!.adapter = recyclerViewAdapter

        lifecycleScope.launch(Dispatchers.Unconfined) {
            withContext(Dispatchers.Default) {
                mRecyclerView!!.addOnScrollListener(object : RecyclerView.OnScrollListener(){
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)
                        Log.d(TAG, "last visible position: ${mLayoutManager.findLastCompletelyVisibleItemPosition()}, total count: ${mLayoutManager.itemCount}")
                        if (mLayoutManager.findLastCompletelyVisibleItemPosition() >= mLayoutManager.itemCount - 1) {
                            recyclerViewAdapter.addItems(itemsAddCount = recycler_view_items)
                        }
                    }
                })
            }

        }




    }
}