
package org.chzz.dragview.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.chzz.dragview.R;
import org.chzz.dragview.model.DragChildInfo;

import java.util.ArrayList;

/**
 *
 * 类: CustomGridView <p>
 * 描述: TODO <p>
 * 作者: wedcel wedcel@gmail.com<p>
 * 时间: 2015年8月25日 下午7:07:44 <p>
 */
public class CustomGridView extends LinearLayout {

	private Context mContext;
	private ArrayList<DragChildInfo> mPlayList = new ArrayList<DragChildInfo>();
	private int viewHeight;
	private int viewWidth;
	private LinearLayout mParentView;
	private int rowNum;
	private int verticalViewWidth;
	private CustomChildClickListener childClickListener;

	public interface CustomChildClickListener {
		public void onChildClicked(DragChildInfo chilidInfo);
	}

	public CustomGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}






	public CustomChildClickListener getChildClickListener() {
		return childClickListener;
	}






	public void setChildClickListener(CustomChildClickListener childClickListener) {
		this.childClickListener = childClickListener;
	}






	/**
	 * 方法: initView <p>
	 * 描述: 初始化<p>
	 * 参数: @param context<p>
	 * 返回: void<p>
	 * 异常 <p>
	 * 作者: 梅雄新 meixx@500wan.com<p>
	 * 时间: 2014年11月15日 上午10:45:56<p>
	 */
	private void initView(Context context) {
		this.mContext = context;
		verticalViewWidth = CommUtil.dip2px(mContext, 1);
		View root = View.inflate(mContext, R.layout.gridview_child_layoutview, null);
		TextView textView = (TextView) root.findViewById(R.id.gridview_child_name_tv);
		int widthSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
		int heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
		textView.measure(widthSpec, heightSpec);
		viewHeight = textView.getMeasuredHeight();
		viewWidth = (mContext.getResources().getDisplayMetrics().widthPixels - CommUtil.dip2px(mContext, 2)) / CHZZDragView.COLUMNUM;
	}

	public CustomGridView(Context context) {
		super(context);
		initView(context);
	}

	/**
	 * 方法: refreshDataSet <p>
	 * 描述: 刷新页面<p>
	 * 参数: @param playList<p>
	 * 返回: void<p>
	 * 异常 <p>
	 * 作者: 梅雄新 meixx@500wan.com<p>
	 * 时间: 2014年11月15日 上午10:46:06<p>
	 */
	public void refreshDataSet(ArrayList<DragChildInfo> playList) {
		mPlayList.clear();
		mPlayList.addAll(playList);
		notifyDataSetChange(false);
	}

	/**
	 * 方法: notifyDataSetChange <p>
	 * 描述: 刷新UI<p>
	 * 参数: @param needAnim<p>
	 * 返回: void<p>
	 * 异常 <p>
	 * 作者: 梅雄新 meixx@500wan.com<p>
	 * 时间: 2014年11月15日 上午10:46:19<p>
	 */
	public void notifyDataSetChange(boolean needAnim) {
		removeAllViews();
		rowNum = mPlayList.size() / CHZZDragView.COLUMNUM + (mPlayList.size() % CHZZDragView.COLUMNUM > 0 ? 1 : 0);
		LinearLayout.LayoutParams rowParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		LinearLayout.LayoutParams verticalParams = new LinearLayout.LayoutParams(verticalViewWidth, LinearLayout.LayoutParams.FILL_PARENT);
		LinearLayout.LayoutParams horizontalParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, verticalViewWidth);
		for (int rowIndex = 0; rowIndex < rowNum; rowIndex++) {
			LinearLayout llContainer = new LinearLayout(mContext);
			llContainer.setOrientation(LinearLayout.HORIZONTAL);
			LinearLayout.LayoutParams itemParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
			itemParam.width = viewWidth;
			for (int columnIndex = 0; columnIndex < CHZZDragView.COLUMNUM; columnIndex++) {
				int itemInfoIndex = rowIndex * CHZZDragView.COLUMNUM + columnIndex;
				boolean isValidateView = true;
				if (itemInfoIndex >= mPlayList.size()) {
					isValidateView = false;
				}
				View root = View.inflate(mContext, R.layout.gridview_child_layoutview, null);
				TextView textView = (TextView) root.findViewById(R.id.gridview_child_name_tv);
				if (isValidateView) {
					final DragChildInfo tempChilid = mPlayList.get(itemInfoIndex);
					textView.setText(tempChilid.getName());
					textView.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							if (childClickListener != null) {
								childClickListener.onChildClicked(tempChilid);
							}
						}
					});
				}
				llContainer.addView(root, itemParam);
				if (columnIndex != CHZZDragView.COLUMNUM - 1) {
					View view = new View(mContext);
					view.setBackgroundResource(R.drawable.ver_line);
					llContainer.addView(view, verticalParams);
				}
			}
			addView(llContainer, rowParam);
			View view = new View(mContext);
			view.setBackgroundResource(R.drawable.hor_line);
			addView(view, horizontalParams);
			Log.e("animator", "" + getHeight() + "--" + rowNum * viewHeight);
			if (needAnim) {
				createHeightAnimator(mParentView, CustomGridView.this.getHeight(), rowNum * viewHeight);
			}
		}
	}

	/**
	 * 方法: createHeightAnimator <p>
	 * 描述: TODO<p>
	 * 参数: @param view
	 * 参数: @param start
	 * 参数: @param end<p>
	 * 返回: void<p>
	 * 异常 <p>
	 * 作者: 梅雄新 meixx@500wan.com<p>
	 * 时间: 2014年11月15日 上午10:46:35<p>
	 */
	public void createHeightAnimator(final View view, int start, int end) {
		ValueAnimator animator = ValueAnimator.ofInt(start, end);
		animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

			@Override
			public void onAnimationUpdate(ValueAnimator valueAnimator) {
				int value = (Integer) valueAnimator.getAnimatedValue();

				ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
				layoutParams.height = value;
				view.setLayoutParams(layoutParams);
			}
		});
		animator.setDuration(200);
		animator.start();
	}

	/**
	 * 方法: setParentView <p>
	 * 描述: TODO<p>
	 * 参数: @param llBtm<p>
	 * 返回: void<p>
	 * 异常 <p>
	 * 作者: 梅雄新 meixx@500wan.com<p>
	 * 时间: 2014年11月15日 上午10:46:40<p>
	 */
	public void setParentView(LinearLayout llBtm) {
		this.mParentView = llBtm;
	}
}
