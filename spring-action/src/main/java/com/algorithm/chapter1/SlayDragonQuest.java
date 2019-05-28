package com.algorithm.chapter1;

import java.io.PrintStream;

/**
 * @program: com.algorithm.chapter1
 * @author: mashifei
 * @create: 2019-05-27-16
 */
public class SlayDragonQuest implements Quest{

    private PrintStream stream;

    public SlayDragonQuest(PrintStream stream){
        this.stream = stream;
    }

    @Override
    public void embark() {
        stream.println("Embarking on quest to slay the dragon.");
    }
}
