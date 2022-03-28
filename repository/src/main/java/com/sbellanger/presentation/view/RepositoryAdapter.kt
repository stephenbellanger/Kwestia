package com.sbellanger.presentation.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sbellanger.domain.model.RepositoryEntity
import com.sbellanger.presentation.IRepositoryContract
import com.sbellanger.repository.databinding.ViewRepositoryBinding
import javax.inject.Inject
import kotlin.properties.Delegates

class RepositoryAdapter @Inject constructor() : RecyclerView.Adapter<RepositoryViewHolder>() {

    ///////////////////////////////////////////////////////////////////////////
    // DATA
    ///////////////////////////////////////////////////////////////////////////

    private var repositories: List<RepositoryEntity> by Delegates.observable(emptyList()) { _, oldValue, newValue ->
        val callback = getDiffUtilCallback(oldValue, newValue)
        val diffResult = DiffUtil.calculateDiff(callback)
        diffResult.dispatchUpdatesTo(this)
    }

    private var listener: IRepositoryContract.ViewEvent.IRepositoryListener? = null

    ///////////////////////////////////////////////////////////////////////////
    // SPECIALIZATION
    ///////////////////////////////////////////////////////////////////////////

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val itemBinding = ViewRepositoryBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return RepositoryViewHolder(itemBinding, listener)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bind(repositories[position])
    }

    override fun getItemCount(): Int {
        return repositories.size
    }

    ///////////////////////////////////////////////////////////////////////////
    // PUBLIC API
    ///////////////////////////////////////////////////////////////////////////

    @SuppressLint("NotifyDataSetChanged")
    fun setData(repositories: List<RepositoryEntity>) {
        this.repositories = repositories
        notifyDataSetChanged()
    }

    fun setListener(listener: IRepositoryContract.ViewEvent.IRepositoryListener) {
        this.listener = listener
    }

    fun clear() {
        this.repositories = emptyList()
        notifyDataSetChanged()
    }

    ///////////////////////////////////////////////////////////////////////////
    // HELPER
    ///////////////////////////////////////////////////////////////////////////

    private fun getDiffUtilCallback(
        oldList: List<RepositoryEntity>,
        newList: List<RepositoryEntity>
    ) = object : DiffUtil.Callback() {

        override fun getOldListSize() = oldList.size

        override fun getNewListSize() = newList.size

        @SuppressWarnings("LongMethod")
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldItem = oldList[oldItemPosition]
            val newItem = newList[newItemPosition]

            if (oldItem.javaClass != newItem.javaClass) return false

            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldItem = oldList[oldItemPosition]
            val newItem = newList[newItemPosition]
            if (oldItem.javaClass != newItem.javaClass) return false

            return oldItem == newItem
        }
    }
}
