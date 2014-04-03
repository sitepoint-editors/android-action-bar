package com.example.actionbar;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.MenuItemCompat.OnActionExpandListener;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements TextView.OnEditorActionListener {
	
	private MenuItem myActionMenuItem;
	private EditText myActionEditText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main_activity_bar, menu);
	    
	    // Here we get the action view we defined
	    myActionMenuItem = menu.findItem(R.id.my_action);
	    View actionView = myActionMenuItem.getActionView();
	    
	    // We then get the edit text view that is part of the action view
	    if(actionView != null) {
	    	myActionEditText = (EditText) actionView.findViewById(R.id.myActionEditText);
	    	if(myActionEditText != null) {
	    		// We set a listener that will be called when the return/enter key is pressed
	    		myActionEditText.setOnEditorActionListener(this);
	    	}
	    	
	    }
	    
	    // For support of API level 14 and below, we use MenuItemCompat
	    MenuItemCompat.setOnActionExpandListener(myActionMenuItem, new OnActionExpandListener() {
	        @Override
	        public boolean onMenuItemActionCollapse(MenuItem item) {
	            // Do something when collapsed
	            return true;  // Return true to collapse action view
	        }

	        @Override
	        public boolean onMenuItemActionExpand(MenuItem item) {
	            // Do something when expanded
	        	if(myActionEditText != null) {
	        		myActionEditText.setText("");
	        	}
	            return true;  // Return true to expand action view
	        }
	    });
	    
	    return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(event.getAction() == MotionEvent.ACTION_DOWN) {
			toggleActionBar();
		}
		
		return true;
	}
	
	private void toggleActionBar() {
		ActionBar actionBar = getActionBar();
		if(actionBar != null) {
			if(actionBar.isShowing()) {
				actionBar.hide();
			}
			else {
				actionBar.show();
			}
		}
	}
	
	public void openSecondActivity(View view) {
		Intent intent = new Intent(this, SecondActivity.class);
		startActivity(intent);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.action_search:
	        	// Code you want run when activity is clicked
	        	Toast.makeText(this, "Search clicked", Toast.LENGTH_SHORT).show();
	            return true;
	        case R.id.action_record:
	        	Toast.makeText(this, "Record clicked", Toast.LENGTH_SHORT).show();
	            return true;
	        case R.id.action_save:
	        	Toast.makeText(this, "Save clicked", Toast.LENGTH_SHORT).show();
	            return true;
	        case R.id.action_label:
	        	Toast.makeText(this, "Label clicked", Toast.LENGTH_SHORT).show();
	            return true;
	        case R.id.action_play:
	        	Toast.makeText(this, "Play clicked", Toast.LENGTH_SHORT).show();
	            return true;
	        case R.id.action_settings:
	        	Toast.makeText(this, "Settings clicked", Toast.LENGTH_SHORT).show();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	@Override
	public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
		if(keyEvent != null) {
			// When the return key is pressed, we get the text the user entered, display it and collapse the view
			if(keyEvent.getAction() == KeyEvent.ACTION_DOWN && keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
				CharSequence textInput = textView.getText();
				// Do something useful with the text
				Toast.makeText(this, textInput, Toast.LENGTH_SHORT).show();
				MenuItemCompat.collapseActionView(myActionMenuItem);
			}
		}
		return false;
	}

}
