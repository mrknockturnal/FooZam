package knockturnal.tech.foozam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NutritionActivity extends AppCompatActivity {

    ImageView fruitView;
    TextView nutritional_info_View;
    Button try_another_Button;
    Button get_recipes_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition);

        // Code for fruit image and nutritional info
        fruitView = findViewById(R.id.result_image);
        nutritional_info_View = findViewById(R.id.nutritional_info);

        // Code for Recipes button and explore button
        get_recipes_button = findViewById(R.id.explore_button);
        try_another_Button = findViewById(R.id.return_to_scan_button);

        // Code for Recipes Button
        get_recipes_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NutritionActivity.this, RecipesActivity.class);
                startActivity(intent);
            }
        });

        // Code for Try Another Button
        try_another_Button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NutritionActivity.this, ScanActivity.class);
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
                        Intent intent1 = new Intent(NutritionActivity.this, LandingActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.ic_scan:
                        Intent intent2 = new Intent(NutritionActivity.this, ScanActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.ic_gallery:
                        Intent intent3 = new Intent(NutritionActivity.this, GalleryActivity.class);
                        startActivity(intent3);
                        break;
                }
                return false;
            }
        });


    }
}
