package knockturnal.tech.foozam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class RecipesActivity extends AppCompatActivity {

    // Back button declaration
    Button go_back_button;

    // ImageViews declaration
    ImageView recipe_one_imageView;
    ImageView recipe_two_imageView;
    ImageView recipe_three_imageView;
    ImageView recipe_four_imageView;
    ImageView recipe_five_imageView;
    ImageView recipe_six_imageView;

    //TextViews declaration
    TextView recipe_one_textView;
    TextView recipe_two_textView;
    TextView recipe_three_textView;
    TextView recipe_four_textView;
    TextView recipe_five_textView;
    TextView recipe_six_textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        // Code to go back to nutritional activity
        go_back_button = findViewById(R.id.back_button);
        go_back_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecipesActivity.this, NutritionActivity.class);
                startActivity(intent);
            }
        });
    }
}
