package com.august.commons;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class TreeItem {
    private String Id;
    private String parendId;
    private String menuName;
    // true:选中  false:未选中
    private List<TreeItem> childrenList;

    public TreeItem(String id, String parendId, String menuName) {
        Id = id;
        this.parendId = parendId;
        this.menuName = menuName;
    }
}
