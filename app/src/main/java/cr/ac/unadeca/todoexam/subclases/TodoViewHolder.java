package cr.ac.unadeca.todoexam.subclases;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import org.sufficientlysecure.htmltextview.HtmlTextView;

import cr.ac.unadeca.todoexam.R;

/**
 * Created by Freddy on 3/4/2018.
 */

public class TodoViewHolder extends RecyclerView.ViewHolder {

    public HtmlTextView html;
    public ImageView borrar;

    public TodoViewHolder(View itemView) {
        super(itemView);

        html=itemView.findViewById(R.id.html_text);
        borrar=itemView.findViewById(R.id.delete);

    }

}
