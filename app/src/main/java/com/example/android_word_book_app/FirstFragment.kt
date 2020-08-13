package com.example.android_word_book_app

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android_word_book_app.adapter.ListAdapter
import com.example.android_word_book_app.data.WordViewModel
import kotlinx.android.synthetic.main.fragment_first.view.*

class FirstFragment : Fragment() {

    private lateinit var mWordViewModel: WordViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_first, container, false)

        // RecyclerView
        val adapter = ListAdapter()
        val recyclerView = view.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val helper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false;
            }
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val word = adapter.getData(position)
                word.word
                Toast.makeText(requireContext(),  word.word + " を削除しました", Toast.LENGTH_SHORT).show()
                mWordViewModel.deleteWord(word)
            }
        })
        helper.attachToRecyclerView(recyclerView)


        // UIををデータに接続する
        mWordViewModel = ViewModelProvider(this).get(WordViewModel::class.java)

        mWordViewModel.readAllData.observe(viewLifecycleOwner, Observer { word ->
            adapter.setData(word)
        })

        view.fab.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_delete) {
            deleteAllWords()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllWords() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mWordViewModel.deleteAllWords()
            Toast.makeText(requireContext(), "単語を全て削除しました", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("単語をすべて削除しますか？")
        builder.setMessage("単語を削除したら戻すことはできません。よろしいですか？")
        builder.create().show()

    }
}
