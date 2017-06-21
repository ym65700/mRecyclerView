package com.sample.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * 作者：Administrator on 2017/6/20 23:19
 * 作用：
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private final Context context;
    private final List<String> datas;

    public RecyclerAdapter(Context context, List<String> datas) {
        this.context = context;
        this.datas = datas;
    }

    /**
     * 相当于 ListView 适配器中的 getView 的创建 holder 布局
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_recyclerview, null);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position) {
        holder.tv.setText(datas.get(position));

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void addData(int position, String data) {
        datas.add(position,data);
        notifyItemInserted(position);
    }

    public void removeData(int position) {
        datas.remove(position);
        notifyItemRemoved(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv;
        private ImageView iv_icon;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_icon = (ImageView) itemView.findViewById(R.id.iv_icon);
            tv = (TextView) itemView.findViewById(R.id.tv_text);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(v, datas.get(getLayoutPosition()));
                    }
                }
            });

        }
    }

    public interface OnItemClickListener {
        /**
         * 某个选项被点击回调
         *
         * @param view
         * @param data
         */
        public void onItemClick(View view, String data);

    }

    private OnItemClickListener onItemClickListener;

    /**
     * 设置某个监听
     *
     * @param onItemClickListener
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}

