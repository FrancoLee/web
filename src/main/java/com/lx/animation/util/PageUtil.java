package com.lx.animation.util;

import com.lx.animation.page.Page;

/**
 * Created by root on 17-12-12.
 */
public class PageUtil {
    public Page setPage(int index,int count){
        Page page=new Page();
        page.setIndex(index);
        if(index>5) {
            page.setPageBeg(index-5);
            index=index-5;
        }
        else {
            page.setPageBeg(1);
            index=1;
        }
        page.setMaxPage((int)Math.ceil((double) count/5.0));
        if(page.getMaxPage()-index>=5)
            page.setPageEnd(index+5);
        else
            page.setPageEnd(page.getMaxPage());
        return page;
    }
}
