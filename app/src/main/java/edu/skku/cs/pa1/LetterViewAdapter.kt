package edu.skku.cs.pa1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat

class LetterViewAdapter(val context: Context, val items : ArrayList<Letter>): BaseAdapter() {
    override fun getCount(): Int {
        return items.size
    }
    override fun getItem(position: Int): Any {
        return items.get(position)
    }
    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(i: Int, cvtView: View?, parent: ViewGroup?): View {
        val inflater : LayoutInflater =
            LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.letter, null)
        val word = items.get(i).word
        val color = items.get(i).color
        var letterView = view.findViewById<TextView>(R.id.letterView)
        letterView.text = word
        when(color[0]){
            'g'->{
                letterView.setTextColor(ContextCompat.getColor(context, R.color.black))
                letterView.setBackgroundColor(ContextCompat.getColor(context, R.color.GreenBackground))
            }
            'y'->{
                letterView.setTextColor(ContextCompat.getColor(context, R.color.black))
                letterView.setBackgroundColor(ContextCompat.getColor(context, R.color.YellowBackground))
            }
            'b'->{
                letterView.setTextColor(ContextCompat.getColor(context, R.color.white))
                letterView.setBackgroundColor(ContextCompat.getColor(context, R.color.GrayBackground))
            }
        }

        return view
    }

}
