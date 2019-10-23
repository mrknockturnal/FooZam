package knockturnal.tech.foozam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class GalleryActivity extends AppCompatActivity {

    ImageView myImageView;
    Button galleryButton;

    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        //Code for the image viewer and button
        myImageView = findViewById(R.id.selectedImage);

        galleryButton = findViewById(R.id.btnChoosePicture);

        // Code for the button
        galleryButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_DENIED){
                        // Permission not granted, request it
                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        // show popup for runtime permission
                        requestPermissions(permissions, PERMISSION_CODE);
                    }
                    else{
                        //permission already granted
                        pickImageFromGallery();
                    }
                }
                else{
                    // system OS is less than current lowest Android Version
                    pickImageFromGallery();
                }

                pickImageFromGallery();
            }
        });

        // Bottom Navigation view code

        BottomNavigationView bottomNavigationView =  findViewById(R.id.bottom_navigation);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.ic_home:
                        Intent intent1 = new Intent(GalleryActivity.this, LandingActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.ic_scan:
                        Intent intent2 = new Intent(GalleryActivity.this, ScanActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.ic_gallery:
                        break;
                }
                return true;
            }
        });
    }

    // Functions for picking image from gallery
    private void pickImageFromGallery(){
        //Intent to pick Photo
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);
    }

    // Condition specific functions

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            if (PERMISSION_CODE == requestCode){
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    //permission was granted
                    pickImageFromGallery();
                }
                else{
                    //permission was denied
                    Toast.makeText(this, "Permission denied....!", Toast.LENGTH_SHORT).show();
                }
            }
        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            //set the image on the imageView

            // This is where the image is stored
            Bitmap bitmap = (Bitmap)data.getExtras().get("data");
            myImageView.setImageBitmap(bitmap);
        }
    }


}

