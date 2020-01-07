package com.zj.wz.wbyx.baseandroid.view;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.baseandroid.utils.PLog;
/**
 * FileName: TagTextView
 * Author: 曹伟
 * Date: 2019/12/18 23:49
 * Description: 起始位置添加tag的TextView
 */
public class TagTextView extends AppCompatTextView {
    private String TAG = getClass().getSimpleName() ;
    private Context mContext ;
    private StringBuffer content_buffer;
    private TextView tv_tag;
    public TagTextView(Context context) {
        super(context);
        mContext = context ;
    }

    public TagTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context ;
    }

    public TagTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context ;
    }

    /**
     *
     * @param content
     * @param tags 方便后续多标签时候添加
     */
    public void setContentAndTag(String content, String... tags) {
        content_buffer = new StringBuffer();
        for (String item : tags) {//将每个tag的内容添加到content后边，之后将用drawable替代这些tag所占的位置
            content_buffer.append(item);
        }
        content_buffer.append(content);
        SpannableString spannableString = new SpannableString(content_buffer);
        for (int i = 0; i < tags.length; i++) {
            String item = tags[i];
            View view = LayoutInflater.from(mContext).inflate(R.layout.layout_tag_text, null);//R.layout.tag是每个标签的布局
            tv_tag = view.findViewById(R.id.tv_tag);
            tv_tag.setText(item);
            Bitmap bitmap = convertViewToBitmap(view);
            Drawable d = new BitmapDrawable(bitmap);
            d.setBounds(0, 0, tv_tag.getWidth(), tv_tag.getHeight());//缺少这句的话，不会报错，但是图片不回显示
            ImageSpan span = new ImageSpan(d, ImageSpan.ALIGN_BOTTOM);//图片将对齐底部边线
            int startIndex;
            int endIndex;
            startIndex = getLastLength(tags, i );
            endIndex = startIndex + item.length();
            PLog.e(TAG, "the start is" + startIndex + "the end is" + endIndex);
            spannableString.setSpan(span, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        setText(spannableString);
        setGravity(Gravity.CENTER_VERTICAL);
    }

    private int getLastLength(String[] tags, int maxLength) {
        int length = 0;
        for (int i = 0; i < maxLength; i++) {
            length += tags[i].length();
        }
        return length;
    }


    private static Bitmap convertViewToBitmap(View view) {
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
                , View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        return bitmap;
    }
}
