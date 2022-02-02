

package com.android.inputmethod.keyboard.internal;

import android.graphics.Canvas;
import android.view.View;

import com.android.inputmethod.keyboard.MainKeyboardView;
import com.android.inputmethod.keyboard.PointerTracker;

import javax.annotation.Nonnull;

/**
 * Abstract base class for previews that are drawn on DrawingPreviewPlacerView, e.g.,
 * GestureFloatingTextDrawingPreview, GestureTrailsDrawingPreview, and
 * SlidingKeyInputDrawingPreview.
 */
public abstract class AbstractDrawingPreview {
    private View mDrawingView;
    private boolean mPreviewEnabled;
    private boolean mHasValidGeometry;

    public void setDrawingView(@Nonnull final DrawingPreviewPlacerView drawingView) {
        mDrawingView = drawingView;
        drawingView.addPreview(this);
    }

    protected void invalidateDrawingView() {
        if (mDrawingView != null) {
            mDrawingView.invalidate();
        }
    }

    protected final boolean isPreviewEnabled() {
        return mPreviewEnabled && mHasValidGeometry;
    }

    public final void setPreviewEnabled(final boolean enabled) {
        mPreviewEnabled = enabled;
    }

    /**
     * Set {@link MainKeyboardView} geometry and position in the window of input method.
     * The class that is overriding this method must call this super implementation.
     *
     * @param originCoords the top-left coordinates of the {@link MainKeyboardView} in
     *        the input method window coordinate-system. This is unused but has a point in an
     *        extended class, such as {@link GestureTrailsDrawingPreview}.
     * @param width the width of {@link MainKeyboardView}.
     * @param height the height of {@link MainKeyboardView}.
     */
    public void setKeyboardViewGeometry(@Nonnull final int[] originCoords, final int width,
            final int height) {
        mHasValidGeometry = (width > 0 && height > 0);
    }

    public abstract void onDeallocateMemory();

    /**
     * Draws the preview
     * @param canvas The canvas where the preview is drawn.
     */
    public abstract void drawPreview(@Nonnull final Canvas canvas);

    /**
     * Set the position of the preview.
     * @param tracker The new location of the preview is based on the points in PointerTracker.
     */
    public abstract void setPreviewPosition(@Nonnull final PointerTracker tracker);
}
