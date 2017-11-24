package com.rivero.daniel.cabifyestimate.presentation.common.custom;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.rivero.daniel.cabifyestimate.R;
import com.rivero.daniel.cabifyestimate.domain.Placement;

public class PlaceSelector extends CardView {

    private TextView street;
    private TextView city;

    private Placement placement;

    public PlaceSelector(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public PlaceSelector(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.view_place_selector, this);

        street = findViewById(R.id.text_street);
        city = findViewById(R.id.text_city);
        TextView hint = findViewById(R.id.text_hint);
        ImageView image = findViewById(R.id.image_description);


        final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PlaceSelector);
        String hintDefined = typedArray.getString(R.styleable.PlaceSelector_hint);
        Drawable drawable = typedArray.getDrawable(R.styleable.PlaceSelector_drawableToEnd);
        typedArray.recycle();

        hint.setText(hintDefined);
        image.setBackground(drawable);
        setCardBackgroundColor(getResources().getColor(R.color.cardview_light_background));
        setRadius(convertDpToPx(context, 6));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setElevation(convertDpToPx(context, 3));
        }
    }

    public float convertDpToPx(Context context, float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }

    public void updatePlacement(Placement placement) {
        this.placement = placement;
        updateLocationData(placement);
    }

    private void updateLocationData(Placement placement) {
        if (placement != null) {
            street.setText(placement.getStreet());
            city.setText(placement.getCity());
        }
    }

    public boolean isPlaceFilled() {
        return placement != null;
    }

    public Placement getPlacement() {
        return placement;
    }

}
