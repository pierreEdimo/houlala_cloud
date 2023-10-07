package com.houlala.marketplace.subCategories;

import com.houlala.marketplace.category.Category;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MappedSubCategory {
    private String label;
    private Category category;
}
