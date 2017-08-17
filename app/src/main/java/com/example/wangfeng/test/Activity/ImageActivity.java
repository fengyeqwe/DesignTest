package com.example.wangfeng.test.Activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.wangfeng.test.R;

import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageActivity extends AppCompatActivity {

    @BindView(R.id.img)
    ImageView mImg;
    @BindView(R.id.activity_image)
    RelativeLayout mActivityImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        ButterKnife.bind(this);
        mActivityImage.setBackground(getImage(this,R.drawable.pic1));
    }

    /**
     *  原来当使用像 imageView.setBackgroundResource，imageView.setImageResource, 或者 BitmapFactory.decodeResource
     *  这样的方法来设置一张大图片的时候，这些函数在完成decode后，最终都是通过java层的createBitmap来完成的，需要消耗更多内存。
     *  因此，改用先通过BitmapFactory.decodeStream方法，创建出一个bitmap，再将其设为ImageView的 source，
     *  decodeStream最大的秘密在于其直接调用JNI>>nativeDecodeAsset()来完成decode，无需再使用java层的createBitmap，从而节省了java层的空间。
     *  如果在读取时加上图片的Config参数，可以跟有效减少加载的内存，从而跟有效阻止抛out of Memory异常。
     *  另外，需要特别注意：decodeStream是直接读取图片资料的字节码了， 不会根据机器的各种分辨率来自动适应，
     *  使用了decodeStream之后，需要在hdpi和mdpi，ldpi中配置相应的图片资源，
     *  否则在不同分辨率机器上都是同样大小（像素点数量），显示出来的大小就不对了。
     * @param context 上下文
     * @param id      图片id
     * @return bitmapDrawable 图片
     */
    public BitmapDrawable getImage(Context context, Integer id) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        InputStream is = context.getResources().openRawResource(id);
        Bitmap bm = BitmapFactory.decodeStream(is, null, opt);
        BitmapDrawable bd = new BitmapDrawable(context.getResources(), bm);
        return bd;
    }
}
