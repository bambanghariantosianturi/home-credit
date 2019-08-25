package com.androidexercise.admin.homecredittest.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.androidexercise.admin.homecredittest.data.ProductDataSource
import com.androidexercise.admin.homecredittest.data.ProductRepository
import com.androidexercise.admin.homecredittest.model.Product

class HomeVM : ViewModel() {

    private val repository = ProductRepository()
    private var list: MutableLiveData<List<Product>>? = null
    private var status = MutableLiveData<Boolean?>()

    private fun setItem() {
        repository.getListProduct(object : ProductDataSource.GetProductCallback {
            override fun onProductLoaded(product: List<Product>?) {
                list?.postValue(product)
                status.value = true
            }

            override fun onDataNotAvailable(errorMessage: String) {
                status.value = false
            }
        })
    }

    fun getItems(): LiveData<List<Product>>? {
        if (list == null) {
            list = MutableLiveData()
            setItem()
        }
        return list
    }
}