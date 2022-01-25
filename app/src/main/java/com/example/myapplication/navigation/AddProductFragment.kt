package com.example.myapplication.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myapplication.Product
import com.example.myapplication.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class AddProductFragment : Fragment() {

    private lateinit var etProductName: EditText
    private lateinit var etProductPrice: EditText
    private lateinit var etProductDescription: EditText
    private lateinit var etProductImage: EditText
    private lateinit var btnAddProduct: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_add_product, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        etProductName = view.findViewById(R.id.enter_product_name)
        etProductDescription = view.findViewById(R.id.enter_product_description)
        etProductPrice = view.findViewById(R.id.enter_product_price)
        etProductImage = view.findViewById(R.id.enter_product_image)
        btnAddProduct = view.findViewById(R.id.button_add)

        validate()

        etProductName.doAfterTextChanged { validate() }
        etProductDescription.doAfterTextChanged { validate() }
        etProductPrice.doAfterTextChanged { validate() }
        etProductImage.doAfterTextChanged { validate() }

        btnAddProduct.setOnClickListener { addProduct() }
    }

    private fun validate() {
        val name = etProductName.text.toString()
        val description = etProductDescription.text.toString()
        val price = etProductPrice.text.toString()
        val image = etProductImage.text.toString()

        btnAddProduct.isEnabled =
            name.isNotEmpty() && description.isNotEmpty() && price.isNotEmpty() && image.isNotEmpty()
    }

    private fun enableAll(enable: Boolean) {
        etProductName.isEnabled = enable
        etProductDescription.isEnabled = enable
        etProductImage.isEnabled = enable
        etProductPrice.isEnabled = enable
        btnAddProduct.isEnabled = enable
    }

    private fun addProduct() {
        enableAll(false)

        val name = etProductName.text.toString()
        val description = etProductDescription.text.toString()
        val price = etProductPrice.text.toString().toDouble()
        val image = etProductImage.text.toString()

        val reference = Firebase.database.getReference("products")

        val result = Product()
        result.id = reference.push().key.orEmpty()
        result.name = name
        result.description = description
        result.price = price
        result.image = image
        result.addedBy = Firebase.auth.currentUser?.email ?: ""

        reference.child(result.id).setValue(result)
            .addOnSuccessListener {
                enableAll(true)
                findNavController().navigate(R.id.navigation_home)
            }.addOnFailureListener {
                enableAll(true)
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
            }
    }
}