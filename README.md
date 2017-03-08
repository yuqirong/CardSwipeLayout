#CardSwipeLayout

[![License](https://img.shields.io/badge/license-MIT-blue.svg)](https://opensource.org/licenses/mit-license.php)

##Introduction
CardSwipeLayout - Use RecyclerView to achieve card swipe layout , like [Tantan](http://tantanapp.com/) .

##Screenshot

![screenshot.gif](https://github.com/yuqirong/CardSwipeLayout/blob/master/screenshots/screenshot.gif)

##Usage

###step 1

For build.gradle :

	compile 'me.yuqirong:cardswipelayout:1.0.0'

Or Maven :

	<dependency>
	  <groupId>me.yuqirong</groupId>
	  <artifactId>cardswipelayout</artifactId>
	  <version>1.0.0</version>
	  <type>pom</type>
	</dependency>

###step 2

init RecyclerView firstly :

``` java
RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
recyclerView.setAdapter(...);
```

###step 3

then set `CardLayoutManager` for RecyclerView and `CardItemTouchHelperCallback` for ItemTouchHelper . In addition , don't forget set `OnSwipeListener` for `CardItemTouchHelperCallback` :

``` java
// dataList means dataSource for adapter
CardItemTouchHelperCallback cardCallback = new CardItemTouchHelperCallback(recyclerView.getAdapter(), dataList);
ItemTouchHelper touchHelper = new ItemTouchHelper(cardCallback); CardLayoutManager cardLayoutManager = new CardLayoutManager(recyclerView, touchHelper);
recyclerView.setLayoutManager(cardLayoutManager);
touchHelper.attachToRecyclerView(recyclerView);
cardCallback.setOnSwipedListener(new OnSwipeListener<T>() {

    @Override
    public void onSwiping(RecyclerView.ViewHolder viewHolder, float ratio, int direction) {
        /**
         * will callback when the card are swiping by user
         * viewHolder : thee viewHolder of swiping card
         * ratio : the ratio of swiping , you can add some animation by the ratio
         * direction : CardConfig.SWIPING_LEFT means swiping from left；CardConfig.SWIPING_RIGHT means swiping from right
         *             CardConfig.SWIPING_NONE means not left nor right
         */
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, T t, int direction) {
	    /**
	     *  will callback when the card swiped from screen by user
	     *  you can also clean animation from the itemview of viewHolder in this method
	     *  viewHolder : the viewHolder of swiped cards
	     *  t : the data of swiped cards from dataList
	     *  direction : CardConfig.SWIPED_LEFT means swiped from left；CardConfig.SWIPED_RIGHT means swiped from right
	     */
    }

    @Override
    public void onSwipedClear() {
        /**
         *  will callback when all cards swiped clear
         *  you can load more data 
         */
    }

});
```

Finally , enjoy it !!!

If you have any questions , you can leave a message in [Issues](https://github.com/yuqirong/CardSwipeLayout/issues).

##Contact Me
* Email : <yqr271228943@gmail.com>
* Weibo : [@活得好像一条狗](http://weibo.com/yyyuqirong)

##License

	MIT License
	
	Copyright (c) 2017 yuqirong
	
	Permission is hereby granted, free of charge, to any person obtaining a copy
	of this software and associated documentation files (the "Software"), to deal
	in the Software without restriction, including without limitation the rights
	to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
	copies of the Software, and to permit persons to whom the Software is
	furnished to do so, subject to the following conditions:
	
	The above copyright notice and this permission notice shall be included in all
	copies or substantial portions of the Software.
	
	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
	IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
	FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
	AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
	LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
	OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
	SOFTWARE.
