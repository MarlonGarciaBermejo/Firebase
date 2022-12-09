package com.firstapp.firebase

import com.google.firebase.firestore.DocumentId

class ShoppingItems(@DocumentId var documentId : String? = null,var name: String? = null, var done: Boolean = false, var category: String? = null ) {
}