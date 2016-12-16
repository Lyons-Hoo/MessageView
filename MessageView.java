package com.example.lyons.demo.customerview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

/**
 * Created by Lyons on 2016/12/6.
 */

public class MessageView extends View {

    private Context mContext;

    /**
     * 绘制背景画笔
     */
    private Paint mMessageBackgroundPaint;

    /**
     * 背景圆半径
     */
    private int mCircleRadius;

    /**
     * 绘制文本画笔
     */
    private Paint mMessageTextPaint;

    /**
     * 绘制的文本
     */
    private String mMessageText;

    /**
     * 绘制文本的大小
     */
    private int mMessageTextSize;

    /**
     * 默认位于依附的view的右上角
     */
    private int mGravity = Gravity.RIGHT | Gravity.TOP;

    private static final String TAG = "MessageView";

    private static final String EXCEPTION = "Exception Of " + TAG + ":";

    /**
     * @param context
     */
    public MessageView(Context context) {
        this(context, null);
    }

    public MessageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MessageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initMessageView(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MessageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setMessageBackgroundColor(int mBackgroundColor) {
        this.mMessageBackgroundPaint.setColor(mBackgroundColor);
    }

    /**
     * 初始化
     *
     * @param context
     */
    private void initMessageView(Context context) {
        if (null == context) {
            throw new IllegalArgumentException(EXCEPTION + "The context reference a NULL ");
        }
        this.mContext = context.getApplicationContext();
        mMessageBackgroundPaint = new Paint();
        mMessageBackgroundPaint.setAntiAlias(true); // 开启抗锯齿
        mMessageBackgroundPaint.setColor(Color.parseColor("#DD6B55")); // 默认背景颜色
        mMessageTextPaint = new Paint();
        mMessageTextPaint.setAntiAlias(true);
        mMessageTextPaint.setColor(Color.WHITE); // 默认消息文本颜色
        mMessageTextSize = dip2px(mContext, 7); // 默认文本大小
        mMessageText = " ";
        initMessageText();
        this.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, mGravity));
    }

    /**
     * 初始背景圆的半径
     */
    private void initCircleRadius() {
        mCircleRadius = (int) (mMessageTextSize * (
                mMessageText.length() > 1 ? mMessageText.length() / 2.7 : mMessageText.length() / 2.1)
        );
    }

    /**
     * 设置显示消息
     */
    private void initMessageText() {
        mMessageTextPaint.setTextSize(mMessageTextSize);
        initCircleRadius();
    }

    /**
     * 设置消息文本
     *
     * @param quantity
     */
    public void setMessageQuantity(int quantity) {
        this.mMessageText = quantity > 99 ? "99+" : quantity + "";
        initCircleRadius();
    }

    /**
     * 设置消息文本大小
     *
     * @param textSize
     */
    public void setMessageTextSize(float textSize) {
        this.mMessageTextSize = dip2px(mContext, textSize);
        initMessageText();
    }

    /**
     * 设置消息文本颜色
     *
     * @param textColor
     */
    public void setMessageTextColor(int textColor) {
        this.mMessageTextPaint.setColor(textColor);
    }

    /**
     * 设置消息Gravity
     *
     * @param gravity
     */
    public void setMessageGravity(int gravity) {
        final FrameLayout.LayoutParams layoutParams = getMessageViewLayoutParams();
        layoutParams.gravity = gravity;
    }

    /**
     * 设置Margin信息 Begin
     *
     * @param leftMargin
     */
    public void setLeftMargin(int leftMargin) {
        final FrameLayout.LayoutParams layoutParams = getMessageViewLayoutParams();
        layoutParams.leftMargin = dip2px(mContext, leftMargin);
    }

    public void setTopMargin(int topMargin) {
        final FrameLayout.LayoutParams layoutParams = getMessageViewLayoutParams();
        layoutParams.topMargin = dip2px(mContext, topMargin);
    }

    public void setRightMargin(int rightMargin) {
        final FrameLayout.LayoutParams layoutParams = getMessageViewLayoutParams();
        layoutParams.rightMargin = dip2px(mContext, rightMargin);
    }

    public void setBottomMargin(int bottomMargin) {
        final FrameLayout.LayoutParams layoutParams = getMessageViewLayoutParams();
        layoutParams.bottomMargin = dip2px(mContext, bottomMargin);
    }

    public void setMargin(int margin) {
        final FrameLayout.LayoutParams layoutParams = getMessageViewLayoutParams();
        layoutParams.leftMargin = dip2px(mContext, margin);
        layoutParams.topMargin = dip2px(mContext, margin);
        layoutParams.rightMargin = dip2px(mContext, margin);
        layoutParams.bottomMargin = dip2px(mContext, margin);
    }

    /**
     * 设置Margin信息 End
     *
     * @param leftMargin
     * @param topMargin
     * @param rightMargin
     * @param bottomMargin
     */
    public void setMargin(int leftMargin, int topMargin, int rightMargin, int bottomMargin) {
        final FrameLayout.LayoutParams layoutParams = getMessageViewLayoutParams();
        layoutParams.leftMargin = dip2px(mContext, leftMargin);
        layoutParams.topMargin = dip2px(mContext, topMargin);
        layoutParams.rightMargin = dip2px(mContext, rightMargin);
        layoutParams.bottomMargin = dip2px(mContext, bottomMargin);
    }

    private FrameLayout.LayoutParams getMessageViewLayoutParams() {
        return (FrameLayout.LayoutParams) this.getLayoutParams();
    }

    /**
     * 设置依附的目标view
     *
     * @param dependView
     */
    public void setDependView(View dependView) {
        if (null == dependView) {
            throw new IllegalArgumentException(EXCEPTION + "DependView is not exist!");
        }
        /**
         * 处理5.0及以上系统Button总会在最上层的问题
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (dependView instanceof Button) {
                dependView.setStateListAnimator(null);
            }
        }
        final ViewGroup dependViewParent = (ViewGroup) dependView.getParent(); // 获取目标view容器
        // 父容器是帧布局直接将this添加进去即可
        if (dependViewParent instanceof FrameLayout) {
            /**
             * 这行代码是为了让消息位于依赖view的上方
             * 这样做了以后位置会变化，建议自己适当调整下布局吧
             * 如果不加这行代码，消息会在依赖view的内部
             */
            ViewGroup.MarginLayoutParams dependViewLP = (ViewGroup.MarginLayoutParams) dependView.getLayoutParams();
            if (dependViewLP.topMargin == 0) {
                dependViewLP.topMargin = dip2px(mContext, 10);
            } else {
                this.getMessageViewLayoutParams().topMargin += dependViewLP.topMargin - dip2px(mContext, 10);
            }
            dependViewParent.addView(MessageView.this);
            return;
        }
        // 先获取在父容器中的下标（移除以后就获取不到了）
        final int targetViewInParentIndex = dependViewParent.indexOfChild(dependView);
        // 从目标view父容器移除它(因为需要将消息view和目标view进行依附)
        dependViewParent.removeView(dependView);
        // 包裹目标view和消息view的容器
        final FrameLayout container = new FrameLayout(mContext);
        // 获取依赖view的LP，其实就是为了让容器还保持依赖view原来的位置
        container.setLayoutParams(dependView.getLayoutParams());
        /**
         * 将依附view和MessageView添加到容器
         * 最后添加到依附view之前的父容器
         */
        container.addView(dependView);
        /**
         * 这两行代码是为了让消息位于依赖view的上方（一定要添加进容器以后再get，
         * 因为get出来的是FrameLayout的LP，不再是原来的LP了，可以自己Debug查看）
         * 如果不加这行代码，消息会在依赖view的内部
         * 这样做了以后位置会变化，建议自己适当调整下布局吧
         */
        final ViewGroup.MarginLayoutParams newDependLP = (ViewGroup.MarginLayoutParams) dependView.getLayoutParams();
        if (newDependLP.topMargin == 0) {
            newDependLP.topMargin = dip2px(mContext, 10);
        }

        container.addView(MessageView.this);
        dependViewParent.addView(container, targetViewInParentIndex);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(mCircleRadius * 2, mCircleRadius * 2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 先画MessageView背景
        canvas.drawCircle(mCircleRadius, mCircleRadius, mCircleRadius, mMessageBackgroundPaint);
        // 然后画文本
        float x;
        float y;
        switch (mMessageText.length()) {
            case 0:
            case 1:
                x = (float) (mCircleRadius / 2.5);
                y = (float) (mCircleRadius * 1.75);
                break;
            case 2:
                x = (float) (mCircleRadius / 3.9);
                y = (float) (mCircleRadius * 1.5);
                break;
            default:
                x = (float) (mCircleRadius / 3.9);
                y = (float) (mCircleRadius * 1.3);
                break;
        }
        canvas.drawText(mMessageText, x, y, mMessageTextPaint);
    }

    /**
     * @param context
     * @param dipValue
     * @return
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
