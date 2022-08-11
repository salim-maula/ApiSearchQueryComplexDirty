package com.salim.android.apisearchquerycomplexdirty.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.salim.android.apisearchquerycomplexdirty.data.DefinitionsItem
import com.salim.android.apisearchquerycomplexdirty.databinding.ItemDefinitionBinding

class DefinationAdapter : RecyclerView.Adapter<DefinationAdapter.DefinitionViewHolder>() {

    private val listDefinition : ArrayList<DefinitionsItem?> = ArrayList()

    class DefinitionViewHolder(val item: ItemDefinitionBinding) : RecyclerView.ViewHolder(item.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DefinitionViewHolder(
        ItemDefinitionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: DefinitionViewHolder, position: Int) {
        holder.item.apply {
            with(listDefinition[position]) {
                tvDefinition.text = this?.definition
            }
        }
    }

    override fun getItemCount() = listDefinition.size

    fun setDefinition(definition: List<DefinitionsItem?>?) {
        this.listDefinition.clear()
        if (definition != null){
            this.listDefinition.addAll(definition)
        }
    }

}