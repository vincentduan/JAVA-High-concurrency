package com.vincent.example.publish;

import com.vincent.annotation.NotRecommend;
import com.vincent.annotation.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NotThreadSafe
@NotRecommend
public class Escape {
    private int thisCannBeEscape = 0;
    public Escape() {
        new InnerClass();
    }
    private class InnerClass {
        public InnerClass() {
            log.info("{}",Escape.this.thisCannBeEscape);
        }
    }

    public static void main(String[] args) {
        new Escape();
    }
}
