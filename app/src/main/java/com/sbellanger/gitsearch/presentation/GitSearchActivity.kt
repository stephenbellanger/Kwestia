package com.sbellanger.gitsearch.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.sbellanger.arch.activity.KtpBaseActivity
import com.sbellanger.arch.helper.hideKeyboard
import com.sbellanger.favorite.presentation.FavoriteFragment
import com.sbellanger.gitsearch.R
import com.sbellanger.gitsearch.databinding.MainActivityBinding
import com.sbellanger.presentation.RepositoryFragment
import com.sbellanger.ui_kit.helper.isFirstLevel
import toothpick.config.Module
import javax.inject.Inject

class GitSearchActivity : KtpBaseActivity<MainActivityBinding>() {

    companion object {
        private const val CURRENT_FRAGMENT = "CURRENT_FRAGMENT"
        private const val REPOSITORY_FRAGMENT_TAG = "REPOSITORY_FRAGMENT_TAG"
        private const val FAVORITE_FRAGMENT_TAG = "FAVORITE_FRAGMENT_TAG"
    }

    ///////////////////////////////////////////////////////////////////////////
    // DATA
    ///////////////////////////////////////////////////////////////////////////

    private var selectedFragment: Int = R.id.repository
    private var activeFragment: Fragment? = null

    private val repositoryFragment =
        supportFragmentManager.findFragmentByTag(REPOSITORY_FRAGMENT_TAG)?.let {
            it as RepositoryFragment
        } ?: RepositoryFragment.newInstance()

    private val favoriteFragment =
        supportFragmentManager.findFragmentByTag(FAVORITE_FRAGMENT_TAG)?.let {
            it as FavoriteFragment
        } ?: FavoriteFragment.newInstance()

    ///////////////////////////////////////////////////////////////////////////
    // CONFIGURATION
    ///////////////////////////////////////////////////////////////////////////

    @Inject
    override lateinit var binding: MainActivityBinding

    override val modules: Array<Module>
        get() = arrayOf(GitSearchActivityModule(this@GitSearchActivity))

    ///////////////////////////////////////////////////////////////////////////
    // LIFECYCLE
    ///////////////////////////////////////////////////////////////////////////

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager
            .addOnBackStackChangedListener {
                val isFirstLevel = supportFragmentManager.isFirstLevel()
                binding.navigationBar.visibility = if (isFirstLevel) View.VISIBLE else View.GONE
            }

        savedInstanceState?.let {
            selectedFragment = it.getInt(CURRENT_FRAGMENT, R.id.repository)
        }

        when (selectedFragment) {
            R.id.repository -> activeFragment = repositoryFragment
            R.id.favorite -> activeFragment = favoriteFragment
        }

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, repositoryFragment, REPOSITORY_FRAGMENT_TAG)
                .hide(repositoryFragment)
                .add(R.id.container, favoriteFragment, FAVORITE_FRAGMENT_TAG)
                .hide(favoriteFragment)
                .show(repositoryFragment)
                .commit()
        }

        binding.navigationBar.setOnItemSelectedListener {
            setCurrentFragment(it.itemId)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(CURRENT_FRAGMENT, selectedFragment)
    }

    ///////////////////////////////////////////////////////////////////////////
    // HELPER
    ///////////////////////////////////////////////////////////////////////////

    private fun setCurrentFragment(itemId: Int): Boolean {
        when (itemId) {
            R.id.repository -> {
                if (activeFragment is RepositoryFragment) return false
                else {
                    supportFragmentManager
                        .beginTransaction()
                        .hide(activeFragment!!)
                        .show(repositoryFragment)
                        .commit()
                }
                activeFragment = repositoryFragment
            }
            R.id.favorite -> {
                if (activeFragment is FavoriteFragment) return false
                else {
                    supportFragmentManager
                        .beginTransaction()
                        .hide(activeFragment!!)
                        .show(favoriteFragment)
                        .commit()
                }
                activeFragment = favoriteFragment
            }
        }
        hideKeyboard()
        return true
    }
}
