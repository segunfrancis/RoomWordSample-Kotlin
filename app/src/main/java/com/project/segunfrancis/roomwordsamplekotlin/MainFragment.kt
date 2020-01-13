package com.project.segunfrancis.roomwordsamplekotlin


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * A simple [Fragment] subclass.
 */

class MainFragment : Fragment() {

    private lateinit var wordViewModel: WordViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_main, container, false)
        val recyclerView = root.findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = WordListAdapter(requireContext())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        wordViewModel = ViewModelProvider(this).get(WordViewModel::class.java)
        wordViewModel.allWords.observe(viewLifecycleOwner, Observer { words ->
            // Update the cached copy pf the words in the adapter
            words.let { adapter.setWords(it) }
        })
        if (arguments != null) {
            arguments?.getString(NewWordFragment.EXTRA_REPLY)?.let {
                val word = Word(it)
                wordViewModel.insert(word)
            }
        }
        return root
    }
}
