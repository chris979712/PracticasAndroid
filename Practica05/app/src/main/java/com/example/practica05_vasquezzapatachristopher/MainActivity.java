package com.example.practica05_vasquezzapatachristopher;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import com.example.practica05_vasquezzapatachristopher.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private Bitmap takenPhotoBitmap;
    private ActivityResultLauncher<Intent> cameraLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null) {
                        takenPhotoBitmap = (Bitmap) data.getExtras().get("data");
                        binding.bookImage.setImageBitmap(takenPhotoBitmap);
                    } else {
                        Toast.makeText(this, "Error taking photo", Toast.LENGTH_SHORT).show();
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.saveButton.setOnClickListener(v->{
            openDetailActivity(
                    binding.bookTitle.getText().toString(),
                    binding.bookAuthors.getText().toString(),
                    binding.bookComment.getText().toString(),
                    binding.ratingBook.getRating()
            );
        });
        binding.bookImage.setOnClickListener(v->{
            openCamera();
        });
    }
    private void openCamera(){
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraLauncher.launch(cameraIntent);
    }
    private void openDetailActivity(String title, String author, String comment, float ratting){
        Book book = new Book(title, author, comment, ratting);
        Intent intent = new Intent(this, DetallesActividad.class);
        intent.putExtra(DetallesActividad.BOOK_KEY, book);
        intent.putExtra(DetallesActividad.BITMAP_KEY, takenPhotoBitmap);
        startActivity(intent);
    }
}