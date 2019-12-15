package cn.dante.t01huanchong;

import java.awt.*;
//改代码并未完全通透
public class BufferMovingCircle extends NoBufferMovingCircle{
    Graphics doubleBuffer = null;   //缓冲区
    @Override
    public void init() {
        super.init();
        doubleBuffer = screenImage.getGraphics();
    }

    public void paint(Graphics g){      //优化原有的paint方法
        doubleBuffer.setColor(Color.WHITE); //现在内存中画图
        doubleBuffer.fillRect(0,0,200,100);
        drawCircle(doubleBuffer);
        g.drawImage(screenImage,0,0,this);  //将buffer一次性展示出来
    }
}
