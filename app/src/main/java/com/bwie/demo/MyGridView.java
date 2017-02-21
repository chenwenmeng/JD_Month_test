package com.bwie.demo;

import android.widget.GridView;

/**
 * 类描述:
 * 作者：陈文梦
 * 时间:2017/2/21 14:06
 * 邮箱:18310832074@163.com
 */

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class MyGridView extends GridView {

    private OnTouchInvalidPositionListener onTouchInvalidPositionListener;

    public MyGridView(Context context) {
        super(context);
    }

    public MyGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        //先创建一个监听接口，一旦点击了无效区域，便实现onTouchInvalidPosition方法，返回true or false来确认是否消费了这个事件
        if(onTouchInvalidPositionListener!=null){
            if(!isEnabled()){
                return isClickable()||isLongClickable();
            }
            int motionPosition = pointToPosition((int)ev.getX(), (int)ev.getY());
            if(ev.getAction()==MotionEvent.ACTION_UP&&motionPosition == INVALID_POSITION){
                super.onTouchEvent(ev);
                return onTouchInvalidPositionListener.onTouchInvalidPosition(motionPosition);
            }
        }
        return super.onTouchEvent(ev);
    }

    public void setOnTouchInvalidPositionListener(
            OnTouchInvalidPositionListener onTouchInvalidPositionListener) {
        this.onTouchInvalidPositionListener = onTouchInvalidPositionListener;
    }

    public interface OnTouchInvalidPositionListener{
        public boolean onTouchInvalidPosition(int motionEvent);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if(ev.getAction() == MotionEvent.ACTION_MOVE){
            return true;//true:禁止滚动
        }

        return super.dispatchTouchEvent(ev);
    }

}
