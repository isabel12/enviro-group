package com.example.swen303;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MessagesFragment extends Fragment {
	
	private int unreadMessages = 0;
	
	private HomeActivity container;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
        if (container == null) {
            // We have different layouts, and in one of them this
            // fragment's containing frame doesn't exist.  The fragment
            // may still be created from its saved state, but there is
            // no reason to try to create its view hierarchy because it
            // won't be displayed.  Note this is not needed -- we could
            // just run the code below, where we would create and return
            // the view hierarchy; it would just never be used.
            return null;
        }	
		
        this.container = (HomeActivity)container.getContext();
		
        View view = inflater.inflate(R.layout.fragment_messages, container, false);	
        
        
        TextView text = (TextView) view.findViewById(R.id.no_messages_text);
        if(this.unreadMessages != 0){
        	text.setVisibility(View.GONE);
        } else {
        	text.setVisibility(View.VISIBLE);
        }
        
        
		return view;	
		
		
	}
	
		
	
	public int GetNumberOfUnreadMessages(){
		return unreadMessages;
	}

}
