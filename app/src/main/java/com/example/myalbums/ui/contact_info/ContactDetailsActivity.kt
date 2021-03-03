package com.example.myalbums.ui.contact_info

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.myalbums.R
import com.example.myalbums.databinding.ActivityContactDetailsBinding
import com.example.myalbums.di.DisposableActivity
import com.example.myalbums.utils.State
import com.example.myalbums.utils.subscribeOnMainThread
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContactDetailsActivity : DisposableActivity() {

    private lateinit var binding: ActivityContactDetailsBinding
    private val viewModel: ContactDetailsViewModel by viewModel<ContactDetailsViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_contact_details)
        binding.toolbarLayout.toolbar.title = getString(R.string.contact_info)
        setSupportActionBar(binding.toolbarLayout.toolbar)
        setUpBackButton()

        disposeLater(viewModel.output.onInfoLoaded.subscribeOnMainThread { response ->
            when (response.state) {
                State.SUCCESS -> response.data?.let {
                    binding.userInfo = it
                }
                State.LOADING -> print("LOADING")
                State.ERROR   -> print("ERROR")
            }

        })

        disposeLater(viewModel.output.onSaveInfo.subscribeOnMainThread { response ->
            when (response.state) {
                State.SUCCESS -> response.data?.let {
                    Toast.makeText(this, getString(R.string.info_saved), Toast.LENGTH_SHORT)
                        .show()
                }
                State.LOADING -> print("LOADING")

                State.ERROR   -> {
                    binding.error = response.data
                    Toast.makeText(this, response.error, Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })

        viewModel.input.loadInfo.onNext(true)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menu.clear()
        menuInflater.inflate(R.menu.text_button_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menuButton) {
            viewModel.input.saveInfo.onItemClick(true)
        }
        else {
            this.finish()
        }
        return super.onOptionsItemSelected(item)
    }
}