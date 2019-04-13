package com.dj.strategypattern;

import java.util.HashMap;
import java.util.Map;

public class TeachStrategyFactory {
    private static Map<String,TeachStrategy> KEY_TEACHSTRATEHY_MAP = new HashMap<String, TeachStrategy>();
    static {
        KEY_TEACHSTRATEHY_MAP.put("PPT",new PptTeachStrategy()) ;
        KEY_TEACHSTRATEHY_MAP.put("PRACTICE",new PracticeTeach());
    }

    public static TeachStrategy getStrategy(String strategyKey){
        return KEY_TEACHSTRATEHY_MAP.get(strategyKey);
    }
}
