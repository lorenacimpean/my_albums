package com.example.myalbums.ui.contact_info

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import com.example.myalbums.R
import com.example.myalbums.databinding.ActivityContactDetailsBinding
import com.example.myalbums.di.DisposableActivity
import com.example.myalbums.utils.State
import com.example.myalbums.utils.subscribeOnMainThread
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContactDetailsActivity : DisposableActivity() {

    private lateinit var binding : ActivityContactDetailsBinding
    private val viewModel : ContactDetailsViewModel by viewModel<ContactDetailsViewModel>()
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_contact_details)
        binding.toolbarLayout.toolbar.title = getString(R.string.contact_info)
        binding.listener = viewModel.input.clickLocation
        setSupportActionBar(binding.toolbarLayout.toolbar)
        setUpBackButton()

        disposeLater(viewModel.output.onInfoLoaded.subscribeOnMainThread { response ->
            when (response.state) {
                State.SUCCESS -> response.data?.let {
                    binding.userInfo = it
                }
                State.LOADING -> print("LOADING")
                State.ERROR -> print("ERROR")
            }
        })

        disposeLater(viewModel.output.onSaveInfo.subscribeOnMainThread { response ->
            when (response.state) {
                State.SUCCESS -> response.data?.let {
                    makeText(this, getString(R.string.info_saved), Toast.LENGTH_SHORT)
                            .show()
                }
                State.LOADING -> print("LOADING")
                State.ERROR -> {
                    binding.error = response.data
                    makeText(this, response.error, Toast.LENGTH_SHORT)
                            .show()
                }
            }
        })
        disposeLater(viewModel.output.onLocationClick.subscribeOnMainThread { permissionError ->
            permissionError.missingPermissions?.let {
                makeText(this, permissionError.message, Toast.LENGTH_SHORT)
                        .show()
                ActivityCompat.requestPermissions(this, it, REQUEST_LOCATION)
            } ?: run {
                viewModel.input.requestLocation.onNext(true)
            }
        })

        viewModel.input.loadInfo.onNext(true)
    }

    override fun onCreateOptionsMenu(menu : Menu) : Boolean {
        menu.clear()
        menuInflater.inflate(R.menu.text_button_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item : MenuItem) : Boolean {
        if (item.itemId == R.id.menuButton) {
            viewModel.input.saveInfo.onItemClick(true)
        } else {
            this.finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onRequestPermissionsResult(requestCode : Int, permissions : Array<String>, grantResults : IntArray) {
        if (requestCode == REQUEST_LOCATION) {
            if (grantResults.size == 2 && grantResults.all {
                    it == PackageManager.PERMISSION_GRANTED
                }) {
                Log.d(TAG, getString(R.string.permission_granted))
                viewModel.input.clickLocation.onItemClick(true)
            } else {
                Log.e(TAG, getString(R.string.permission_denied))
            }
        }
    }

    companion object {

        private const val TAG = "ContactDetailsActivity"
        private const val REQUEST_LOCATION = 1
    }
}