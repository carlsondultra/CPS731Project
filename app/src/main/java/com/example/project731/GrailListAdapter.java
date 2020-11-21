package com.example.project731;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.util.List;

public class GrailListAdapter extends ArrayAdapter<Grail> {

    private Context mContext;
    private int mResource;

    public GrailListAdapter(Context context, int resource, List<Grail> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }
    private static class ViewHolder{
        TextView name;

        ImageView shoePic;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        setUpImageLoader();

        String name = getItem(position).getRealName();
        String shoePic = getItem(position).getShoePicDrawable();

        ViewHolder holder;

        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
            holder = new GrailListAdapter.ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.realNameMatch);
            holder.shoePic = (ImageView) convertView.findViewById(R.id.shoeMatchImage);
        }
        else{
            holder = (GrailListAdapter.ViewHolder) convertView.getTag();
        }

        ImageLoader imageLoader = ImageLoader.getInstance();
        int defaultImage = mContext.getResources().getIdentifier("@drawable/banned", null, mContext.getPackageName());

        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true)
                .cacheOnDisc(true).resetViewBeforeLoading(true)
                .showImageForEmptyUri(defaultImage)
                .showImageOnFail(defaultImage)
                .showImageOnLoading(defaultImage).build();

        holder.name.setText(name);
        imageLoader.displayImage(shoePic,holder.shoePic,options);

/*
        if(convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item,parent, false);

        TextView name = (TextView) convertView.findViewById(R.id.realNameMatch);
        ImageView image = (ImageView) convertView.findViewById(R.id.shoeMatchImage);

        name.setText(grail.getRealName());
        image.setImageDrawable(Drawable.createFromPath(grail.getShoePicDrawable()));

*/
        return convertView;
    }
    private void setUpImageLoader(){

        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheOnDisc(true).cacheInMemory(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .displayer(new FadeInBitmapDisplayer(300)).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                mContext)
                .defaultDisplayImageOptions(defaultOptions)
                .memoryCache(new WeakMemoryCache())
                .discCacheSize(100 * 1024 * 1024).build();

        ImageLoader.getInstance().init(config);

    }
}
