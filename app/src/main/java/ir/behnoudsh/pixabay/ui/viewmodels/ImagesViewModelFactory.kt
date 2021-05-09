package ir.behnoudsh.pixabay.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ir.behnoudsh.pixabay.ui.adapters.ImagesAdapter

class ImagesViewModelFactory(): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ImagesViewModel::class.java)){
            return ImagesViewModel() as T
        }
        throw IllegalArgumentException ("UnknownViewModel")
    }
}