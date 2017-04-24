package com.mobilesolutionworks.android.imagepaging;

public interface Scrollable {

    boolean isHittingEdge(int direction);

    boolean isScrollable();
}
