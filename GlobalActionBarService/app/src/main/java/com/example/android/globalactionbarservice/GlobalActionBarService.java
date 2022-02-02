// Copyright 2016 Google Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.example.android.globalactionbarservice;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.GestureDescription;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.media.AudioManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.FrameLayout;

import java.util.ArrayDeque;
import java.util.Deque;

public class GlobalActionBarService extends AccessibilityService {
    FrameLayout mLayout;
    ...
        
    @Override
    protected void onServiceConnected() {  
       // Create an overlay and display the action bar
       WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
       mLayout = new FrameLayout(this);
       WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
       lp.type = WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY;
       lp.format = PixelFormat.TRANSLUCENT;
       lp.flags |= WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
       lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
       lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
       lp.gravity = Gravity.TOP;
       LayoutInflater inflater = LayoutInflater.from(this);
       inflater.inflate(R.layout.action_bar, mLayout);
       wm.addView(mLayout, lp);
       configureSwipeButton();  //Configures the swipe button with hardcardcoded points
		configureScrollButton();
    }
           

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {

    }

    @Override
    public void onInterrupt() {

    }
}

private void configureSwipeButton() {
   Button swipeButton = (Button) mLayout.findViewById(R.id.swipe);
   swipeButton.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View view) {
           Path swipePath = new Path();
           swipePath.moveTo(1000, 1000);
           swipePath.lineTo(100, 1000);
           GestureDescription.Builder gestureBuilder = new GestureDescription.Builder();
           gestureBuilder.addStroke(new GestureDescription.StrokeDescription(swipePath, 0, 500));
           dispatchGesture(gestureBuilder.build(), null, null);
       }
   });
}

private AccessibilityNodeInfo findScrollableNode(AccessibilityNodeInfo root) {
   Deque<AccessibilityNodeInfo> deque = new ArrayDeque<>();
   deque.add(root);
   while (!deque.isEmpty()) {
       AccessibilityNodeInfo node = deque.removeFirst();
       if (node.getActionList().contains(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_FORWARD)) {
           return node;
       }
       for (int i = 0; i < node.getChildCount(); i++) {
           deque.addLast(node.getChild(i));
       }
   }
   return null;
}

private void configureScrollButton() {
   Button scrollButton = (Button) mLayout.findViewById(R.id.scroll);
   scrollButton.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View view) {
           AccessibilityNodeInfo scrollable = findScrollableNode(getRootInActiveWindow());
           if (scrollable != null) {
               scrollable.performAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_FORWARD.getId());
           }
       }
   });
}

public static final AccessibilityNodeInfo.AccessibilityAction ACTION_PASTE
public static final AccessibilityNodeInfo.AccessibilityAction ACTION_CLICK   //click jenj
public static final AccessibilityNodeInfo.AccessibilityAction ACTION_CLICK   //click send
public static final AccessibilityNodeInfo.AccessibilityAction ACTION_CLICK   //click address
public static final AccessibilityNodeInfo.AccessibilityAction ACTION_PASTE 			//Paste ammount
public static final AccessibilityNodeInfo.AccessibilityAction ACTION_CLICK   //click ammount
public static final AccessibilityNodeInfo.AccessibilityAction ACTION_PASTE 			//Paste ammount
public static final AccessibilityNodeInfo.AccessibilityAction ACTION_CLICK   //click next step
public static final AccessibilityNodeInfo.AccessibilityAction ACTION_CLICK   //click send now
																			//Check the node TXRECEIPT STATUS for Success
public static final AccessibilityNodeInfo.AccessibilityAction ACTION_CLICK   //click back																			
public static final AccessibilityNodeInfo.AccessibilityAction ACTION_CLICK   //click back to main wallet

public static final AccessibilityNodeInfo.AccessibilityAction ACTION_SHOW_ON_SCREEN  //Shows the node boundary on screen, may be good for testing
public static final int TYPE_WINDOW_CONTENT_CHANGED  //Detects a change in the window
//Grant a clayout acess and then send a action click to the window in order to start it.


public void addAction (AccessibilityNodeInfo.AccessibilityAction action) //Add an action
public boolean performAccessibilityAction (int action, 
                Bundle arguments)  //Performs the action a view
	public void onInitializeAccessibilityNodeInfo (AccessibilityNodeInfo info) //Initiliases a Node info with information on the view.

	//Accessability event communicates UI changes.
	//We need to simulate that change.
	
	
public void onInitializeAccessibilityNodeInfo (AccessibilityNodeInfo info)  //Initiliases a node with infromation on the view
	
//AccessibilityWindowInfo(AccessibilityWindowInfo info)
//Start of Program
	
	
	
	
	
	
