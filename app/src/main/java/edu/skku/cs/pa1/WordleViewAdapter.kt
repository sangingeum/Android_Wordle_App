package edu.skku.cs.pa1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat

class WordleViewAdapter(val context: Context, val items : ArrayList<Wordle>): BaseAdapter() {
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
        val view: View = inflater.inflate(R.layout.wordle, null)
        val word = items.get(i).word
        val color = items.get(i).color
        var views = arrayListOf<TextView>()
        views.add(view.findViewById<TextView>(R.id.WordleLetterView1))
        views.add(view.findViewById<TextView>(R.id.WordleLetterView2))
        views.add(view.findViewById<TextView>(R.id.WordleLetterView3))
        views.add(view.findViewById<TextView>(R.id.WordleLetterView4))
        views.add(view.findViewById<TextView>(R.id.WordleLetterView5))

        for(i in 0..4){
            var curLetter = views[i]
            curLetter.text = "" + word[i]
            val curColor = color[i]
            when(curColor){
                'g'->{
                    curLetter.setTextColor(ContextCompat.getColor(context, R.color.black))
                    curLetter.setBackgroundColor(ContextCompat.getColor(context, R.color.GreenBackground))
                    }
                'y'->{
                    curLetter.setTextColor(ContextCompat.getColor(context, R.color.black))
                    curLetter.setBackgroundColor(ContextCompat.getColor(context, R.color.YellowBackground))
                }
                'b'->{
                    curLetter.setTextColor(ContextCompat.getColor(context, R.color.white))
                    curLetter.setBackgroundColor(ContextCompat.getColor(context, R.color.GrayBackground))
                }
            }
        }

        return view
    }

}
