package com.algorithm.chapter1;

/**
 * @program: com.algorithm.chapter1
 * @author: mashifei
 * @create: 2019-05-27-15
 */
public class BraveKnight implements Knight{

    private Quest quest;

    public BraveKnight(Quest quest){
        this.quest = quest;
    }

    @Override
    public void embarkOnQuest() {
        quest.embark();
    }

}
