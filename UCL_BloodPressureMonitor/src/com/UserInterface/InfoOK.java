package com.UserInterface;



import com.Common.R;
import com.Controller.generalCtrl;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class InfoOK extends Activity {
	private static final String TAG = "InfoOK";
	private generalCtrl GCtrl;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        makeLogEntry("open");
        setContentView(R.layout.infook);
        makeLogEntry("setcontentview");
        RelativeLayout mainLayout = (RelativeLayout)findViewById(R.id.infoLayout);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        lp.addRule(RelativeLayout.CENTER_IN_PARENT);
        Button addButton =new Button(this);
        makeLogEntry("create button");
        addButton.setText(R.string.OK);
        makeLogEntry("set button text");
        mainLayout.addView(addButton,lp);
        makeLogEntry("add button to view");
        GCtrl=new generalCtrl(this);
        GCtrl.actOncreat();
        TextView info = (TextView) findViewById(R.id.infotext);
        String infonote="h";
        Bundle extras=getIntent().getExtras();
        infonote = extras.getString("infotext");
        info.setText(infonote);
        makeLogEntry("set info text");
        final int btncode = extras.getInt("buttoncode");
        addButton.setOnClickListener(new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			GCtrl.Buttonhandler(btncode);
			makeLogEntry("made it to the button");
		}
        });
    }
    private void makeLogEntry(String s){
	Log.d(TAG,s);
    }
}
