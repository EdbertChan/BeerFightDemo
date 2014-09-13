package swipe.android.beerFightDemo.model;



import java.util.List;
import java.util.Random;

import org.askerov.dynamicgrid.BaseDynamicGridAdapter;

import swipe.android.beerFightDemo.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class WordDynamicAdapter extends BaseDynamicGridAdapter {
    public WordDynamicAdapter(Context context, List<?> items, int columnCount) {
        super(context, items, columnCount);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CheeseViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_grid, null);
            holder = new CheeseViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (CheeseViewHolder) convertView.getTag();
        }
        holder.build(getItem(position).toString());
        
        return convertView;
    }
    public String currentString(){
    	List<String> list = (List<String>) (List<?>)this.getItems();
    	String currentString = TextUtils.join("", list);
    
    	return currentString;
    }

    private class CheeseViewHolder {
      
        private ImageView image;
private int color;
        private CheeseViewHolder(View view) {
          color = newColor();
            image = (ImageView) view.findViewById(R.id.item_img);
            
        }

        void build(String title) {
          //  titleText.setText(title);
        	Bitmap b = getThumb(title, color);
        	image.setImageBitmap(b);
        }
    }
    private int newColor(){
    	
    	return Color.rgb(55, 66,
				77);
    }
	static Random random = new Random();
	private Bitmap getThumb(String s, int color) {
		Bitmap bmp = Bitmap.createBitmap(150, 150, Bitmap.Config.RGB_565);
		Canvas canvas = new Canvas(bmp);
		Paint paint = new Paint();

		paint.setColor(color);
		paint.setTextSize(24);
		paint.setFlags(Paint.ANTI_ALIAS_FLAG);
		canvas.drawRect(new Rect(0, 0, 150, 150), paint);
		paint.setColor(Color.WHITE);
		paint.setTextAlign(Paint.Align.CENTER);
		canvas.drawText(s, 75, 75, paint);

		return bmp;
	}
}