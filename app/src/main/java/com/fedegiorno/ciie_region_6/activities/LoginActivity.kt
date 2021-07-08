package com.fedegiorno.ciie_region_6.activities

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import com.facebook.*
import com.fedegiorno.ciie_region_6.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.firebase.auth.FacebookAuthProvider


class LoginActivity : AppCompatActivity() {

    private val GOOGLE_SIGN_IN = 100

    private val callbackManager = CallbackManager.Factory.create()

    private lateinit var btnRegistrar: Button
    private lateinit var btnIngresar: Button
    private lateinit var btnGoogle: Button
    private lateinit var btnFacebook: Button
    private lateinit var etxEmail: EditText
    private lateinit var etxClave: EditText
    private lateinit var llyAutenticacion: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnRegistrar = findViewById(R.id.btnRegistrar)
        btnIngresar = findViewById(R.id.btnIngresar)
        btnGoogle = findViewById(R.id.btnGoogle)
        btnFacebook = findViewById(R.id.btnFacebook)
        etxEmail = findViewById(R.id.etxEmail)
        etxClave = findViewById(R.id.etxClave)
        llyAutenticacion = findViewById(R.id.llyAutenticacion)
        //Setup
        setup()
        session()

    }

    override fun onStart() {
        super.onStart()

        llyAutenticacion.visibility = View.VISIBLE
    }

    private fun session() {

        val prefs: SharedPreferences = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)
        val email: String? = prefs.getString("email", null)
        val provider: String? = prefs.getString("provider", null)

        if (email != null && provider != null) {
            llyAutenticacion.visibility = View.INVISIBLE
            showLista(email, ProviderType.valueOf(provider))
        }
    }

    private fun setup() {
        title = "Autenticacion"

        btnRegistrar.setOnClickListener { //boton que permite el registro con email y contraseña
            if (etxEmail.text.isNotEmpty() && etxClave.text.isNotEmpty()) {
                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(
                        etxEmail.text.toString(),
                        etxClave.text.toString()
                    ).addOnCompleteListener {
                        if (it.isSuccessful) {
                            showLista(it.result?.user?.email ?: "", ProviderType.BASIC)
                        } else {
                            showAlert()
                        }
                    }
            }
        }

        btnIngresar.setOnClickListener {//boton que permite ingresar con email y contraseña previamente registrados
            if (etxEmail.text.isNotEmpty() && etxClave.text.isNotEmpty()) {
                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(
                        etxEmail.text.toString(),
                        etxClave.text.toString()
                    ).addOnCompleteListener {
                        if (it.isSuccessful) {
                            showLista(it.result?.user?.email ?: "", ProviderType.BASIC)
                        } else {
                            showAlert()
                        }
                    }
            }
        }

        btnGoogle.setOnClickListener() {
            //Configuracion
            val googleConf = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

            val googleClient: GoogleSignInClient = GoogleSignIn.getClient(this, googleConf)
            googleClient.signOut()

            startActivityForResult(googleClient.signInIntent, GOOGLE_SIGN_IN)
        }

        btnFacebook.setOnClickListener() {

            LoginManager.getInstance().logInWithReadPermissions(this, listOf("email"))

            LoginManager.getInstance().registerCallback(callbackManager,
                object : FacebookCallback<LoginResult> {

                    override fun onSuccess(result: LoginResult?) {

                        result?.let{
                            val token: AccessToken = it.accessToken
                            val credential: AuthCredential = FacebookAuthProvider.getCredential(token.token)

                            FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener {

                                if(it.isSuccessful) {
                                    showLista(it.result?.user?.email ?: "", ProviderType.FACEBOOK)
                                }else{
                                    showAlert()
                                }
                            }
                        }
                    }

                    override fun onCancel() {

                    }

                    override fun onError(error: FacebookException?) {
                        showAlert()
                    }
                })
        }
    }

    private fun showAlert() {//En caso de producirse un error de autenticacion

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al docente")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showLista(email: String, provider: ProviderType) { //En caso de autenticación favorable vamos a MainActivity
        val ListIntent: Intent = Intent(this, MainActivity::class.java).apply {
            putExtra("email", email)
            putExtra("provider", provider.name)
        }
        startActivity(ListIntent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        callbackManager.onActivityResult(requestCode, resultCode,data)

        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GOOGLE_SIGN_IN) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)

            try {
                val account: GoogleSignInAccount? = task.getResult(ApiException::class.java)

                if (account != null) {
                    val credential: AuthCredential = GoogleAuthProvider.getCredential(account.idToken, null)

                    FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener {

                        if(it.isSuccessful) {
                            showLista(account.email ?: "", ProviderType.GOOGLE)
                        }else{
                            showAlert()
                        }
                    }
                }
            } catch (e: ApiException){
                showAlert()
            }
        }
    }
}