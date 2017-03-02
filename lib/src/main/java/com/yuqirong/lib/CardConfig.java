package com.yuqirong.lib;

/**
 * @author maomao
 * @email qirong.yu@jubaobar.com
 * @date 2017-3-2
 */

public interface CardConfig {
    /**
     * 显示可见的卡片数量
     */
    int DEFAULT_SHOW_ITEM = 3;
    /**
     * 默认缩放的比例
     */
    float DEFAULT_SCALE = 0.1f;
    /**
     * 卡片Y轴偏移量时按照14等分计算
     */
    int DEFAULT_TRANSLATE_Y = 14;
    /**
     * 卡片滑动时默认倾斜的角度
     */
    float DEFAULT_ROTATE_DEGREE = 15f;
    /**
     * 卡片滑动时不偏左也不偏右
     */
    int SWIPING_NONE = 1;
    /**
     * 卡片向左滑动时
     */
    int SWIPING_LEFT = 1 << 2;
    /**
     * 卡片向右滑动时
     */
    int SWIPING_RIGHT = 1 << 3;
    /**
     * 卡片从左边滑出
     */
    int SWIPED_LEFT = 1;
    /**
     * 卡片从右边滑出
     */
    int SWIPED_RIGHT = 1 << 2;

}
