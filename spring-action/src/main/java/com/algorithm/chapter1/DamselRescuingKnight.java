package com.algorithm.chapter1;

/**
 * @program: com.algorithm.chapter1
 * @author: mashifei
 * @create: 2019-05-27-15
 */
public class DamselRescuingKnight implements Knight{

    private RescueDamselQuest quest;

    public DamselRescuingKnight(){
        quest = new RescueDamselQuest();
    }

    @Override
    public void embarkOnQuest() {
        quest.embark();
    }
}
