package com.sbellanger.presentation.view

import androidx.recyclerview.widget.RecyclerView
import com.sbellanger.domain.model.RepositoryEntity
import com.sbellanger.presentation.IRepositoryContract
import com.sbellanger.repository.databinding.ViewRepositoryBinding
import com.sbellanger.ui_kit.R
import com.sbellanger.ui_kit.helper.clickWithDebounce

class RepositoryViewHolder(
    private val itemBinding: ViewRepositoryBinding,
    private val listener: IRepositoryContract.ViewEvent.IRepositoryListener?
) : RecyclerView.ViewHolder(itemBinding.root) {

    ///////////////////////////////////////////////////////////////////////////
    // DATA
    ///////////////////////////////////////////////////////////////////////////

    private var isFavorite: Boolean = false

    ///////////////////////////////////////////////////////////////////////////
    // PUBLIC API
    ///////////////////////////////////////////////////////////////////////////

    fun bind(repository: RepositoryEntity) {
        with(itemBinding) {
            isFavorite = repository.isFavorite
            name.text = repository.name
            description.text = repository.description
            issueCount.text = repository.openIssuesCount.toString()
            star.apply {
                if (isFavorite) setImageResource(R.drawable.ic_star)
                else setImageResource(R.drawable.ic_empty_star)
                setOnClickListener {
                    if (isFavorite) {
                        isFavorite = false
                        setImageResource(R.drawable.ic_empty_star)
                    } else {
                        isFavorite = true
                        setImageResource(R.drawable.ic_star)
                    }
                    listener?.onFavoriteClicked(repository, isFavorite)
                }
                root.clickWithDebounce {
                    listener?.onRepositoryClicked(repository)
                }
            }
        }
    }
}
