package org.example.mysteryboxlearn.dict.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.mysteryboxlearn.dict.entity.Dict;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class DictGenContext { // 字典上下文

    private List<String> dictTypes;
    private Map<String, List<Dict>> dictMap;
}