package com.example.practica05_vasquezzapatachristopher;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.practica05_vasquezzapatachristopher.databinding.ActivityDetallesActividadBinding;

public class DetallesActividad extends AppCompatActivity {

    public static final String BOOK_KEY = "book_key";
    public static final String BITMAP_KEY = "bitmap_key";
    private ActivityDetallesActividadBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetallesActividadBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Bundle extras = getIntent().getExtras();
        Book ratedBook = extras.getParcelable(BOOK_KEY);
        Bitmap bitmap = extras.getParcelable(BITMAP_KEY);
        if(bitmap!=null){
            binding.imageView.setImageBitmap(bitmap);
        }
        binding.setBook(ratedBook);
    }
}