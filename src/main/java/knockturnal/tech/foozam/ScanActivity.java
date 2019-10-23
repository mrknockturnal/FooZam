package knockturnal.tech.foozam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class ScanActivity extends AppCompatActivity {
    ImageView imageView;
    Button predict_Button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        // Code for Taking Picture of Fruit Button
        final Button btnCamera = findViewById(R.id.btnCamera);
        imageView = findViewById(R.id.imageView);

        //Code for predict Button
        predict_Button = findViewById(R.id.predict_button);
        predict_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ScanActivity.this, NutritionActivity.class);
                startActivity(intent);

            }
        });


        // Code For Bottom Navigation View
        BottomNavigationView bottomNavigationView =  findViewById(R.id.bottom_navigation);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.ic_home:
                        Intent intent1 = new Intent(ScanActivity.this, LandingActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.ic_scan:
                        break;
                    case R.id.ic_gallery:
                        Intent intent3 = new Intent(ScanActivity.this, GalleryActivity.class);
                        startActivity(intent3);
                        break;
                }
                return false;
            }
        });



        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,0);
                predict_Button.setVisibility(View.VISIBLE);
            }
        });
    }

    // This is where the photo is getting stored
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = (Bitmap)data.getExtras().get("data");
        imageView.setImageBitmap(bitmap);

    }
}
