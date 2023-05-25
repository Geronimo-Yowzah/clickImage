package com.example.testclickimage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.PhotoViewAttacher;

public class MainActivity extends AppCompatActivity implements OnClickableAreaClickedListener {

    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView image = findViewById(R.id.imageView);

        Glide.with(this).asBitmap().load(R.drawable.austria_map).into(new CustomTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                Canvas canvas = new Canvas(resource);
                Paint paint = new Paint();

                List<ClickableArea> clickableAreas = getClickableAreas();
                for (ClickableArea i : clickableAreas) {
                    State state = (State) i.getItem();
                    paint.setColor(state.getColor());
                    canvas.drawRect(i.getX(), i.getY(), i.getX() + i.getW(),i.getY() + i.getH(), paint);
                }

                image.setImageBitmap(resource);

                ClickableAreasImage clickableAreasImage = new ClickableAreasImage(new PhotoViewAttacher(image), MainActivity.this);
                clickableAreasImage.setClickableAreas(clickableAreas);
            }

            @Override
            public void onLoadCleared(@Nullable Drawable placeholder) {
            }
        });
    }

    private List<ClickableArea> getClickableAreas() {
        List<ClickableArea> clickableAreas = new ArrayList<>();

        clickableAreas.add(new ClickableArea(600, 100, 50, 50, new State("Lower Austria", Color.BLUE)));
        clickableAreas.add(new ClickableArea(440, 125, 50, 50, new State("Upper Austria", Color.BLUE)));
        clickableAreas.add(new ClickableArea(700, 126, 50, 50, new State("Vienna", Color.BLUE)));

        clickableAreas.add(new ClickableArea(685, 270, 50, 50, new State("Burgenland", Color.GREEN)));
        clickableAreas.add(new ClickableArea(420, 350, 50, 50, new State("Carinthia", Color.GREEN)));
        clickableAreas.add(new ClickableArea(370, 245, 50, 50, new State("Salzburg", Color.GREEN)));

        clickableAreas.add(new ClickableArea(170, 280, 50, 50, new State("Tyrol", Color.RED)));
        clickableAreas.add(new ClickableArea(30, 280, 50, 50, new State("Vorarlberg", Color.RED)));
        clickableAreas.add(new ClickableArea(570, 250, 50, 50, new State("Styria", Color.RED)));

        return clickableAreas;
    }

    @Override
    public void onClickableAreaTouched(Object item, int x, int y) {
        if (item instanceof State) {
            String text = ((State) item).getName();
            dialog = new Dialog(MainActivity.this);
            dialog.setContentView(R.layout.dialog);
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.setCancelable(false);
            dialog.getWindow().getAttributes().windowAnimations = R.style.animation;

            TextView setCountry = dialog.findViewById(R.id.setCountry);
            TextView setX = dialog.findViewById(R.id.setX);
            TextView setY = dialog.findViewById(R.id.setY);

            setCountry.setText("Country: " + text);
            setX.setText("X: " + x);
            setY.setText("Y: " + y);

            TextView okayText = dialog.findViewById(R.id.okay_text);
            okayText.setOnClickListener(v -> {
                dialog.dismiss();
                Toast.makeText(MainActivity.this, "Okay clicked", Toast.LENGTH_SHORT).show();
            });

            dialog.show();
        }
    }
}


//public class MainActivity extends AppCompatActivity implements OnClickableAreaClickedListener{
//    TextView okay_text;
//    Dialog dialog;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        // Add image
//        ImageView image = (ImageView) findViewById(R.id.imageView);
////        image.setImageResource(R.drawable.austria_map);
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.austria_map);
////        image.setImageBitmap(bitmap);
//
//        Bitmap mutableBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
//
////        Canvas canvas = new Canvas(mutableBitmap);
//
//        Paint paint = new Paint();
//
////        image.setImageBitmap(mutableBitmap);
//
//        Glide .with(this).asBitmap().load(R.drawable.austria_map).into(new CustomTarget<Bitmap>() {
//            @Override
//            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
//
//                Canvas canvas = new Canvas(resource);
//
//                List<ClickableArea> clickableAreas = getClickableAreas();
//
//
//                for(ClickableArea i : clickableAreas ){
//                    State state = (State) i.getItem();
//                    paint.setColor(state.getColor());
//                    canvas.drawRect(i.getX(),i.getY(),i.getX()+i.getW(),i.getY()+i.getH(),paint);
//                }
//
//                image.setImageBitmap(resource);
//
//
//                // Create your image
//                ClickableAreasImage clickableAreasImage = new ClickableAreasImage(new PhotoViewAttacher(image), new OnClickableAreaClickedListener() {
//                    @Override
//                    public void onClickableAreaTouched(Object item,int x, int y) {
//                        dialog = new Dialog(MainActivity.this);
//                        if(item instanceof State){
//                            String text = ((State) item).getName();
//                            dialog.setContentView(R.layout.dialog);
//                            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                            dialog.setCancelable(false);
//                            dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
//
//                            TextView setCountry = dialog.findViewById(R.id.setCountry);
//                            TextView setx = dialog.findViewById(R.id.setX);
//                            TextView sety = dialog.findViewById(R.id.setY);
//
//                            setCountry.setText("Contry : "+text);
//                            setx.setText("X : "+x);
//                            sety.setText("Y : "+y);
//                            okay_text = dialog.findViewById(R.id.okay_text);
//
//                            okay_text.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    dialog.dismiss();
//                                    Toast.makeText(MainActivity.this, "Okay clicked", Toast.LENGTH_SHORT).show();
//                                }
//                            });
//                            dialog.show();
//                        }
//                    }
//                });
//
//                // Set your clickable areas to the image
//                clickableAreasImage.setClickableAreas(clickableAreas);
//            }
//
//            @Override
//            public void onLoadCleared(@Nullable Drawable placeholder) {
//
//            }
//        });
//
//    }
//
//    private List<ClickableArea> getClickableAreas() {
//
//        List<ClickableArea> clickableAreas = new ArrayList<>();
//
//        clickableAreas.add(new ClickableArea(600, 100, 50, 50, new State("Lower Austria",Color.BLUE)));
//        clickableAreas.add(new ClickableArea(440, 125, 50, 50, new State("Upper Austria",Color.BLUE)));
//        clickableAreas.add(new ClickableArea(700, 126, 50, 50, new State("Vienna",Color.BLUE)));
//
//        clickableAreas.add(new ClickableArea(685, 270, 50, 50, new State("Burgenland",Color.GREEN)));
//        clickableAreas.add(new ClickableArea(420, 350, 50, 50, new State("Carinthia",Color.GREEN)));
//        clickableAreas.add(new ClickableArea(370, 245, 50, 50, new State("Salzburg",Color.GREEN)));
//
//        clickableAreas.add(new ClickableArea(170, 280, 50, 50, new State("Tyrol",Color.RED)));
//        clickableAreas.add(new ClickableArea(30, 280, 50, 50, new State("Vorarlberg",Color.RED)));
//        clickableAreas.add(new ClickableArea(570, 250, 50, 50, new State("Styria",Color.RED)));
//
//        return clickableAreas;
//    }
//
//
//    @Override
//    public void onClickableAreaTouched(Object item, int x, int y) {
//
//    }
//}

