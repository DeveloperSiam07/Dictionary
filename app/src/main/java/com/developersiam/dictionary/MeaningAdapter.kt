package com.developersiam.dictionary

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.developersiam.dictionary.databinding.MeaningRecyclerRowBinding

class MeaningAdapter(private var meaningList: List<Meaning>): RecyclerView.Adapter<MeaningAdapter.meaningViewHolder>() {
    class meaningViewHolder(private val binding: MeaningRecyclerRowBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(meaning: Meaning){
            binding.partsOfspeechTV.text = meaning.partOfSpeech
            binding.definitionTV.text = meaning.definitions.joinToString("\n\n") {
                var currentIndex = meaning.definitions.indexOf(it)
                (currentIndex+1).toString()+". "+it.definition.toString()
            }

            if (meaning.synonyms.isEmpty()){
                binding.synonym.visibility = View.INVISIBLE
                binding.synonymTV.visibility = View.INVISIBLE
            }else{
                binding.synonym.visibility = View.VISIBLE
                binding.synonymTV.visibility = View.VISIBLE
                binding.synonymTV.text = meaning.synonyms.joinToString(", ")
            }


            if (meaning.antonyms.isEmpty()){
                binding.antonym.visibility = View.INVISIBLE
                binding.antonymTV.visibility = View.INVISIBLE
            }else{
                binding.antonym.visibility = View.VISIBLE
                binding.antonymTV.visibility = View.VISIBLE
                binding.antonymTV.text = meaning.antonyms.joinToString(", ")
            }

        }
    }

    fun updateNewData(newMeaningList: List<Meaning>){
        meaningList = newMeaningList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): meaningViewHolder {
        val binding: MeaningRecyclerRowBinding
        binding = MeaningRecyclerRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return meaningViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return meaningList.size
    }

    override fun onBindViewHolder(holder: meaningViewHolder, position: Int) {
        holder.bind(meaningList[position])
    }
}