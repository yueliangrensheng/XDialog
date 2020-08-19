package com.yazao.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;

/**
 * 类描述：
 *
 * @author zhaishaoping
 * @data 25/01/2018 8:15 PM
 */

public class XDialog extends Dialog {

    @StyleRes
    private int resId = 0;//Dialog 进出动画的资源Id
    private int height = ViewGroup.LayoutParams.WRAP_CONTENT;//Dialog布局的高度
    private int width = ViewGroup.LayoutParams.MATCH_PARENT;//Dialog布局的宽度

    private XDialog(@NonNull Context context) {
        super(context, R.style.XDialogStyle);
    }

    private XDialog(@NonNull Context context, int resId, int width, int height) {
        super(context, R.style.XDialogStyle);
        this.resId = resId;
        this.width = width;
        this.height = height;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (mLayoutView != null) {
//            setContentView(mLayoutView);
//        }
        getWindow().setLayout(width, height);
        if (0 != resId) {
            getWindow().setWindowAnimations(resId);
        }

    }

    public static class Builder {

        private XDialog dialog;
        private View mLayoutView;
        private SparseArray<View> mViews = new SparseArray<>();
        Context mContext;
        boolean cancelable = true;
        boolean canceledOnTouchOutside = true;
        private int gravity = Gravity.CENTER;//Dialog 显示布局 Gravity
        @StyleRes
        private int resId = 0;//Dialog 进出动画
        private int height = ViewGroup.LayoutParams.WRAP_CONTENT;//Dialog布局的高度
        private int width = ViewGroup.LayoutParams.MATCH_PARENT;//Dialog布局的宽度


        public Builder(Context context) {
            mContext = context;
        }

        public Builder setLayoutRes(@LayoutRes int layoutRes) {
            mLayoutView = LayoutInflater.from(mContext).inflate(layoutRes, null, false);
            return this;
        }

        public Builder setCancelable(boolean flag) {
            cancelable = flag;
            return this;
        }

        public Builder setCanceledOnTouchOutside(boolean flag) {
            canceledOnTouchOutside = flag;
            return this;
        }

        public Builder setVisibility(@IdRes int viewId, int visibility) {
            View view = getView(viewId);
            view.setVisibility(visibility);
            return this;
        }

        public Builder setText(@IdRes int viewId, CharSequence text) {
            if (viewId == 0) {
                return this;
            }

            TextView view = getView(viewId);
            view.setText(text);
            return this;
        }

        public Builder setText(@IdRes int viewId, @StringRes int resId) {
            if (viewId == 0) {
                return this;
            }

            TextView view = getView(viewId);
            view.setText(resId);
            return this;
        }

        public Builder setTextUnderline(@IdRes int viewId) {
            if (viewId == 0) {
                return this;
            }

            TextView view = getView(viewId);
            view.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
            view.getPaint().setFlags(Paint.ANTI_ALIAS_FLAG);
            return this;
        }

//        public Builder setImageResource(@IdRes int viewId, String url, @DrawableRes int placeholderResId, BitmapTransformation transformation) {
//            if (viewId == 0) {
//                return this;
//            }
//
//            if (TextUtils.isEmpty(url)) {
//                return this;
//            }
//
//            ImageView imageView = getView(viewId);
//            if (transformation == null) {
//                ImageUtil.getInstance().loadImage(mContext, url, imageView, placeholderResId);
//            } else {
//                ImageUtil.getInstance().loadImage(mContext, url, imageView, placeholderResId, transformation);
//            }
//            return this;
//        }

        public Builder setImageBitmap(@IdRes int viewId, Bitmap bitmap) {
            if (viewId == 0) {
                return this;
            }

            if (bitmap == null) {
                return this;
            }

            ImageView imageView = getView(viewId);
            if (imageView == null) {
                return this;
            }
            imageView.setImageBitmap(bitmap);

            return this;
        }

        public Builder setOnClickListener(@IdRes int viewId, final OnDialogClickListener listener) {
            if (viewId == 0) {
                return this;
            }

            View view = getView(viewId);
            if (view == null) {
                return this;
            }

            if (listener == null) {
                return this;
            }
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(dialog, view);
                }
            });
            return this;
        }

        public Builder setGravity(int gravity) {
            this.gravity = gravity;
            return this;
        }

        public Builder setAnimation(@StyleRes int resId) {
            this.resId = resId;
            return this;
        }

        public Builder setSize(int width, int height) {
            this.width = width;
            this.height = height;
            return this;
        }

        private XDialog create() {
            return create(true);
        }

        private XDialog create(boolean isForceCreateDialog) {

            //设置默认 值
            if (gravity == Gravity.BOTTOM && resId == 0) {
                resId = R.style.XDialogAnim;
            }

            if (isForceCreateDialog) {
                dialog = new XDialog(mContext, resId, width, height);
            } else {
                if (dialog == null) {
                    dialog = new XDialog(mContext, resId, width, height);
                }
            }

            if (mLayoutView != null) {
                dialog.setContentView(mLayoutView);
            }

            dialog.getWindow().setGravity(gravity);

            dialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
            dialog.setCancelable(cancelable);
            return dialog;
        }


        public XDialog show() {
            XDialog dialog = create(false);
            dialog.show();
            return dialog;
        }


        public <T extends View> T getView(@IdRes int viewId) {
            View view = mViews.get(viewId);
            if (view == null) {
                view = mLayoutView.findViewById(viewId);
                mViews.put(viewId, view);
            }

            return (T) view;
        }


        public void hide() {
            if (dialog != null && dialog.isShowing()) {
                dialog.hide();
            }
        }


    }

    public void onDestroy() {
        if (this != null) {
            this.cancel();
        }
    }
}
