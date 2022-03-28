package com.sbellanger.favorite.presentation.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sbellanger.favorite.databinding.ViewFavoriteBinding
import com.sbellanger.favorite.domain.model.FavoriteEntity
import com.sbellanger.favorite.presentation.IFavoriteContract
import javax.inject.Inject

class FavoriteAdapter @Inject constructor() : RecyclerView.Adapter<FavoriteViewHolder>() {

    ///////////////////////////////////////////////////////////////////////////
    // DATA
    ///////////////////////////////////////////////////////////////////////////

    private val favorites: MutableList<FavoriteEntity> = mutableListOf()
    private var listener: IFavoriteContract.ViewEvent.IFavoriteListener? = null

    ///////////////////////////////////////////////////////////////////////////
    // SPECIALIZATION
    ///////////////////////////////////////////////////////////////////////////

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val itemBinding = ViewFavoriteBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteViewHolder(itemBinding, listener)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(favorites[position])
    }

    override fun getItemCount(): Int {
        return favorites.size
    }

    ///////////////////////////////////////////////////////////////////////////
    // PUBLIC API
    ///////////////////////////////////////////////////////////////////////////

    @SuppressLint("NotifyDataSetChanged")
    fun setData(favorites: List<FavoriteEntity>) {
        this.favorites.apply {
            clear()
            addAll(favorites)
        }
        notifyDataSetChanged()
    }

    fun clear() {
        favorites.clear()
        notifyDataSetChanged()
    }

    fun setListener(listener: IFavoriteContract.ViewEvent.IFavoriteListener) {
        this.listener = listener
    }
}
