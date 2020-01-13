package com.project.segunfrancis.roomwordsamplekotlin


import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_new_word.*

/**
 * A simple [Fragment] subclass.
 */
class NewWordFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_new_word, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button_save.setOnClickListener {
            if (!TextUtils.isEmpty(edit_word.text)) {
                val word = edit_word.text.toString()
                val bundle = Bundle()
                bundle.putString(EXTRA_REPLY, word)
                findNavController().navigate(R.id.action_newWordFragment_to_mainFragment, bundle)
                Snackbar.make(view, "Word has been saved", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            } else {
                Toast.makeText(requireContext(), "Empty Note", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
    }
}
