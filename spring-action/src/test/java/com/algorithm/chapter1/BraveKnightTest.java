package com.algorithm.chapter1;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @program: com.algorithm.chapter1
 * @author: mashifei
 * @create: 2019-05-27-15
 */

public class BraveKnightTest {

    @Test
    public void knightShouldEmbarkOnQuest(){
        Quest quest = mock(Quest.class);
        BraveKnight knight = new BraveKnight(quest);
        knight.embarkOnQuest();
        verify(quest,times(1)).embark();
    }
}
