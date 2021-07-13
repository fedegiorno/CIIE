package com.fedegiorno.ciie_region_6.activities

import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.fedegiorno.ciie_region_6.R
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import com.itextpdf.text.Document
import com.itextpdf.text.Paragraph
import com.itextpdf.text.pdf.PdfWriter
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

class InscripcionActivity : AppCompatActivity() {

    private val STORAGE_CODE: Int = 1001

    private lateinit var emailprueba: String

    private lateinit var etxDatosInscripcion: EditText
    private lateinit var btnCancelar: Button
    private lateinit var btnInscripcion: Button

    private lateinit var mFileName: String
    private lateinit var mFilePath: String

    // [START storage_field_declaration]
    lateinit var storage: FirebaseStorage
    // [END storage_field_declaration]

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inscripcion)

        etxDatosInscripcion = findViewById(R.id.etxDatosInscripcion)
        btnCancelar = findViewById(R.id.btnCancelar)
        btnInscripcion = findViewById(R.id.btnInscripcion)

        // [START storage_field_initialization]
        storage = Firebase.storage
        // [END storage_field_initialization]


    }

    override fun onStart() {
        super.onStart()

        emailprueba = "fedegiorno@gmail.com"

        var textoInscripcionPDF: String = emailprueba

        etxDatosInscripcion.setText(textoInscripcionPDF)

        btnInscripcion.setOnClickListener(){
            crearFormPDF()
            guardarFormPDF()
        }
        btnCancelar.setOnClickListener(){

        }
    }

    private fun guardarFormPDF() {

        var fileUri:Uri = Uri.parse(mFilePath)
        // Create a storage reference from our app
        val storageRef = storage.reference

        var file = Uri.fromFile(File(mFilePath))
        // Create a reference to inscripciones file
        val inscripcionRef = storageRef.child("Inscripciones/${file.lastPathSegment}")

        var uploadTask = inscripcionRef.putFile(file)

// Register observers to listen for when the download is done or if it fails
        uploadTask.addOnFailureListener {
            Toast.makeText(this, "Se produjo un fallo al subir el formulario", Toast.LENGTH_LONG).show()
        }.addOnSuccessListener { taskSnapshot ->
            Toast.makeText(this, "El formulario se subio exitosamente", Toast.LENGTH_LONG).show()
        }
    }

    private fun crearFormPDF() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_DENIED
            ) {
                val permission = arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                requestPermissions(permission, STORAGE_CODE)
            } else {
                savePDF()
            }
        } else {
            savePDF()
        }
        //Toast.makeText(this, "Formulario enviado", Toast.LENGTH_SHORT).show()
    }

    private fun savePDF() {
        val mDoc = Document()
        mFileName = "InscripcionCIIE-6-" + SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault())
            .format(System.currentTimeMillis())
        mFilePath = Environment.getExternalStorageDirectory().toString() + "/" + mFileName + ".pdf"

        try {
            PdfWriter.getInstance(mDoc, FileOutputStream(mFilePath))
            mDoc.open()

            val data = etxDatosInscripcion.text.toString().trim()
            mDoc.addAuthor("CIIE-6")
            //mDoc.addHeader("HEADER","content")
            mDoc.addSubject("Formulario de inscripción a los cursos de los CIIEs de la Región 6 ")
            mDoc.addTitle("Inscripción a Cursos")
            mDoc.right(2.0f)
            mDoc.left(4.0f)
            mDoc.top(5.0f)
            mDoc.add(Paragraph(data))
            mDoc.close()
            Toast.makeText(this, "$mFileName.pdf\n is create to \n$mFilePath", Toast.LENGTH_LONG).show()
        } catch (e: Exception){
            Toast.makeText(this, "" + e.toString(), Toast.LENGTH_SHORT).show()
        }
    }
}