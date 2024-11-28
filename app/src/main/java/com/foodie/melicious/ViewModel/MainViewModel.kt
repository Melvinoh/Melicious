package com.foodie.melicious.ViewModel

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.foodie.melicious.Helper.UserDb
import com.foodie.melicious.Model.CategoryModel
import com.foodie.melicious.Model.FoodsModel
import com.foodie.melicious.Model.SliderModel
import com.foodie.melicious.Model.UserModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application)
{
    private val firebaseDatabase = FirebaseDatabase.getInstance()
    private val userDB :UserDb = UserDb.getDatabase(application)

    private val _banner = MutableLiveData<MutableList<SliderModel>>()
    private val _category= MutableLiveData<MutableList<CategoryModel>>()
    private val _popular = MutableLiveData<MutableList<FoodsModel>>()
    private val _recommended = MutableLiveData<MutableList<FoodsModel>>()
    private val _categoryItems = MutableLiveData<MutableList<FoodsModel>>()
    private val _userProfile = MutableLiveData<MutableList<UserModel?>>()

    val category:LiveData<MutableList<CategoryModel>> = _category
    val banner : LiveData<MutableList<SliderModel>> = _banner
    val popular:LiveData<MutableList<FoodsModel>> = _popular
    val recommended:LiveData<MutableList<FoodsModel>> = _recommended
    val categoryItems : MutableLiveData<MutableList<FoodsModel>> = _categoryItems
    val userProfile: MutableLiveData<MutableList<UserModel?>> = _userProfile



    fun loadBanners(){
        val ref = firebaseDatabase.getReference("Banner")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    Log.d("FirebaseData", "Data retrieved successfully")
                    Log.d("FirebaseData", "Data: ${snapshot.value}")
                    val list = mutableListOf<SliderModel>()
                    for(childSnapshot in snapshot.children){
                        val data = childSnapshot.getValue(SliderModel::class.java)
                        if(data != null){
                            list.add(data)
                        }
                        _banner.value = list
                    }
                } else {
                    Log.d("FirebaseData", "No data found")
                }

            }
            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
    fun loadCategory(){
        val  ref = firebaseDatabase.getReference("Category")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    Log.d("FirebaseData", "Data retrieved successfully")
                    Log.d("FirebaseData", "Data: ${snapshot.value}")

                    val lists= mutableListOf<CategoryModel>()
                    for(childSnapshot in snapshot.children) {
                        val list = childSnapshot.getValue(CategoryModel::class.java)
                        if (list != null){
                            lists.add(list)
                        }
                    }
                    _category.value = lists
                }else {
                    Log.d("FirebaseData", "No data found")
                }

            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
    @SuppressLint("SuspiciousIndentation")
    fun loadPopular(){
        val ref = firebaseDatabase.getReference("Foods")
        val  query = ref.orderByChild("bestFood").equalTo("popular")
            query.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
               val lists = mutableListOf<FoodsModel>()
                if (snapshot.exists()) {
                    Log.d("FirebaseData", "Data retrieved successfully")
                    Log.d("FirebaseData", "Data: ${snapshot.value}")
                    for (childSnapshot in snapshot.children){
                        val list = childSnapshot.getValue(FoodsModel::class.java)
                        if(list != null){
                            lists.add(list)
                        }
                    }
                    _popular.value = lists
                }else{
                    Log.d("FirebaseData", "No data found")
                }

            }
            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
    fun loadRecommended(){
        val ref = firebaseDatabase.getReference("Foods")
        val  query = ref.orderByChild("bestFood").equalTo("recommended")
        query.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<FoodsModel>()
                if (snapshot.exists()) {
                    Log.d("FirebaseData", "Data retrieved successfully")
                    Log.d("FirebaseData", "Data: ${snapshot.value}")
                    for (childSnapshot in snapshot.children){
                        val list = childSnapshot.getValue(FoodsModel::class.java)
                        if(list != null){
                            lists.add(list)
                        }
                    }
                    _recommended.value = lists
                }else{
                    Log.d("FirebaseData", "No data found")
                }

            }
            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    fun loadCategoryItems(id :Int) {
        val ref = firebaseDatabase.getReference("Foods")
        val  query = ref.orderByChild("categoryId").equalTo(id.toDouble())
        query.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<FoodsModel>()
                if (snapshot.exists()) {
                    Log.d("FirebaseData", "Data retrieved successfully")
                    Log.d("FirebaseData", "Data: ${snapshot.value}")
                    for (childSnapshot in snapshot.children){
                        val list = childSnapshot.getValue(FoodsModel::class.java)
                        if(list != null){
                            lists.add(list)
                        }
                    }
                    _categoryItems.value =lists
                }else{
                    Log.d("FirebaseData", "No data found")
                }

            }
            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
    fun loadUserProfile(userId: Int) {
        viewModelScope.launch {
            val userList = userDB.UserDao().getUser(userId)
            _userProfile.postValue(userList.toMutableList())
        }
    }
    fun updateUser(user: UserModel) {
        viewModelScope.launch {
            userDB.UserDao().insertUser(user) // Call to DAO to insert user
            // Optionally, reload user profile after updating
            loadUserProfile(user.id)
        }
    }


}




