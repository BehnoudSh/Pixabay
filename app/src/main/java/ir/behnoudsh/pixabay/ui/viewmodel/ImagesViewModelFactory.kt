package ir.behnoudsh.pixabay.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ImagesViewModelFactory(): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ImagesViewModel::class.java)){
            return ImagesViewModel() as T
        }
        throw IllegalArgumentException ("UnknownViewModel")
    }
}