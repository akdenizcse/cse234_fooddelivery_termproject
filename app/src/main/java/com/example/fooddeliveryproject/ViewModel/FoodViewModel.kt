package com.example.fooddeliveryproject.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddeliveryproject.Models.Food
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class FoodViewModel():ViewModel() {
    private val db= Firebase.firestore

    private val _foodList=MutableLiveData<ArrayList<Food>>()
     val foodList : LiveData<ArrayList<Food>> get() = _foodList
    fun getFoodList(){
        viewModelScope.launch {
            db.collection("Food").get().addOnSuccessListener {
                _foodList.value=it.toObjects(Food::class.java) as ArrayList<Food>
            }.addOnSuccessListener {

            }.addOnFailureListener{

            }
        }
    }


    fun searchFood(query: String) {
        val foodList = mutableListOf<Food>()
        // Search in name
        val nameQuery = db.collection("Food")
            .whereGreaterThanOrEqualTo("name", query)
            .whereLessThanOrEqualTo("name", query + "\uf8ff")

        // Search in description
        val descriptionQuery = db.collection("Food")
            .whereGreaterThanOrEqualTo("description", query)
            .whereLessThanOrEqualTo("description", query + "\uf8ff")

        // Search in category
        val categoryQuery = db.collection("Food")
            .whereGreaterThanOrEqualTo("category", query)
            .whereLessThanOrEqualTo("category", query + "\uf8ff")

        // Execute all queries
        nameQuery.get().addOnSuccessListener { nameDocuments ->
            foodList.addAll(nameDocuments.toObjects(Food::class.java))
            descriptionQuery.get().addOnSuccessListener { descriptionDocuments ->
                foodList.addAll(descriptionDocuments.toObjects(Food::class.java))
                categoryQuery.get().addOnSuccessListener { categoryDocuments ->
                    foodList.addAll(categoryDocuments.toObjects(Food::class.java))

                    // Remove duplicates (if any)
                    val uniqueFoodList = foodList.distinctBy { it.id }
                    _foodList.value = ArrayList(uniqueFoodList)
                }.addOnFailureListener { exception ->
                    Log.w("Firestore", "Error getting category documents: ", exception)
                }
            }.addOnFailureListener { exception ->
                Log.w("Firestore", "Error getting description documents: ", exception)
            }
        }.addOnFailureListener { exception ->
            Log.w("Firestore", "Error getting name documents: ", exception)
        }
    }

    fun getRandomFoodItems(count: Int) {
        val foodCollection = db.collection("Food")
        var isShuffled = false

        foodCollection.get().addOnSuccessListener { result ->
            val foodItems = result.documents.mapNotNull { it.toObject(Food::class.java) }
            if (_foodList!=null){
                if (!isShuffled) {
                    val randomItems = ArrayList(foodItems.take(count))
                    isShuffled = true
                    _foodList.value = randomItems
                }
            }

        }.addOnFailureListener { exception ->
        }
    }




}