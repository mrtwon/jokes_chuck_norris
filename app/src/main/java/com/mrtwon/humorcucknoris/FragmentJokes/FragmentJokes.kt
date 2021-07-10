package com.mrtwon.humorcucknoris.FragmentJokes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.mrtwon.humorcucknoris.MainActivity
import com.mrtwon.humorcucknoris.R
import com.mrtwon.humorcucknoris.Retrofit.JokeData
import com.mrtwon.humorcucknoris.Retrofit.JokesResponse

class FragmentJokes: Fragment() {

    /* viewModel */ val viewModel: JokesViewModel by lazy { ViewModelProvider(this).get(JokesViewModel::class.java) }
    val jokesList = arrayListOf<JokeData>()
    lateinit var recyclerView: RecyclerView
    lateinit var searchByCount: TextInputEditText

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.fragment_jokes, container, false)
        recyclerView = view.findViewById(R.id.recycler_view)
        searchByCount = view.findViewById(R.id.search_jokes)
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        recyclerView.adapter = Adapter(jokesList)
        return view
    }

    // subscribing listeners onStart
    override fun onStart() {
        listenerSearchJokes()
        observerJokesList()
        observerError()
        super.onStart()
    }

    // listener for list jokes, response from the server
    fun observerJokesList(){
        viewModel.jokesListLiveData.observe(viewLifecycleOwner, {
            jokesList.clear()
            jokesList.addAll(it)
            recyclerView.adapter?.notifyDataSetChanged()
        })
    }
    // listener for error livedata, if an error occurs during the request
    fun observerError(){
        viewModel.errorLiveData.observe(viewLifecycleOwner){
            if(it){
                Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                viewModel.errorLiveData.value = false
            }
        }
    }
    // listener for edittext
    fun listenerSearchJokes(){
            searchByCount.setOnEditorActionListener { v, actionId, event ->
                if(actionId == EditorInfo.IME_ACTION_NEXT){
                    val limit = v.text.toString().toInt()
                    viewModel.getJokesLimit(limit)
                    return@setOnEditorActionListener true
                }
                false
            }
        }
    // viewholder for adapter
    inner class ViewHolder(private val itemView: View): RecyclerView.ViewHolder(itemView){
        val joke_textView: TextView = itemView.findViewById(R.id.joke)
        fun bind(jokeElement: JokeData){
            joke_textView.text = jokeElement.joke
        }
    }
    // nested class with the implementation of the adapter class
    inner class Adapter(val list: List<JokeData>): RecyclerView.Adapter<ViewHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = layoutInflater.inflate(R.layout.one_joke, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(list[position])
        }

        override fun getItemCount(): Int {
            return list.size
        }

    }
}