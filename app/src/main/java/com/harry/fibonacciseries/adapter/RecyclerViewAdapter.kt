package com.harry.fibonacciseries.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.github.satoshun.coroutine.autodispose.view.autoDisposeScope
import com.harry.fibonacciseries.FibonacciCalculation
import com.harry.fibonacciseries.R
import kotlinx.coroutines.*

class RecyclerViewAdapter(private var count: Int) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TAG = RecyclerViewAdapter::class.qualifiedName

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.fibonacci_list_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as MyViewHolder
        holder.cell_no.text = "Cell" + (position + 1)
        holder.itemView.autoDisposeScope.launch(Dispatchers.Main) {
            if (position == 0 || position == 1) {
                holder.fibonacci_no.text = position.toString()
            }
            else {
                holder.fibonacci_no.text =
                    FibonacciCalculation.fibTail(position, 0.toULong(), 1.toULong()).toString()
            }
        }
    }
    fun addItems(itemsAddCount: Int) {
        count += itemsAddCount
        notifyItemRangeInserted(count - itemsAddCount, count)
    }

    override fun getItemCount(): Int {
        return count
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        var cell_no: TextView
        var fibonacci_no: TextView

        init {
            itemView.setOnClickListener(this)
            cell_no = itemView.findViewById(R.id.cell_no) as TextView
            fibonacci_no = itemView.findViewById(R.id.fibonacci_no) as TextView
        }

        override fun onClick(p0: View?) {
            Log.d(TAG, "onClick " + cell_no.text)
            Toast.makeText(itemView.context, "onClick " + cell_no.text, Toast.LENGTH_SHORT).show()
        }

    }

}