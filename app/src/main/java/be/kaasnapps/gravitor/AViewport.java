package be.kaasnapps.gravitor;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by jonas on 6/05/2015.
 */
public class AViewport extends Activity implements View.OnTouchListener
{

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView( new PortView( this ) );

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

    private class PortView extends View
    {
        // the length and width of a single square
        private int tileSide = 50;
        // the number of squares along x-axis and y-axis
        private int numTiles = 15;

        int fieldWidth = numTiles * tileSide;
        int fieldHeight = numTiles * tileSide;

        private int halfViewWidth;
        private int halfViewHeight;
        private int maxTranslateX;
        private int maxTranslateY;

        int viewWidth = 50;
        int viewHeight = 50;

        private int circleX;
        private int circleY;
        private int diameter = 50;

        private ShapeDrawable one;
        private ShapeDrawable two;

        private ShapeDrawable circle;
        Rect rect = new Rect();
        private Paint p;

        public PortView( Context context )
        {
            super( context );
            one = new ShapeDrawable( new RectShape() );
            two = new ShapeDrawable( new RectShape() );
            circle = new ShapeDrawable( new OvalShape() );
            one.getPaint().setColor( 0x88FF8844 );
            two.getPaint().setColor( 0x8844FF88 );
            circle.getPaint().setColor( 0x99000000 );
            p = new Paint();
            setFocusable( true );
        }

        @Override
        protected void onMeasure( int widthMeasureSpec, int heightMeasureSpec )
        {
            int width = Math.min( fieldWidth, MeasureSpec.getSize( widthMeasureSpec ) );
            int height = Math.min( fieldHeight, MeasureSpec.getSize( heightMeasureSpec ) );

            viewWidth = width;
            viewHeight = height;
            halfViewWidth = width/2;
            halfViewHeight = height/2;
            maxTranslateX = fieldWidth - width;
            maxTranslateY = fieldHeight - height;

            setMeasuredDimension( width, height );
        }
        // GOEDE UITLEG!!!!!
        /*
         _______________________________________
         | ______________                        |
         ||              |                       |
         ||              |vH                     |
         ||              |                       |
         ||______________|                       |
         |       vW                              |fH
         |       .(x1, y1)         ______________|
         |                        |              |
         |                        |              |
         |<---------------------->|              |
         |     maxTranslateX      |______________|
         |_______________________________________|
         fW

         We start translating once x,y goes past x1 (viewWidth/2),y1 (viewHeight/2).
         Movement along x-axis is x - x1, to the maximum of maxTranslateX (fieldWidth - viewWidth).
         The movement along y-axis is calculated similarly.
        */
        @Override
        protected void onDraw( Canvas canvas )
        {
            super.onDraw( canvas );

            canvas.save();

            if ( circleX > halfViewWidth )
            {
                int translateX = Math.min( circleX - halfViewWidth, maxTranslateX );
                canvas.translate( -translateX, 0 );
            }

            if ( circleY > halfViewHeight )
            {
                int translateY = Math.min( circleY - halfViewHeight, maxTranslateY );
                canvas.translate( 0, -translateY );
            }

            drawBoard( canvas );
            drawCircle( canvas );
            canvas.restore();
        }

        private void drawBoard( Canvas canvas )
        {
            int num = 1;
            boolean useTwo = false;
            for ( int row = 0; row < numTiles; row++ )
            {
                int y = row * tileSide;
                for ( int col = 0; col < numTiles; col++ )
                {
                    int x = col * tileSide;
                    Drawable d = useTwo ? two : one;
                    d.setBounds( x, y, x + tileSide, y + tileSide );
                    d.draw( canvas );
                    canvas.drawText( "" + num, x + 10 , y + 20, p );
                    ++num;
                    useTwo = !useTwo;
                }
            }
        }

        private void setRect()
        {
            rect.set( circleX, circleY, circleX + diameter, circleY + diameter );
        }

        private void drawCircle( Canvas canvas )
        {
            setRect();
            circle.setBounds( rect );
            circle.draw( canvas );
        }




        @Override
        public boolean onKeyDown( int keyCode, KeyEvent keyEvent )
        {
            boolean handled = true;
            switch ( keyCode )
            {
                case KeyEvent.KEYCODE_DPAD_DOWN:
                    if ( circleY <= fieldHeight - diameter - 5 ) circleY += 5;
                    break;
                case KeyEvent.KEYCODE_DPAD_UP:
                    if( circleY >= 5) circleY -= 5;
                    break;
                case KeyEvent.KEYCODE_DPAD_LEFT:
                    if( circleX >= 5 ) circleX -= 5;
                    break;
                case KeyEvent.KEYCODE_DPAD_RIGHT:
                    if( circleX <= fieldWidth - diameter -5 ) circleX += 5;
                    break;
                default: handled = false;
            }
            if ( handled )
            {
                invalidate();
            }
            return handled;
        }
    }
}
