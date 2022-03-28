package com.sbellanger.favorite.presentation.view

import androidx.recyclerview.widget.RecyclerView
import com.sbellanger.favorite.databinding.ViewFavoriteBinding
import com.sbellanger.favorite.domain.model.FavoriteEntity
import com.sbellanger.favorite.presentation.IFavoriteContract
import com.sbellanger.ui_kit.helper.clickWithDebounce

class FavoriteViewHolder(
    private val itemBinding: ViewFavoriteBinding,
    private val listener: IFavoriteContract.ViewEvent.IFavoriteListener?
) : RecyclerView.ViewHolder(itemBinding.root) {

    ///////////////////////////////////////////////////////////////////////////
    // PUBLIC API
    ///////////////////////////////////////////////////////////////////////////

    fun bind(favorite: FavoriteEntity) {
        with(itemBinding) {
            name.text = favorite.repositoryName
            description.text = favorite.description
            languageColor.setColorFilter(favorite.color)
            language.text = favorite.language
            issueCount.text = favorite.openedIssues.toString()
            star.setOnClickListener {
                listener?.onFavoriteRemovedClick(favorite.id)
            }
            root.clickWithDebounce {
                listener?.onFavoriteClick(favorite)
            }
        }
    }
}
