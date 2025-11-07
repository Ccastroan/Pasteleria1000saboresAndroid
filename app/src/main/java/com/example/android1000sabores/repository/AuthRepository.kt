package com.example.android1000sabores.repository

import com.example.android1000sabores.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class AuthRepository {
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    suspend fun login(correo: String, clave: String): User? {
        return try {
            //Intentar autenticar
            val resultado = auth.signInWithEmailAndPassword(correo, clave).await()
            val user = resultado.user
            if (user != null) {
                //Si el usuario no es nulo, obtener los datos adicionales de Firestore
                getUserFromFirestore(user.uid, correo) ?: User(
                    correo = correo,
                    nombre = if (correo == "admin@pasteleria1000sabores.cl") "Administrador" else "Usuario",
                    rol = if (correo == "admin@pasteleria1000sabores") "admin" else "cliente"
                )
            } else null

        } catch (e: Exception) {
            loginWithFirestore(correo, clave)
            null
        }
    }

    private suspend fun getUserFromFirestore(uid: String, correo: String): User? {
        return try {
            val doc = db.collection("usuarios").document(uid).get().await()
            if (doc.exists()) {
                User(
                    correo = doc.getString("correo") ?: correo,
                    nombre = doc.getString("nombre") ?: "Usuario",
                    rol = doc.getString("rol") ?: "cliente"

                )

            } else null
        } catch (e: Exception) {
            null
        }
    }

    private suspend fun loginWithFirestore(correo: String, clave: String): User?
    {
        return try {
            val query = db.collection("usuarios")
                .whereEqualTo("correo", correo)
                .whereEqualTo("clave", clave)
                .get()
                .await()
            if (!query.isEmpty) {
                val doc = query.documents[0]
                User(
                    correo = doc.getString("correo") ?: "",
                    nombre = doc.getString("nombre") ?: "Cliente",
                    rol = doc.getString("rol") ?: "cliente"
                )
            } else null
        }catch(e: Exception) {
                null
            }
        }
}

