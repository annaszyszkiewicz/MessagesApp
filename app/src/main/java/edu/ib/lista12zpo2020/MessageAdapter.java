package edu.ib.lista12zpo2020;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * klasa tworząca adapter i view holder
 */
// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class MessageAdapter extends
        RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    /**
     * klasa sluzaca do buforowania widoków w celu szybkiego dostepu
     */
    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView msgTV;

        /**
         * konstruktor wiadomosci
         *
         * @param itemView pojedynczy element
         */
        public ViewHolder(View itemView) {
            super(itemView);
            msgTV = (TextView) itemView.findViewById(R.id.tvMsg);
        }
    }

    private List<Message> msgList;

    /**
     * konstruktor listy wiadomosci
     *
     * @param messages lista wiadomosci
     */
    public MessageAdapter(List<Message> messages) {
        msgList = messages;
    }

    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View msgView = inflater.inflate(R.layout.message, parent, false);

        ViewHolder viewHolder = new ViewHolder(msgView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MessageAdapter.ViewHolder holder, int position) {
        Message msg = msgList.get(position);

        TextView textView = holder.msgTV;
        textView.setText(msg.toString());
    }

    /**
     * metoda zwracajaca ilosc wiaodmosci w liscie
     *
     * @return ilosc wiadomosci w liscie
     */
    @Override
    public int getItemCount() {
        return msgList.size();
    }
}
